package addressbook.test.appmanager;

import addressbook.test.model.GropeData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupeHelper {
  private WebDriver wd;

  public GroupeHelper(WebDriver wd) {
    this.wd =wd;
  }

  public void returnToGroupePage() {
    wd.findElement(By.linkText("Logout")).click();
  }

  public void submitGroupeCreation() {
    wd.findElement(By.name("submit")).click();
    wd.findElement(By.linkText("group page")).click();
  }

  public void fillGroupeForm(GropeData gropeData) {
    wd.findElement(By.name("group_name")).click();
    wd.findElement(By.name("group_name")).clear();
    wd.findElement(By.name("group_name")).sendKeys(gropeData.getName());
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).clear();
    wd.findElement(By.name("group_header")).sendKeys(gropeData.getHeader());
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).clear();
    wd.findElement(By.name("group_footer")).sendKeys(gropeData.getFooter());
  }

  public void initGroupeCreation() {
    wd.findElement(By.name("new")).click();
  }

  public void deleteSelectedGroupse() {
    wd.findElement(By.name("delete")).click();
  }

  public void selectGroupe() {
    wd.findElement(By.name("selected[]")).click();
  }
}
