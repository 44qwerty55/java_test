package addressbook.test.appmanager;

import addressbook.test.model.GropeData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupeHelper extends HelperBase {

  public GroupeHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupePage() {
    click(By.linkText("Logout"));
  }

  public void submitGroupeCreation() {
    click(By.name("submit"));
    //  wd.findElement(By.linkText("group page")).click();
  }

  public void fillGroupeForm(GropeData gropeData) {
    type(By.name("group_name"), gropeData.getName());
    type(By.name("group_header"), gropeData.getHeader());
    type(By.name("group_footer"), gropeData.getFooter());
  }

  public void initGroupeCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroupse() {
    click(By.name("delete"));
  }

  public void selectGroupe() {
    click(By.name("selected[]"));
  }

  public void initGropeModification() {
    click(By.name("edit"));
  }

  public void submitGroupeModification() {
    click(By.name("update"));
  }
}
