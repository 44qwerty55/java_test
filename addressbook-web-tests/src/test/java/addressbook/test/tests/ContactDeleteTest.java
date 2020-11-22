package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeleteTest extends TestBase{

  @Test(enabled = true)
  public void testContactDelete() throws Exception {
    app.goTo().gotoContactPage();
    if (! app.getContactHelper().isThereAcontact()) {
      app.goTo().gotoAddContactPage();
      app.getContactHelper().createContact(new AddContact().withFirstname("testt_ypdate").withLastname("test_update"), true);;
    app.goTo().returnToHomePage();
    }
    List<AddContact> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() -1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().allertWindow();
    app.goTo().returnToHomePage();
      List<AddContact> after = app.getContactHelper().getContactList();

    before.remove(before.size() -1);
    Assert.assertEquals(after, before);


    System.out.println(before);
    System.out.println(after);

  }

}
