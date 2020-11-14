package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {



    app.getNavigationHelper().gotoGroupPage();
    List<GropeData> before = app.getGroupeHelper().getGroupeList();
  //  int before = app.getGroupeHelper().getGroupCount();
    app.getGroupeHelper().initGroupeCreation();
    app.getGroupeHelper().fillGroupeForm(new GropeData("name1", null, null));
    app.getGroupeHelper().submitGroupeCreation();
    app.getNavigationHelper().gotoGroupPage();
    List<GropeData> after = app.getGroupeHelper().getGroupeList();
 //   int after = app.getGroupeHelper().getGroupCount();
 //   app.getGroupeHelper().returnToGroupePage();

    Assert.assertEquals(after.size(), before.size() +1);
  }


}
