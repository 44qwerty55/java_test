package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.hibernate.Session;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTeste extends TestBase {



  @DataProvider
  public Iterator<Object[]> validFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader =new BufferedReader(new FileReader("src\\test\\resources\\contact.csv"));
    String line = reader.readLine();
    // читаем пока строки не кончаться
    while (line != null) {
      // делем на части каждуюс строку
      String[] split = line.split(";") ;
      // деобаляем в массив
      list.add(new Object[] {new AddContact().withFirstname(split[0]).withLastname(split[1]).withMiddlename(split[2])
              .withCompany(split[3]).withHome(split[4]).withMobile(split[5]).withWork(split[6])
              .withEmail(split[7]).withBday(split[8]).withBmonth(split[9])
              .withByear(split[10])});
      line = reader.readLine();

    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> validFromJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contact.json")));
    String json = "";
        // читаем строки из файла
    String line = reader.readLine();
    // читаем пока строки не кончаться
    while (line != null) {
      json += line;
      line = reader.readLine();

    }
    Gson gson = new Gson();
    List<AddContact> contacts =  gson.fromJson(json, new TypeToken<List<AddContact>>(){}.getType());
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();

  }

  @DataProvider
  public Iterator<Object[]> validFromXml() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src\\test\\resources\\contact.xml")));
    String xml = "";
    // читаем строки из файла
    String line = reader.readLine();
    // читаем пока строки не кончаться
    while (line != null) {
      xml += line;
      line = reader.readLine();

    }
    XStream xstream = new XStream();
    xstream.alias("contact", AddContact.class);
    // убираем лишние данные
    xstream.omitField(AddContact.class, "id");
    // xstream обрабатывает анотации
    //  xstream.omitField(GropeData.class, "id");
  //  xstream.processAnnotations(AddContact.class);
    List<AddContact> contacts = (List<AddContact>)    xstream.fromXML(xml);

    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();


  }

  // проверка на название группы через БД
  @BeforeMethod
  public void ensurePrecondition() {

    Groups result = app.db().groups();
    String contactGrroup = null;
    for (GropeData group : result) {

      if (group.getName().equals("contact_groupe")) {
        contactGrroup = group.getName();
      }
    }
    if (contactGrroup == null) {
      app.goTo().groupPage();
      app.groupe().createGroup(new GropeData().withName("contact_groupe"));
    }
  }

/* старая проверка на название группы через браузер
  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().groupPage();
    String groupee = app.groupe().name("contact_groupe");
    if (groupee.equals("null")) {
      app.groupe().createGroup(new GropeData().withName("contact_groupe"));
    }

  }
*/




  //получение данных о группе из файлов
  @Test(dataProvider = "validFromXml" ,enabled = false )
  public void contactCreationTesteWithFile(AddContact contact) throws Exception {
   // Groups beforeGroups = app.db().groups();
    Contacts before = app.db().contacts();
    app.goTo().gotoAddContactPage();

    app.contacts().createContact(contact, true ,false);
      app.goTo().returnToHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  // полученте данных о группе из бд
  @Test(enabled = true)
  public void contactCreationTesteWithGroupBd() throws Exception {
    Groups beforeGroups = app.db().groups();
    Contacts before = app.db().contacts();
    app.goTo().gotoAddContactPage();

    AddContact contact = new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1")
            .withCompany("test company3").withAddress("testt").withHome("11").withMobile("22").withWork("333")
            .withEmail("test@test.ru").withBday("8").withBmonth("July")
            .withByear("1990").inGroup(beforeGroups.iterator().next());
    app.contacts().addContactForm((contact), false,true);
    app.contacts().submitNewContact();
    app.goTo().returnToHomePage();
    Contacts after = app.db().contacts();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }

  // получение списка контактов из веб для работы надо перегенерить метод сравнения в GropeData  equals и hashCode на те значения которые отображаються в веб интерфейсе
  @Test(enabled = false)
  public void contactCreationTeste() throws Exception {
    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();
    app.goTo().gotoAddContactPage();

    AddContact contact = new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1")
            .withCompany("test company3").withHome("11").withMobile("22").withWork("333")
            .withEmail("test@test.ru").withBday("8").withBmonth("July")
            .withByear("1990");
    app.contacts().addContactForm((contact), false,true);
    app.contacts().submitNewContact();
    app.goTo().returnToHomePage();
    Contacts after = app.contacts().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }
  // получение списка контактов из веб для работы надо перегенерить метод сравнения в GropeData  equals и hashCode на те значения которые отображаються в веб интерфейсе
  @Test(enabled = false)
  public void contactCreationTesteWithFoto() throws Exception {

    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();
    app.goTo().gotoAddContactPage();
    File photo = new File("src\\test\\resources\\jordan.jpg");
    AddContact contact = new AddContact().withFirstname("testf").withLastname("test1").withPhoto(photo);
    app.contacts().createContactWithFoto(contact);
    app.contacts().submitNewContact();
      app.goTo().returnToHomePage();
    Contacts after = app.contacts().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }




    @Test(enabled = false)
  public void testCurrentDir(){
    File currentDir = new File(".");
  currentDir.getAbsolutePath();
    System.out.println("qwerty " + currentDir.getAbsolutePath());
  File photo =  new File("src\\resourses\\jordan.jpg");
  System.out.println("photo " + photo.getAbsolutePath());
  System.out.println("photo + " + photo.exists());
  }

}









