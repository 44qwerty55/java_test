package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeleteTest extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().gotoContactPage();
    if (!app.contacts().isThereAcontact()) {
      app.goTo().gotoAddContactPage();
      app.contacts().createContact(new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1").withCompany("test company3"), true);
    }
    app.goTo().gotoContactPage();

  }


  @Test(enabled = true)
  public void testContactDelete() throws Exception {
    Contacts before = app.contacts().all();
    AddContact deletetContact = before.iterator().next();
    app.contacts().selectContactById(deletetContact.getId());
    app.contacts().deleteContact();
    app.contacts().allertWindow();
    app.goTo().returnToHomePage();
    Contacts after = app.contacts().all();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, CoreMatchers.equalTo(before.withRemove(deletetContact)));

    System.out.println(before);
    System.out.println(after);

  }

}
