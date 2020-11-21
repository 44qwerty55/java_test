package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.annotations.Test;

import java.util.List;


public class ContactsAllFind extends TestBase {


  @Test(enabled = false)
  public void contactFindTeste() throws Exception {

    app.goTo().returnToHomePage();
    List<AddContact> before = app.getContactHelper().getContactList();
    System.out.println(before);
  }
}