package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactDeleteTest extends TestBase{

  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.getNavigationHelper().gotoAddContactPage();
      app.getContactHelper().createContact(new AddContact("test9", "test1", "test1", "test company3", "test@test.ru", "8", "July", "1990","test_mod"), true);
    app.getNavigationHelper().returnToHomePage();
    }
    List<AddContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().allertWindow();
    app.getNavigationHelper().returnToHomePage();
      List<AddContact> after = app.getContactHelper().getContactList();

    before.remove(before.size() -1);
    Assert.assertEquals(after, before);


    System.out.println(before);
    System.out.println(after);

  }

}
