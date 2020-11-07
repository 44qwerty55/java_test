package addressbook.test.tests;

import addressbook.test.model.AddContact;
import org.testng.annotations.*;
import org.openqa.selenium.*;


public class ContactCreationTeste extends TestBase {
  private WebDriver wd;


  @Test
  public void contactCreationTeste() throws Exception {
    app.getNavigationHelper().gotoAddContactPage();
    app.getContactHelper().addContactForm(new AddContact("test9", "test1", "test1", "test company3", "test@test.ru", "8", "July", "1990", "name"));
    app.getContactHelper().submitNewContact();
    app.getContactHelper().returnToHomePage();
  }
}









