package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactUpdateTest extends TestBase {

  @Test(enabled = true)
  public void testContactUpdate() throws Exception {

    app.goTo().gotoContactPage();
    if (!app.getContactHelper().isThereAcontact()) {
      app.goTo().gotoAddContactPage();
      app.getContactHelper().createContact(new  AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1").withCompany("test company3"), true);
    }
    List<AddContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().editContact(before.size() - 1);
    AddContact contact = new AddContact().withId(before.size() - 1).withFirstname("testt_ypdate").withLastname("test_update");
    //AddContact contact = new AddContact(before.get(before.size() - 1).getId(), "testt_ypdate", "test_update");
    //AddContact contact = new AddContact(  "test_update", null, "test_update", null, null, null, null, null, null);
    // app.getContactHelper().addContactForm(contact, false);
    app.getContactHelper().addContactFormFIO(contact);
    app.getContactHelper().submiteUpdateContact();
    app.goTo().returnToHomePage();
    List<AddContact> after = app.getContactHelper().getContactList();

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super AddContact> biId = (k1, k2) -> Integer.compare(k1.getId(), k2.getId());
    before.sort(biId);
    after.sort(biId);
    Assert.assertEquals(before, after);
    System.out.println(before);
    System.out.println(after);
  }
}
