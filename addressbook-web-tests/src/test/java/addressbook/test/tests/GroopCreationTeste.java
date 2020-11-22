package addressbook.test.tests;

import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {
    app.goTo().groupPage();
    //Set<GropeData> before = app.groupe().all();
    Groups before = app.groupe().all();
    //  int before = app.getGroupeHelper().getGroupCount();
    GropeData group = new GropeData().withName("test2");
    app.groupe().createGroup(group);
    app.goTo().groupPage();
    //Set<GropeData> after = app.groupe().all();
    Groups after = app.groupe().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    //Assert.assertEquals(after.size(), before.size() +1);
    // превращаем after в поток объектов (GropeData) stream и  превращаем его в поток идентификаторов mapToInt  и выбираем максимальное значение max и перобразуем в обычное целое число getAsInt
   // group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
   // before.add(group);
   // Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
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


    /*  сортировака списков
     List<GropeData> before = app.groupe().list();
       List<GropeData> after = app.groupe().list();
       before.add(group);
    Comparator<? super GropeData> biId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(biId);
    after.sort(biId);

    Assert.assertEquals(before, after);
    System.out.println(before);
    System.out.println(after);
  */
  }

}
