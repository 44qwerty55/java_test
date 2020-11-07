package addressbook.test;

import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupDeletetion() throws Exception {
    gotoGroupPage();
    selectGroupe();
    deleteSelectedGroupse();
    returnToGroupePage();
  }


}
