package addressbook.test.tests;

import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroopCreationTeste extends TestBase {


  // Logger класс org.slf4j   , в скобках указываем класс для логгера. т.е. в котором он работает
 // Logger logger = LoggerFactory.getLogger(GroopCreationTeste.class);

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader("src\\resourses\\g.csv"));
    // читаем строки из файла
    String line = reader.readLine();
    // читаем пока строки не кончаться
    while (line != null) {
      // делем на части каждуюс строку
      String[] split = line.split(";") ;
      // деобаляем в массив
      list.add(new Object[] {new GropeData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();

    }
    return list.iterator();
  }


  @Test(dataProvider = "validGroups")
  public void testGroopCreation(GropeData group) throws Exception {

   // logger.info("start testGroopCreation" );
    app.goTo().groupPage();
    //Set<GropeData> before = app.groupe().all();
    Groups before = app.groupe().all();
    //  int before = app.getGroupeHelper().getGroupCount();

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

    System.out.println(before);
    System.out.println(after);
   // logger.info("stop testGroopCreation" );
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

// негативный тест на создание кривого имени группы
  @Test
  public void testGroopBadCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.groupe().all();
     GropeData group = new GropeData().withName("test2'");
    app.groupe().createGroup(group);
    app.goTo().groupPage();
    // вместо чтения массива просто сравниваем кол-во групп
    assertThat(app.groupe().getGroupCount(), equalTo(before.size()));
     Groups after = app.groupe().all();
   // assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before));
  }

}
