package mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTest extends TestBase{


  @Test
  public void  testRegistration() {
    // непосредственное обращение к браузеру registration()
app.registration().start("user1" ,"user1@localhost.localdomain");
  }
}
