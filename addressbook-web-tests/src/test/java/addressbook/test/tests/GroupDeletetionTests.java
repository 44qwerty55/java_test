package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupDeletetion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupeHelper().getGroupCount();
    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
      before = before +1;
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe();
    app.getGroupeHelper().deleteSelectedGroupse();
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupeHelper().getGroupCount();
    Assert.assertEquals(after, before -1);
  }


}

