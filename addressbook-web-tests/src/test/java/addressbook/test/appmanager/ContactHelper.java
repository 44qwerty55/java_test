package addressbook.test.appmanager;

import addressbook.test.model.AddContact;
import addressbook.test.model.AddContactGroupe;
import addressbook.test.model.GropeData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }



  public void submitNewContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
    //  wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  public void addContactForm(AddContact addContact , boolean creation) {
    type(By.name("firstname"), addContact.getFirstname());
    type(By.name("middlename"), addContact.getMiddlename());
    type(By.name("lastname"), addContact.getLastname());
    type(By.name("company"), addContact.getCompany());
    type(By.name("email"), addContact.getEmail());
    select(By.name("bday"), addContact.getBday());
    select(By.name("bmonth"), addContact.getBmonth());
    type(By.name("byear"), addContact.getByear());


    if (creation) {
      select(By.name("new_group"), addContact.getNew_group());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  //  select(By.name("new_group"), addContact.getNew_group());

  }

  public void addContactFormGroupe(AddContactGroupe addContact) {
    select(By.name("new_group"), addContact.getNew_group());
  }

  public void deleteContact() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();

  }

  public void selectContact(int index) {

    //  click(By.id("4"));
    click(By.name("selected[]"));
    // click(By.id("MassCB"));
  }

  public void editSelectedContact() {
    click(By.xpath("//img[@alt='Edit']"));
    // driver.findElement(By.xpath("//img[@alt='Edit']")).click();
  }

  public void submiteUpdateContact() {
    //  driver.findElement(By.xpath("(//input[@name='update'])[2]")).click();
    click(By.name("update"));
  }

  public void createContact(AddContact addContact, boolean b) {
   // app.getNavigationHelper().gotoAddContactPage();
    addContactForm((addContact), true);
    //  app.getContactHelper().addContactFormGroupe(new AddContactGroupe("test_mod"));
    submitNewContact();
    //returnToHomePage();

  }

  public boolean isThereAcontact() {
   return isElementPresent(By.name("selected[]"));
  }


  public List<AddContact> getContactList() {
List<AddContact> contacts = new ArrayList<AddContact>();
List<WebElement>  elements = wd.findElements(By.xpath("//tr[@name='entry']" ));
    for (WebElement element : elements){

      String lastname = element.findElement(By.xpath("//td[2]")).getText();
      String firstname = element.findElement(By.xpath("//td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      AddContact contact = new AddContact(id, firstname,lastname);
      //AddContact contact = new AddContact(firstname, null , lastname, null,null,null,null,null,null);
      contacts.add(contact);
    }
return contacts;

  }
}
