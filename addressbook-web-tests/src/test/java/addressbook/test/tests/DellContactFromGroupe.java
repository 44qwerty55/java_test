package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DellContactFromGroupe extends TestBase{


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


      Groups resultcon = app.db().groups();
      String contactWithGrroup = "true";
      for (GropeData groupsee : resultcon) {
       if (groupsee.getName().equals(groupToAdd) ){
        if (groupsee.contactIngroupe().isEmpty()) {
          contactWithGrroup = null;
        }
      }}
      if (contactWithGrroup == null) {
               app.goTo().gotoContactPage();
        Contacts before = app.db().contacts();
        AddContact addContactToGroupe = before.iterator().next();
        app.contacts().selectContactById(addContactToGroupe.getId());
        app.contacts().addContactTo(groupToAdd);
        app.contacts().submitAddToGroupeContact();
      }



  }
  @Test(enabled = false)
  public void dellContactFromeGroupe() throws Exception {
    app.goTo().gotoContactPage();
    app.contacts().choseGroupe(groupe);
    app.contacts().selectContactById(268);
    app.contacts().dellContactFrom();
    app.goTo().gotoContactPage();
    app.contacts().choseGroupe("[all]");





  }


}


