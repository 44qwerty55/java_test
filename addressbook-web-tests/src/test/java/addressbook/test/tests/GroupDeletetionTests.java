package addressbook.test.tests;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupDeletetion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe();
    app.getGroupeHelper().deleteSelectedGroupse();
    app.getGroupeHelper().returnToGroupePage();
  }


}
