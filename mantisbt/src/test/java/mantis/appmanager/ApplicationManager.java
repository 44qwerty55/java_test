package mantis.appmanager;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.regex.MatchResult;


public class ApplicationManager {
  private WebDriver wd;
  private final Properties properties;
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target =  System.getProperty("target" , "local");

    properties.load(new FileReader(new File(String.format("src\\test\\resources\\%s.properties", target))));





  }

// остановка драйвера так же проверяет , что он запущен
  public void stop() {
    if (wd != null){
    wd.quit();}
  }

  public HttpSession newSession() {
    return new  HttpSession(this);
  }

  public String getProperty(String key) {
   return properties.getProperty(key);
  }


  // выносим возможность читать файл конфига в тестах
  public Properties properties(){
    return properties;
  }
 // обращение к браузеру минуя init
 // public RegistrationHelper registration() {
  //  return new RegistrationHelper(this);}
  // обращение к браузеру с условием
  public RegistrationHelper registration() {
    if (registrationHelper == null){
   registrationHelper = new RegistrationHelper(this);}
    return registrationHelper;
  }

  // инициализация для ftphelper

public FtpHelper ftp() {
    if (ftp == null) {
      ftp = new FtpHelper(this);
    }
    return  ftp;
}


// вовзращаем браузер при непосредственном его указание (RegistrationHelper)
  public WebDriver getDriver() {
    // ининциализация драйвера, если он еще не проинициализирован
    if (wd == null){
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      }


      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      //подстановка из файла
      wd.get(properties.getProperty("web.baseUrl"));

    }
    return wd;
  }
}
