package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.annotations.*;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().initGroupeCreation();
    app.getGroupeHelper().fillGroupeForm(new GropeData("name1", null, null));
    app.getGroupeHelper().submitGroupeCreation();
    app.getGroupeHelper().returnToGroupePage();
  }


}
