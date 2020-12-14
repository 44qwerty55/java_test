package mantis.tests;

import mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase{

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession();
    session.login("administration" , "root");
   assertTrue(session.login("administrator" , "root"));
   assertTrue(session.isLoggedInAs("administrator"));
  }
}
