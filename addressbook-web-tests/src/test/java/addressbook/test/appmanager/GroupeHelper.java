package addressbook.test.appmanager;

import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
    // очищаем кэш
    groupCache = null;
  }

  public void selectGroupe(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    // click(By.name("selected[]"));
  }


  public void selectGroupeById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    // click(By.name("selected[]"));
  }


  public void initGropeModification() {


    click(By.name("edit"));
    // очищаем кэш
    groupCache = null;
  }

  public void submitGroupeModification() {
    click(By.name("update"));
  }

  public void createGroup(GropeData group) {

    initGroupeCreation();
    fillGroupeForm(group);
    submitGroupeCreation();
    // очищаем кэш
    groupCache = null;
    //  returnToGroupePage();
  }


  public void modifyGroup(GropeData group) {
    selectGroupeById(group.getId());
    initGropeModification();
    fillGroupeForm(group);

    submitGroupeModification();
    // очищаем кэш
    groupCache = null;

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
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //  GropeData group = new GropeData().withId(id).withName(name);
      groups.add(new GropeData().withId(id).withName(name));
    }
    return groups;
  }
// поиск группы по имени
  public String name(String nameGrope) {
   // List<GropeData> groups = new ArrayList<GropeData>();
    String nameq = "null";
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();

      if (name.equals(nameGrope)) {
        nameq = name;
      }
    }
    return nameq;
  }
// задаем кэш групп
 private Groups groupCache = null;



  // метод возвращает множество данных по группе   ориентируясь на тег "span.group"
  // public Set<GropeData> all() {
  public Groups all() {

    // проверка на наличие кэша перед формированием множества
    if (groupCache != null) {
      return new Groups(groupCache);
    }

    //Set<GropeData> groups = new HashSet<GropeData>();
    groupCache = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      //  GropeData group = new GropeData().withId(id).withName(name);
      groupCache.add(new GropeData().withId(id).withName(name));
    }
    // возвращаем не сам кеш groupCache а копию его  new Groups(groupCache);
    return new Groups(groupCache);
  }


  public void delete(GropeData groupe) {
    selectGroupeById(groupe.getId());
    deleteSelectedGroupse();
  }
}
