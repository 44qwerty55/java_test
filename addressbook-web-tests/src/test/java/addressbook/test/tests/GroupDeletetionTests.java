package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.List;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.groupe().list().size() == 0) {
      app.groupe().createGroup(new GropeData().withName("test1"));
      //  before = before +1;
    }
  }

  @Test
  public void testGroupDeletetion() throws Exception {

   // int before = app.getGroupeHelper().getGroupCount();
    List<GropeData> before = app.groupe().list();
    int index = before.size() -1;
    app.groupe().deleteGroupe(index);
    app.goTo().groupPage();
    // int after = app.getGroupeHelper().getGroupCount();
    List<GropeData> after = app.groupe().list();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(index);
    Assert.assertEquals(after, before);
  //  for (int i =0; i< after.size(); i++){
  //    Assert.assertEquals(after.get(i), before.get(i));
  //  }
  }



}

