package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddresTest extends TestBase{


  @Test
  public void testContactAddres()  {
    app.goTo().returnToHomePage();
    AddContact contact = app.contacts().all().iterator().next();
    AddContact contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

    System.out.println(contact.getAddress());
    System.out.println(contactInfoFromEditForm.getAddress());


    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

  }


}
