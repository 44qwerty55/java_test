package addressbook.test.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupDeletetion() throws Exception {
    app.gotoGroupPage();
    app.selectGroupe();
    app.deleteSelectedGroupse();
    app.returnToGroupePage();
  }


}
