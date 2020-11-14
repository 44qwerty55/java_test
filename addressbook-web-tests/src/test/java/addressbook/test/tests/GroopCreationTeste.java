package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {



    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupeHelper().getGroupCount();
    app.getGroupeHelper().initGroupeCreation();
    app.getGroupeHelper().fillGroupeForm(new GropeData("name1", null, null));
    app.getGroupeHelper().submitGroupeCreation();
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupeHelper().getGroupCount();
 //   app.getGroupeHelper().returnToGroupePage();

    Assert.assertEquals(after, before +1);
  }


}
