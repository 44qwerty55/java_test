package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactUpdateTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    if (app.db().contacts().size() == 0)
  {
      app.goTo().gotoAddContactPage();
      app.contacts().addContactFormFIO(new AddContact().withFirstname("test9").withLastname("test1"));
      app.contacts().submitNewContact();
    }

  }
/* проверка через веб
  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().gotoContactPage();
    if (!app.contacts().isThereAcontact()) {
      app.goTo().gotoAddContactPage();
      app.contacts().addContactFormFIO(new AddContact().withFirstname("test9").withLastname("test1"));
      app.contacts().submitNewContact();
      app.goTo().returnToHomePage();
    }
  }
*/


  @Test(enabled = true)
  public void testContactUpdate() throws Exception {

    Contacts before = app.db().contacts();
    app.goTo().gotoContactPage();
    AddContact modifineContact = before.iterator().next();
    app.contacts().modifyContactById(modifineContact.getId());
    AddContact contact = new AddContact().withId(modifineContact.getId()).withFirstname("contact_update")
            .withLastname("contact_update").withMiddlename("contact_update")
            .withCompany("test contact_update").withHome("11").withMobile("22").withWork("333").withAddress("update")
            .withEmail("update@test.ru").withBday("8").withBmonth("July")
            .withByear("1990");


    app.contacts().addContactForm((contact), false , false);
    app.contacts().submiteUpdateContact();
    Contacts after = app.db().contacts();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withRemove(modifineContact).withAdded(contact)));

  }
}
