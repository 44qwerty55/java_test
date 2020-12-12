package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContacToGroupe extends TestBase{

  // переменная с название группы
  private String groupe;

  @BeforeMethod
  public void ensurePrecondition() {
    // берем название группы из файла конфигурации
   String groupToAdd = app.properties().getProperty("groupToadd");
    groupe = groupToAdd;
    // проверка на наличие контакта
    if (app.db().contacts().size() == 0)
    {
      app.goTo().gotoAddContactPage();
      app.contacts().addContactFormFIO(new AddContact().withFirstname("test9").withLastname("test1"));
      app.contacts().submitNewContact();
    }
    // проверка на наличие группы
    Groups result = app.db().groups();
    String contactGrroup = null;
    for (GropeData group : result) {

      if (group.getName().equals(groupToAdd)) {
        contactGrroup = group.getName();
      }
    }
    if (contactGrroup == null) {
      app.goTo().groupPage();
      // если поиск по бд с названием группы из конфига дал null добавляем группу с название из конфига
      app.groupe().createGroup(new GropeData().withName(groupToAdd));
    }


  }



  @Test(enabled = true)
  public void addContactToGroupe() throws Exception {
    Groups beforeGroups = app.db().groups();
    app.goTo().gotoContactPage();
    Contacts before = app.db().contacts();
    AddContact addContactToGroupe = before.iterator().next();
    app.contacts().selectContactById(addContactToGroupe.getId());
    app.contacts().addContactTo(groupe);
    app.contacts().submitAddToGroupeContact();
  }


}
