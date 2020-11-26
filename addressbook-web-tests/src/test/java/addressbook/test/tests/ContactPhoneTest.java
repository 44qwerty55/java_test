package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTest extends TestBase {


  @Test
  public void testContactPhones()  {

    app.goTo().returnToHomePage();
    AddContact contact = app.contacts().all().iterator().next();
    AddContact contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
   // assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
   // assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
  }

  private String mergePhones(AddContact contact) {
  return  Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork()).stream().filter((s) -> ! s.equals(""))
            .map(ContactPhoneTest::cleaned).collect(Collectors.joining("\n"));

  }

  public static String cleaned(String phone){
return phone.replaceAll("\\s","").replaceAll("[-()]]","");
  }

}
