package generators;

import addressbook.test.model.AddContact;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "file")
  public String file;

  @Parameter(names = "-d", description = "date Format")
  public String format;

  public static void main(String[] args) throws IOException {

    ContactGenerator generator = new ContactGenerator();

    JCommander jCommander = new JCommander(generator);
    // если поймал исключение
    try {
      jCommander.parse(args);
    }catch (ParameterException ex){
      // то выводим доступные действия
      jCommander.usage();
      return;
    }
    generator.run();


  }

  private void run() throws IOException {
    List<AddContact> contacts = generatContacts(count);
    if (format.equals("csv")){
      saveAsCsv(contacts , new File(file));
    } else if (format.equals("xml")){
      saveAsXml(contacts , new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("bad format " + format);
    }

  }

  private void saveAsJson(List<AddContact> contacts, File file) throws IOException {
    // формат jsona
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);

    // записываем в файл
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<AddContact> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    // тэг для сохранения данных
    xstream.alias("contact", AddContact.class);
    // убираем лишние данные
   // xstream.omitField(AddContact.class, "id");
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  // для вызова без командной строки нужно прописать методам значение static (private static void save)
  private  void saveAsCsv(List<AddContact> contacts, File file) throws IOException {
    File currentDir = new File(".");
    currentDir.getAbsolutePath();
    System.out.println("qwerty " + currentDir.getAbsolutePath());
    // запись в файл
    Writer writer = new FileWriter(file);
    for (AddContact contact : contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(),contact.getLastname(),contact.getMiddlename()
      ,contact.getCompany(),contact.getHome(),contact.getMobile(),contact.getWork(),contact.getEmail(),contact.getBday()
      ,contact.getBmonth(),contact.getByear(),contact.getNew_group()));
    }
    // закрываем файл
    writer.close();
  }


  private  List<AddContact> generatContacts(int coount) {
    List<AddContact>  contacts = new ArrayList<AddContact>();
    for (int i=0; i< coount; i++){
      contacts.add(new AddContact().withFirstname(String.format("test %s", i))
              .withLastname(String.format("test %s",i)).withMiddlename(String.format("test %s",i))
      .withCompany(String.format("company %s",i)).withHome(String.format("123%s",i)).withMobile(String.format("1254%s",i))
      .withWork(String.format("65789%s",i)).withEmail(String.format("test%s@test.ru",i)).withBday(String.format("7"))
      .withBmonth(String.format("July")).withByear(String.format("1982")).withGroup(String.format("contact_groupe")));
    }
    return contacts;
  }

}
