package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.annotations.Test;

public class ContactUpdateTest extends TestBase {

  @Test
  public void testContactUpdate() throws Exception {

    app.getNavigationHelper().gotoContactPage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.getNavigationHelper().gotoAddContactPage();
      app.getContactHelper().createContact(new AddContact("test9", "test1", "test1", "test company3", "test@test.ru", "8", "July", "1990","test_mod"), true);
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().editSelectedContact();
    app.getContactHelper().addContactForm(new AddContact("test_update", "test_update", "test_update", "test_update company3", "test@test.ru", "8", "July", "1990", null) , false);
    app.getContactHelper().submiteUpdateContact();
    app.getContactHelper().returnToHomePage();

  }
}
