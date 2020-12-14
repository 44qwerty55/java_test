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


public class ApplicationManager {
  WebDriver wd;
  private final Properties properties;
  private String browser;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target =  System.getProperty("target" , "local");

    properties.load(new FileReader(new File(String.format("src\\test\\resources\\%s.properties", target))));



    // String browser = BrowserType.FIREFOX;
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    }

    // wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //   wd.get("http://localhost/addressbook/");
    //подстановка из файла
    wd.get(properties.getProperty("web.baseUrl"));

  }


  public void stop() {
    wd.quit();
  }


  // выносим возможность читать файл конфига в тестах
  public Properties properties(){
    return properties;
  }

}
