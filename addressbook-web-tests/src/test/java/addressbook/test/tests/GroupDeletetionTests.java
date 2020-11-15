package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.List;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;


  @Test
  public void testGroupDeletetion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
   // int before = app.getGroupeHelper().getGroupCount();
    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
   //   before = before +1;
    }
    List<GropeData> before = app.getGroupeHelper().getGroupeList();
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe(before.size() -1);
    app.getGroupeHelper().deleteSelectedGroupse();
    app.getNavigationHelper().gotoGroupPage();
   // int after = app.getGroupeHelper().getGroupCount();
    List<GropeData> after = app.getGroupeHelper().getGroupeList();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(before.size() -1);
    Assert.assertEquals(after, before);
  //  for (int i =0; i< after.size(); i++){
  //    Assert.assertEquals(after.get(i), before.get(i));
  //  }
  }


}

