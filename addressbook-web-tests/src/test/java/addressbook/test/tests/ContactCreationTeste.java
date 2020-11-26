package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTeste extends TestBase {


  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().groupPage();
    String groupee = app.groupe().name("contact_groupe");
    if (groupee.equals("null")) {
      app.groupe().createGroup(new GropeData().withName("contact_groupe"));
    }

  }


  @Test(enabled = true)
  public void contactCreationTeste() throws Exception {

    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();
    app.goTo().gotoAddContactPage();
    AddContact contact = new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1").withCompany("test company3").withHome("11").withMobile("22").withWork("333").withEmail("test@test.ru").withBday("8").withBmonth("July").withByear("1990").withGroup("contact_groupe");
    app.contacts().addContactForm((contact), true);
    app.contacts().submitNewContact();
    app.goTo().returnToHomePage();
    Contacts after = app.contacts().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


    System.out.println(before);
    System.out.println(after);
  }
}









