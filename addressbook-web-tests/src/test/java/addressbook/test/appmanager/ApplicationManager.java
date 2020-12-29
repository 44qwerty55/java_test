package addressbook.test.appmanager;


//import org.openqa.selenium.By;
//import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import sun.plugin2.util.BrowserType;

public class ApplicationManager {
  WebDriver wd;
  private final Properties properties;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupeHelper groupeHelper;
  private ContactHelper contactHelper;
  private String browser;
  private DbHelper dbHeler;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target =  System.getProperty("target" , "local");

    properties.load(new FileReader(new File(String.format("src\\test\\resources\\%s.properties", target))));

    // инициализация связи с бд
    dbHeler = new DbHelper();

    // удаленный запучк
    if ("".equals(properties.getProperty("selenium.server"))){
    // String browser = BrowserType.FIREFOX;
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    }} else {
      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setBrowserName(browser);
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
    }

    // wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    //   wd.get("http://localhost/addressbook/");
    //подстановка из файла
    wd.get(properties.getProperty("web.baseUrl"));
    groupeHelper = new GroupeHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    // login
    // подстановка логина и пароля  напрямую
    // sessionHelper.login("admin", "secret");
    // подстановка логина и пароля  из файла
    sessionHelper.login(properties.getProperty("web.adminLogin"),properties.getProperty("web.adminPassword"));


  }


  public void stop() {
    wd.quit();
  }

 /* private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }*/


  public GroupeHelper groupe() {
    return groupeHelper;
  }

  // выносим возможность читать файл конфига в тестах
  public Properties properties(){
    return properties;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactHelper contacts() {
    return contactHelper;
  }
  public DbHelper db() {return  dbHeler;}
}
