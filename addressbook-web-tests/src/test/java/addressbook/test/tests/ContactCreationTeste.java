package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.GropeData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;


public class ContactCreationTeste extends TestBase {


  @Test(enabled = false)
  public void contactCreationTeste() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupeHelper().isThereAgroupe()) {
      app.getGroupeHelper().createGroup(new GropeData("name1", null, null));
    }
    app.getNavigationHelper().returnToHomePage();
    List<AddContact> before = app.getContactHelper().getContactList();
    app.getNavigationHelper().gotoAddContactPage();
    AddContact contact = new AddContact("test9", "test1", "test1", "test company3", "test@test.ru", "8", "July", "1990", "name1");
    app.getContactHelper().addContactForm((contact), true);
    app.getContactHelper().submitNewContact();
    app.getNavigationHelper().returnToHomePage();
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









