package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {


    app.getNavigationHelper().gotoGroupPage();
    List<GropeData> before = app.getGroupeHelper().getGroupeList();
    //  int before = app.getGroupeHelper().getGroupCount();
    app.getGroupeHelper().initGroupeCreation();
    GropeData group = new GropeData("name1", "name1", "name1");
    app.getGroupeHelper().fillGroupeForm(group);
    app.getGroupeHelper().submitGroupeCreation();
    app.getNavigationHelper().gotoGroupPage();
    List<GropeData> after = app.getGroupeHelper().getGroupeList();
    //   int after = app.getGroupeHelper().getGroupCount();
    //   app.getGroupeHelper().returnToGroupePage();

    //   Assert.assertEquals(after.size(), before.size() +1);


/*int max = 0;
for (GropeData g : after) {
  if (g.getId() > max) {
    max = g.getId();
  }
}
*/

//   Comparator<? super GropeData> byId = (Comparator<GropeData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
//  int max1 =  after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

//group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);
    Comparator<? super GropeData> biId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(biId);
    after.sort(biId);

    Assert.assertEquals(before, after);
    System.out.println(before);
    System.out.println(after);
  }


}
