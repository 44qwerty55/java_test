package addressbook.test.appmanager;

import addressbook.test.model.Login;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {


  private WebDriver wd;


  public SessionHelper(WebDriver wd) {
    this.wd = wd;
  }


  public void login(Login login) {
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(login.getUsername());
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(login.getPassword());
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
}
