package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContacEmailTest extends TestBase{


  @Test
  public void testContactEmail()  {
    app.goTo().returnToHomePage();
    AddContact contact = app.contacts().all().iterator().next();
    AddContact contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmail(contactInfoFromEditForm)));

  }

  private String mergeEmail(AddContact contact) {
    return  Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));

  }


}
