package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupeModaficationTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.groupe().list().size() == 0) {
      app.groupe().createGroup(new GropeData().withName("test1"));
      //  before = before +1;
    }
  }

  @Test
  public void testsgroupeModafication() {

    List<GropeData> before = app.groupe().list();
    int index = before.size() -1;
    GropeData group = new GropeData().withId(before.get(index).getId()).withName("test3").withFooter("test3").withHeader("test3");
    app.groupe().modifyGroup(index, group);
    app.goTo().groupPage();
    List<GropeData> after = app.groupe().list();
    Assert.assertEquals(after.size(), before.size() );

    before.remove(index);
    before.add(group);
    Comparator<? super GropeData> biId = (g1 , g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(biId);
    after.sort(biId);
   Assert.assertEquals(before, after);
  }



}
