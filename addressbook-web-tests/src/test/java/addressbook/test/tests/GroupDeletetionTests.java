package addressbook.test.tests;

import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Set;


public class GroupDeletetionTests extends TestBase {
  private WebDriver wd;

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();
    if (app.groupe().all().size() == 0) {
      app.groupe().createGroup(new GropeData().withName("test1"));
      //  before = before +1;
    }
  }

  @Test
  public void testGroupDeletetion() throws Exception {

   // int before = app.getGroupeHelper().getGroupCount();
    Set<GropeData> before = app.groupe().all();
    // выбираем группу, метод iterator последовательно перебирает элементы множества и next берет следующий элемент
    GropeData deletedGroupe = before.iterator().next();
   // int index = before.size() -1;

     app.groupe().delete(deletedGroupe);
    app.goTo().groupPage();
    // int after = app.getGroupeHelper().getGroupCount();
    Set<GropeData> after = app.groupe().all();
    Assert.assertEquals(after.size(), before.size() -1);
    before.remove(deletedGroupe);
    Assert.assertEquals(after, before);
  //  for (int i =0; i< after.size(); i++){
  //    Assert.assertEquals(after.get(i), before.get(i));
  //  }
  }



}

