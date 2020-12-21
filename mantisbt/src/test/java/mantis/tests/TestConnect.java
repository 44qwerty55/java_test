package mantis.tests;

import mantis.model.User;
import mantis.model.Users;
import org.hibernate.Session;
import org.testng.annotations.Test;

import java.util.List;

public class TestConnect extends TestBase{


  @Test(enabled = true)
  public void testHbConnection() {
    String userName = null;
    Integer id = null;
    Users  result = app.db().users();
    for (User user : result)  {
      if (!user.getUsername().equals("administrator"))
        userName = user.getUsername();
      id = user.getId();
      System.out.println("qqq   " + userName + "  id " + id);
      break;
    }
  }




}
