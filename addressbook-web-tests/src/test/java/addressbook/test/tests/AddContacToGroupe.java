package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContacToGroupe extends TestBase{

  // переменная с название группы
  Logger logger = LoggerFactory.getLogger(AddContacToGroupe.class);
  private String groupe;

  @BeforeMethod
  public void ensurePrecondition() {
    // берем название группы из файла конфигурации
    String groupToAdd = app.properties().getProperty("groupToadd");
    groupe = groupToAdd;

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


    // проверка на то что есть контакт не  привязанный не к одной  группе
    Contacts conactsWithGroupe = app.db().contacts();
    Set<AddContact> contactList = new HashSet<>();
    for (AddContact con : conactsWithGroupe) {
      if (con.getGroups().size() == 0){
       contactList.add(con);}
    }
    // если таких нет создаем контакт
    if (contactList.isEmpty()) {
      app.goTo().gotoAddContactPage();
      app.contacts().addContactFormFIO(new AddContact().withFirstname("testqqq").withLastname("testqqq"));
      app.contacts().submitNewContact();
    }

  }






  @Test(enabled = true)
  public void addContactToGroupe() throws Exception {
    // выбираем группу в соответсвии с названием из конфига
    Groups groupeTest = app.db().groups();
    Set<GropeData> groupeTestList = new HashSet<>();
        for (GropeData group : groupeTest) {
          if (group.getName().equals(groupe)) {
            groupeTestList.add(group);
          }}

          GropeData groupQ = groupeTestList.iterator().next();
    // проверяем сколько контактов к ней привязанно
          Contacts beforeContacts = groupQ.getContacts();
    // пвыбираем контакты не привязанные к группе
    Contacts conactsWithGroupe = app.db().contacts();
    Set<AddContact> contactList = new HashSet<>();
    for (AddContact con : conactsWithGroupe) {
      if (con.getGroups().size() == 0){
        contactList.add(con);}
    }
          AddContact contact = contactList.iterator().next();
    // проверяем сколько групп было привязанно к контакту
          Groups beforeGroups = contact.getGroups();

// добавляем контакт в группу
          app.goTo().gotoContactPage();
          app.contacts().selectContactById(contact.getId());
          app.contacts().addContactTo(groupe);
          app.contacts().submitAddToGroupeContact();
// данные после добавления ипроверки на размер
          Contacts afterContacts = app.db().groupId(groupQ.getId()).getContacts();
          Groups afterGroups = app.db().contactsId(contact.getId()).getGroups();

          assertThat(afterContacts.size(), equalTo(beforeContacts.size() + 1));
          assertThat(afterGroups.size(), equalTo(beforeGroups.size() + 1));

        }}


