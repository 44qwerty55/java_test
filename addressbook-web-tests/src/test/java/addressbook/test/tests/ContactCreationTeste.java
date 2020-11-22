package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTeste extends TestBase {


  @BeforeMethod
  public void ensurePrecondition() {
    app.goTo().groupPage();

  //  if (app.groupe().all().size() == 0) {
      app.groupe().createGroup(new GropeData().withName("contact_groupe"));
      //  before = before +1;
  //  }
  }

  @Test(enabled = true)
  public void contactCreationTeste() throws Exception {

    app.goTo().returnToHomePage();
    List<AddContact> before = app.getContactHelper().getContactList();
    app.goTo().gotoAddContactPage();
    AddContact contact = new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1").withCompany("test company3").withEmail("test@test.ru").withBday("8").withBmonth("July").withByear("1990").withGroup("contact_groupe");
    app.getContactHelper().addContactForm((contact), true);
    app.getContactHelper().submitNewContact();
    app.goTo().returnToHomePage();
    List<AddContact> after = app.getContactHelper().getContactList();

    before.add(contact);
    Comparator<? super AddContact> biId = (k1, k2) -> Integer.compare(k1.getId(), k2.getId());
    before.sort(biId);
    after.sort(biId);

    Assert.assertEquals(before, after);


    System.out.println(before);
   System.out.println(after);
  }
}









