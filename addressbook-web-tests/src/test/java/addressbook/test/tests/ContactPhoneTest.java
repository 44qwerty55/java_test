package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {


  @Test
  public void testContactPhones()  {

    app.goTo().returnToHomePage();
    AddContact contact = app.contacts().all().iterator().next();
    AddContact contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

    assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
    assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
  }

  public String cleaned(String phone){
return phone.replaceAll("\\s","").replaceAll("[-()]]","");

  }

}
