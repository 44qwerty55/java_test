package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupDeletetion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe();
    app.getGroupeHelper().deleteSelectedGroupse();
    app.getGroupeHelper().returnToGroupePage();
  }


}
