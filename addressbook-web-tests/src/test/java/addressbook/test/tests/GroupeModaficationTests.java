package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.annotations.Test;

public class GroupeModaficationTests extends TestBase {

  @Test
  public void testsgroupeModafication() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe();
    app.getGroupeHelper().initGropeModification();
    app.getGroupeHelper().fillGroupeForm(new GropeData("test_mod", "Test_mod", "Test_mod"));
    app.getGroupeHelper().submitGroupeModification();
    app.getGroupeHelper().returnToGroupePage();
  }

}
