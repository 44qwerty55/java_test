package addressbook.test.appmanager;

import addressbook.test.model.AddContact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactHelper extends HelperBase {
  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void submitNewContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  //  wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

  public void addContactForm(AddContact addContact) {
    type(By.name("firstname"),addContact.getFirstname());
    type(By.name("middlename"),addContact.getMiddlename());
    type(By.name("lastname"),addContact.getLastname());
    type(By.name("company"),addContact.getCompany());
    type(By.name("email"),addContact.getEmail());
    select(By.name("bday"),addContact.getBday() );
    select(By.name("bmonth"),addContact.getBmonth() );
    type(By.name("byear"),addContact.getByear());
    select(By.name("new_group"),addContact.getNew_group() );

  }

}
