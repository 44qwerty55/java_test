package addressbook.test.appmanager;

import addressbook.test.model.GropeData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

  public void selectGroupe(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
  }
  public void selectGroupeById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id +"']")).click();
    // click(By.name("selected[]"));
  }

  public void initGropeModification() {
    click(By.name("edit"));
  }

  public void submitGroupeModification() {
    click(By.name("update"));
  }

  public void createGroup(GropeData group) {
   initGroupeCreation();
    fillGroupeForm(group);
    submitGroupeCreation();
  //  returnToGroupePage();
  }


  public void modifyGroup(GropeData group) {
    selectGroupeById(group.getId());
   initGropeModification();
    fillGroupeForm(group);
    submitGroupeModification();

  }
  /* метод для изменению по индексу
   public void modifyGroup(int index, GropeData group) {
    selectGroupe(index);
   initGropeModification();
    fillGroupeForm(group);
    submitGroupeModification();

  }

   */

  public void deleteGroupe(int index) {
    selectGroupe(index);
    deleteSelectedGroupse();
    }


  public boolean isThereAgroupe() {
    return isElementPresent(By.name("selected[]"));


  }

  public int getGroupCount() {
   return wd.findElements(By.name("selected[]")).size();
  }

  // метод возвращает список данных по группе ориентируясь на тег "span.group"
  public List<GropeData> list() {
    List<GropeData> groups = new ArrayList<GropeData>();
    List<WebElement>  elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
    //  GropeData group = new GropeData().withId(id).withName(name);
      groups.add(new GropeData().withId(id).withName(name));
    }
    return groups;
  }

  // метод возвращает множество данных по группе   ориентируясь на тег "span.group"
  public Set<GropeData> all() {
  Set<GropeData> groups = new HashSet<GropeData>();
    List<WebElement>  elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements){
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //  GropeData group = new GropeData().withId(id).withName(name);
      groups.add(new GropeData().withId(id).withName(name));
    }
    return groups;
  }


  public void delete(GropeData groupe) {
    selectGroupeById(groupe.getId());
    deleteSelectedGroupse();
  }
}
