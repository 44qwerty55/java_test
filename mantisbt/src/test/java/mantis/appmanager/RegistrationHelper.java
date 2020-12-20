package mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationHelper extends HelperBase{
 // private final ApplicationManager app;
//  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    // конструктор базового класса HelperBase
    super(app);
 //   this.app = app;
    // инициализация в AplicationManager напрямую
    //wd = app.wd;
    // инициализация в момент обращения
 //   wd = app.getDriver();
  }

  public void start(String username, String email) {
    // signup_page.php страница регистрации пользоваетля
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
   type(By.name("username"),username);
   type(By.name("email"),email);
 //   click(By.cssSelector("input[value='Signup']"));
    click(By.cssSelector("input[type='submit']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("span[class='bigger-110']"));
   // click(By.xpath("//input[@value='Signup']"));
    //click(By.cssSelector(".btn-success"));
  }



  public void resetPassword(String user){
   click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/i"));
    click(By.linkText("Manage Users"));
    click(By.linkText(user));
    click(By.xpath("//input[@value='Reset Password']"));
  }

  public void login (String username, String password)
  {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.id("username"), username);
    click(By.xpath("//input[@value='Login']"));
    type(By.id("password"), password);
    click(By.xpath("//input[@value='Login']"));
  }

}
