package addressbook.test.tests;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactCreationTeste extends TestBase {



  @DataProvider
  public Iterator<Object[]> validFromCsv() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader =new BufferedReader(new FileReader("src\\resourses\\contact.csv"));
    String line = reader.readLine();
    // читаем пока строки не кончаться
    while (line != null) {
      // делем на части каждуюс строку
      String[] split = line.split(";") ;
      // деобаляем в массив
      list.add(new Object[] {new AddContact().withFirstname(split[0]).withLastname(split[1]).withMiddlename(split[2])
              .withCompany(split[3]).withHome(split[4]).withMobile(split[5]).withWork(split[6])
              .withEmail(split[7]).withBday(split[8]).withBmonth(split[9])
              .withByear(split[10]).withGroup(split[11])});
      line = reader.readLine();

    }
    return list.iterator();
  }



  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().groupPage();
    String groupee = app.groupe().name("contact_groupe");
    if (groupee.equals("null")) {
      app.groupe().createGroup(new GropeData().withName("contact_groupe"));
    }

  }






  @Test(dataProvider = "validFromCsv")
  public void contactCreationTesteWithFile(AddContact contact) throws Exception {

    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();
    app.goTo().gotoAddContactPage();

    app.contacts().createContact(contact, true);
      app.goTo().returnToHomePage();
    Contacts after = app.contacts().all();
    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

  }



  @Test(enabled = false)
  public void contactCreationTeste() throws Exception {

    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();
    app.goTo().gotoAddContactPage();

    AddContact contact = new AddContact().withFirstname("test9").withLastname("test1").withMiddlename("test1")
            .withCompany("test company3").withHome("11").withMobile("22").withWork("333")
            .withEmail("test@test.ru").withBday("8").withBmonth("July")
            .withByear("1990").withGroup("contact_groupe");
    app.contacts().addContactForm((contact), true);
    app.contacts().submitNewContact();
    app.goTo().returnToHomePage();
    Contacts after = app.contacts().all();

    assertThat(after.size(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));


  //  System.out.println(before);
  //  System.out.println(after);
  }

  @Test(enabled = false)
  public void contactCreationTesteWithFoto() throws Exception {

    app.goTo().returnToHomePage();
    Contacts before = app.contacts().all();
    app.goTo().gotoAddContactPage();
    File photo = new File("src\\resourses\\jordan.jpg");
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









