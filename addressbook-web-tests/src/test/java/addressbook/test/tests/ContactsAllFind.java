package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.Groups;
import org.testng.annotations.Test;

import java.util.List;


public class ContactsAllFind extends TestBase {


  @Test(enabled = false)
  public void contactFindTeste() throws Exception {

    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();

  //  List<AddContact> before = app.contacts().getContactList();
 //  System.out.println(before.contains(test9));
  //  System.out.println(before.stream().findAny().equals().getFirstname("test9"));

  //  before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()

  }
  @Test(enabled = true)
  public void cgroupetFindTeste() throws Exception {

    app.goTo().groupPage();
    //Set<GropeData> before = app.groupe().all();
    Groups before = app.groupe().all();
    System.out.println(before);
    String groupee = app.groupe().name("na1");
    System.out.println(groupee);




}



}