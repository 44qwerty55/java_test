package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GroupeModaficationTests extends TestBase {

  @Test
  public void testsgroupeModafication() {
    app.getNavigationHelper().gotoGroupPage();
    int before = app.getGroupeHelper().getGroupCount();
    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
      before = before +1;
    }
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe();
    app.getGroupeHelper().initGropeModification();
    app.getGroupeHelper().fillGroupeForm(new GropeData("test_mod", "Test_mod", "Test_mod"));
    app.getGroupeHelper().submitGroupeModification();
    app.getNavigationHelper().gotoGroupPage();
    int after = app.getGroupeHelper().getGroupCount();
    Assert.assertEquals(after, before );

  }

}
