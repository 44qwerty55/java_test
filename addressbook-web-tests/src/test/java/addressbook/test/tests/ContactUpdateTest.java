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
    app.goTo().gotoContactPage();
    if (!app.contacts().isThereAcontact()) {
      app.goTo().gotoAddContactPage();
      app.contacts().createContact(new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1").withCompany("test company3"), true);
    }

  }


  @Test(enabled = true)
  public void testContactUpdate() throws Exception {

    Contacts before = app.contacts().all();

    AddContact modifineContact = before.iterator().next();
    app.contacts().modifyContactById(modifineContact.getId());
    AddContact contact = new AddContact().withId(modifineContact.getId()).withFirstname("testt_ypdate").withLastname("test_update");
    app.contacts().addContactFormFIO(contact);
    app.contacts().submiteUpdateContact();
    app.goTo().returnToHomePage();

    Contacts after = app.contacts().all();
    Assert.assertEquals(after.size(), before.size());
    assertThat(after, equalTo(before.withRemove(modifineContact).withAdded(contact)));

    System.out.println(before);
    System.out.println(after);
  }
}
