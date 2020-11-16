package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;

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

    Assert.assertEquals(after.size(), before.size() +1);


int max = 0;
for (GropeData g : after) {
  if (g.getId() > max) {
    max = g.getId();
  }
}
group.setId(max);
    before.add(group);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after) );
  }


}
