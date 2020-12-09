package addressbook.test.tests;

import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupeModaficationTests extends TestBase {

 // проверка с условием чтения групп из БД
 @BeforeMethod
 public void ensurePrecondition() {
   if (app.db().groups().size() == 0) {
     app.goTo().groupPage();
     app.groupe().createGroup(new GropeData().withName("test1"));
   }
 }

  /*  проверка с условием чтение групп из веба
  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.groupe().all().size() == 0) {
      app.groupe().createGroup(new GropeData().withName("test1"));
      //  before = before +1;
    }
  }
*/
  // получение списка групп из bd
  @Test(enabled = true)
  public void testsGroupeModaficationBd() {

    Groups before = app.db().groups();
    GropeData modifiedGroup = before.iterator().next();
    GropeData group = new GropeData().withId(modifiedGroup.getId()).withName("test3").withFooter("test3").withHeader("test3");
    app.goTo().groupPage();
    app.groupe().modifyGroup(group);
    app.goTo().groupPage();
    // быстрая проверка счетчик групп, хеширование через веб
    assertThat(app.groupe().getGroupCount(), equalTo(before.size()));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }



  // получение списка групп из веб для работы надо перегенерить метод сравнения в GropeData  equals и hashCode на те значения которые отображаються в веб интерфейсе
  @Test(enabled = false)
  public void testsGroupeModafication() {
    app.goTo().groupPage();
   Groups before = app.groupe().all();
    GropeData modifiedGroup = before.iterator().next();
    GropeData group = new GropeData().withId(modifiedGroup.getId()).withName("test3").withFooter("test3").withHeader("test3");
    app.groupe().modifyGroup(group);
    app.goTo().groupPage();
  // быстрая проверка счетчик групп, хеширование
assertThat(app.groupe().getGroupCount(), equalTo(before.size()));
    Groups after = app.groupe().all();
  //  Assert.assertEquals(after.size(), before.size() );
 //   before.remove(modifiedGroup);
  //  before.add(group);
  // Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
  }



}
