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



    // проверка на наличие контакта
    if (app.db().contacts().size() == 0)
    {
      app.goTo().gotoAddContactPage();
      app.contacts().addContactFormFIO(new AddContact().withFirstname("test9").withLastname("test1"));
      app.contacts().submitNewContact();
    }
    // проверка на то что есть контакты не привязанные к группе
   // else
   //   {       }


  }



  @Test(enabled = true)
  public void addContactToGroupe() throws Exception {
    Contacts findIdContact = app.db().contacts();

    Groups groupsBefore = app.db().groups();
  //  logger.info("groops   " + groups );
    List<GropeData> gropesBef = new ArrayList<>();
    for (GropeData gr: groupsBefore) {
      if (gr.getName().equals(groupe)) {
        gropesBef.add(gr);
      }
    }
    logger.info("нужная группа  " + gropesBef);
    GropeData groupBefore = gropesBef.iterator().next();
   // logger.info("нужная группа 2 " + groupQ.getId());
    Contacts contactsBefore = groupBefore.getContacts();
    logger.info("список контактов до    " + contactsBefore);



     app.goTo().gotoContactPage();
    AddContact addContactToGroupe = findIdContact.iterator().next();
    app.contacts().selectContactById(addContactToGroupe.getId());
    app.contacts().addContactTo(groupe);
    app.contacts().submitAddToGroupeContact();





   // assertThat(after, equalTo(findIdContact.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }


}
