package addressbook.test.appmanager;

import addressbook.test.model.*;
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


  public void addContactFormFIO (AddContact addContact ){
    type(By.name("firstname"), addContact.getFirstname());
    type(By.name("lastname"), addContact.getLastname());
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
   // wd.switchTo().alert().accept();
   }
  public void allertWindow() {
    wd.switchTo().alert().accept();
  }

  public void selectContact(int index) {

    //  click(By.id("4"));
    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
    // click(By.id("MassCB"));
  }
  public void selectContactById(int id) {

    wd.findElement(By.cssSelector("input[id='"+ id +"']")).click();
    // click(By.name("selected[]"));
    // click(By.id("MassCB"));
  }


  public void editContact(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
  //  wd.findElements(By.name("//img[@alt='Edit']")).get(index).click();
    // click(By.xpath("//img[@alt='Edit']"));
  }


  public void modifyContactById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+ id +"'] img[alt='Edit']")).click();
    // click(By.name("selected[]"));  By.cssSelector("href='edit.php?id=" + id +"' img[@alt='Edit']")
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
//List<WebElement>  elements = wd.findElements(By.xpath("//tr[@name='entry']" ));

    List<WebElement>  elements = wd.findElements(By.xpath("//tr[@name='entry']" ));
    for (WebElement element : elements){

      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      AddContact contact = new AddContact().withId(id).withFirstname(firstname).withLastname(lastname);
      //AddContact contact = new AddContact(firstname, null , lastname, null,null,null,null,null,null);
      contacts.add(contact);
    }
return contacts;

  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {

      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new AddContact().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }


}
