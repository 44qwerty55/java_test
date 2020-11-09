package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.AddContactGroupe;
import addressbook.test.model.GropeData;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class ContactCreationTeste extends TestBase {



  @Test
  public void contactCreationTeste() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
    }
    app.getNavigationHelper().gotoAddContactPage();
    app.getContactHelper().addContactForm(new AddContact("test9", "test1", "test1", "test company3", "test@test.ru", "8", "July", "1990","name1"), true);
 //  app.getContactHelper().addContactFormGroupe(new AddContactGroupe("test_mod"));
    app.getContactHelper().submitNewContact();
    app.getContactHelper().returnToHomePage();
  }
}









