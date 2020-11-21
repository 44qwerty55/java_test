package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class GroupeModaficationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.groupe().all().size() == 0) {
      app.groupe().createGroup(new GropeData().withName("test1"));
      //  before = before +1;
    }
  }

  @Test
  public void testsgroupeModafication() {

    Set<GropeData> before = app.groupe().all();
    GropeData modifiedGroup = before.iterator().next();
    GropeData group = new GropeData().withId(modifiedGroup.getId()).withName("test3").withFooter("test3").withHeader("test3");
    app.groupe().modifyGroup(group);
    app.goTo().groupPage();
    Set<GropeData> after = app.groupe().all();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(modifiedGroup);
    before.add(group);
   Assert.assertEquals(before, after);
  }



}
