package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.annotations.Test;

import java.util.List;


public class ContactsAllFind extends TestBase {


  @Test
  public void contactFindTeste() throws Exception {

    app.getNavigationHelper().returnToHomePage();
    List<AddContact> before = app.getContactHelper().getContactList();
    System.out.println(before);
  }
}