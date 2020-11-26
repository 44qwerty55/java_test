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
    // очищаем кэш
    contactCache = null;
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
    type(By.name("home"), addContact.getHome());
    type(By.name("mobile"), addContact.getMobile());
    type(By.name("work"), addContact.getWork());
    type(By.name("email"), addContact.getEmail());
    type(By.name("email2"), addContact.getEmail2());
    type(By.name("email3"), addContact.getEmail3());
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
    // очищаем кэш
    contactCache = null;
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


/*
  или ищем так:
  1) ищем чекбокс контакта
       WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
  2) переходим на два элемента выше
   WebElement row = checkbox.findElement(By.xpath("./../.."));
  3) ищем список всех элементов с тэгои td
  List<WebElement> cels = row.findElements(By.tagName("td"));
  4) внутри этого списка ищем тег с номером 8 (т.к. начинаеться с 0 то ставим 7) и в эотм теге элемент а
  cels.get(7).findElement(By.tagName("a")).click();

  или так:
  ищем все в одну строку последовательно
   wd.findElement(By.xpath(String.format("iinput[value='%s']/../../td[8]/a", id))).click();

  или так:
  ищем  элемент с использованием позаброса, т.е. в котором есть вложенный инпут
    wd.findElement(By.xpath(String.format("//tr[.//input[value='%s']]/td[8]/a", id))).click();

   или так:
   wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']",id))).click();
 */

  }



  public void submiteUpdateContact() {
    //  driver.findElement(By.xpath("(//input[@name='update'])[2]")).click();
    click(By.name("update"));
    // очищаем кэш
    contactCache = null;
  }

  public void createContact(AddContact addContact, boolean b) {
   // app.getNavigationHelper().gotoAddContactPage();
    addContactForm((addContact), true);
    //  app.getContactHelper().addContactFormGroupe(new AddContactGroupe("test_mod"));
    submitNewContact();
    // очищаем кэш
    contactCache = null;
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


  // задаем кэш контакта
  private Contacts contactCache = null;

  public Contacts all() {
    // проверка на наличие кэша перед формированием множества
    if (contactCache != null) {
      return new Contacts(contactCache);
    }

    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
    for (WebElement element : elements) {

      /*
      или можно было изначально получить список всех тэго td
        List<WebElement> cells = element.findElements(By.tagName("td"));
       String lastname = cells.get(1).getText();
       String firstname = cells.get(2).getText();
      */


      String lastname = element.findElement(By.xpath("td[2]")).getText();
      String firstname = element.findElement(By.xpath("td[3]")).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      // split() - разрезать на части строку регуляркой
      String[] phones = allPhones.split("\n");
      contactCache.add(new AddContact().withId(id).withFirstname(firstname).withLastname(lastname).withHome(phones[0]).withMobile(phones[1]).withWork(phones[2]));
    }
    return new Contacts(contactCache);
  }


  public AddContact infoFromEditForm(AddContact contact) {

    modifyContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new AddContact().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withHome(home).withMobile(mobile).withWork(work);

  }
}
