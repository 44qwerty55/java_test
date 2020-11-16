package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;

public class GroupeModaficationTests extends TestBase {

  @Test
  public void testsgroupeModafication() {
    app.getNavigationHelper().gotoGroupPage();

    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
    //  before = before +1;
    }
    List<GropeData> before = app.getGroupeHelper().getGroupeList();
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupeHelper().selectGroupe(before.size() -1);
    app.getGroupeHelper().initGropeModification();
    GropeData group = new GropeData(before.get(before.size() -1).getId(),"test_mod", "Test_mod", "Test_mod");
    app.getGroupeHelper().fillGroupeForm(group);
    app.getGroupeHelper().submitGroupeModification();
    app.getNavigationHelper().gotoGroupPage();
    List<GropeData> after = app.getGroupeHelper().getGroupeList();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(before.size() -1);
    before.add(group);
   Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after) );
  }

}
