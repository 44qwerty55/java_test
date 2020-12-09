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
    if (app.db().contacts().size() == 0)
    {
      app.goTo().gotoAddContactPage();
      app.contacts().addContactFormFIO(new AddContact().withFirstname("test9").withLastname("test1"));
      app.contacts().submitNewContact();
    }

  }


  @Test(enabled = true)
  public void testContactDelete() throws Exception {
    Contacts before = app.db().contacts();
    app.goTo().returnToHomePage();
    AddContact deletetContact = before.iterator().next();
    app.contacts().selectContactById(deletetContact.getId());
    app.contacts().deleteContact();
    app.contacts().allertWindow();
    app.goTo().returnToHomePage();
    Contacts after = app.db().contacts();
    assertEquals(after.size(), before.size() - 1);
    assertThat(after, CoreMatchers.equalTo(before.withRemove(deletetContact)));

  }

}
