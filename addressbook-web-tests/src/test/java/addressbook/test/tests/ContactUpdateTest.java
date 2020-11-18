package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactUpdateTest extends TestBase {

  @Test
  public void testContactUpdate() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    if (!app.getContactHelper().isThereAcontact()) {
      app.getNavigationHelper().gotoAddContactPage();
      app.getContactHelper().createContact(new AddContact("test9", "test1", "test1", "test company3", "test@test.ru", "8", "July", "1990", "test_mod"), true);
    }
    List<AddContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() -1);
    AddContact contact = new AddContact("test_update", "test_update", "test_update", "test_update company3", "test@test.ru", "8", "July", "1990", null);
    app.getContactHelper().addContactForm(contact, false);
    app.getContactHelper().submiteUpdateContact();
    app.getNavigationHelper().returnToHomePage();
    List<AddContact> after = app.getContactHelper().getContactList();

    before.remove(before.size() -1);
    before.add(contact);
    Comparator<? super AddContact> biId = (k1, k2) -> Integer.compare(k1.getId(), k2.getId());
    before.sort(biId);
    after.sort(biId);
  //  Assert.assertEquals(before, after);
    System.out.println(before);
    System.out.println(after);
  }
}
