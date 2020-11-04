package addressbook.test;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTeste {
  private WebDriver wd;


  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login(new Login("admin", "secret"));
  }

  private void login(Login login) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(login.getUsername());
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(login.getPassword());
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test

  public void contactCreationTeste() throws Exception {
    gotoAddContactPage();
    addFIO(new AddFioContact("test", "test", "test"));
    addCompany("test company2");
    addEmail("test@test.ru");
    addBirthday("8","July","1990");
    submitNewContact();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitNewContact() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

   private void addBirthday(String bday, String bmonth, String byear) {
   wd.findElement(By.name("bday")).click();
     new Select(wd.findElement(By.name("bday"))).selectByVisibleText(bday);
     wd.findElement(By.name("bday")).click();
     wd.findElement(By.name("bmonth")).click();
     new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(bmonth);
     wd.findElement(By.name("bmonth")).click();
     wd.findElement(By.name("byear")).click();
     wd.findElement(By.name("byear")).clear();
     wd.findElement(By.name("byear")).sendKeys(byear);
  }

 // private void addBirthday() {
  //  wd.findElement(By.name("bday")).click();
 //   new Select(wd.findElement(By.name("bday"))).selectByVisibleText("6");
 //   wd.findElement(By.xpath("//option[@value='6']")).click();
 //   wd.findElement(By.name("bmonth")).click();
 //   new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("July");
 //   wd.findElement(By.xpath("//option[@value='July']")).click();
 //   wd.findElement(By.name("byear")).click();
 //   wd.findElement(By.name("byear")).clear();
 //   wd.findElement(By.name("byear")).sendKeys("1990");
//  }

  private void addEmail(String email) {
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(email);
  }

  private void addCompany(String company) {
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(company);
  }

  private void addFIO(AddFioContact addFioContact) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(addFioContact.getFirstname());
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(addFioContact.getMiddlename());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(addFioContact.getLastname());
  }

  private void gotoAddContactPage() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    wd.quit();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }


}
