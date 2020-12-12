package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DellContactFromGroupe extends TestBase{


  // переменная с название группы
  Logger logger = LoggerFactory.getLogger(DellContactFromGroupe.class);
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
    // цикл поиска по всем группам
    for (GropeData group : result) {
      // если нашли группу с нужным названием , из конфига, все ок
      if (group.getName().equals(groupToAdd)) {
        contactGrroup = group.getName();
      }
    }
    // если поиск по бд с названием группы из конфига дал null добавляем группу с название из конфига
    if (contactGrroup == null) {
      app.goTo().groupPage();
      app.groupe().createGroup(new GropeData().withName(groupToAdd));
    }

    // проверка на то что есть контакт привязанный к группе
      Groups resultcon = app.db().groups();
      String contactWithGrroup = "true";
      for (GropeData groupsee : resultcon) {
       if (groupsee.getName().equals(groupToAdd) ){
        if (groupsee.getContacts().isEmpty()) {
          // в группе нет ни одного контакта
          contactWithGrroup = null;
        }
      }
      }
    // привязываем любой контакт к группе
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
  public void findIdTest() throws Exception {

    Groups groups = app.db().groups();
    logger.info("groops   " + groups );
    List<GropeData> gropes = new ArrayList<>();
    for (GropeData gr: groups) {
      if (gr.getName().equals(groupe)) {
        gropes.add(gr);
      }
    }
    logger.info("нужная группа  " + gropes);
    GropeData groupQ = gropes.iterator().next();
    logger.info("нужная группа 2 " + groupQ.getId());
    AddContact contact = groupQ.getContacts().iterator().next();
    logger.info("айди   " + contact.getId());
  }


  @Test(enabled = true)
  public void dellContactFromeGroupe() throws Exception {
    app.goTo().gotoContactPage();
    app.contacts().choseGroupe(groupe);
    // ищем id контакта добавленного в группу, что бы потом его выбрать
    Groups groups = app.db().groups();
    List<GropeData> gropes = new ArrayList<>();
    for (GropeData gr: groups) {
      if (gr.getName().equals(groupe)) {
        gropes.add(gr);
      }
    }
    GropeData groupQ = gropes.iterator().next();
    AddContact contact = groupQ.getContacts().iterator().next();
    // выбираем
    app.contacts().selectContactById(contact.getId());
    app.contacts().dellContactFrom();
    app.goTo().gotoContactPage();
    app.contacts().choseGroupe("[all]");

  }


}


