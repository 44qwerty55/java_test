package mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  private final ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    // инициализация в AplicationManager напрямую
    //wd = app.wd;
    // инициализация в момент обращения
    wd = app.getDriver();
  }

  public void start(String username, String email) {
    // signup_page.php страница регистрации пользоваетля
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
