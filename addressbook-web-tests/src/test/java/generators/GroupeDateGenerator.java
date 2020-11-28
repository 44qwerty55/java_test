package generators;

import addressbook.test.model.GropeData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupeDateGenerator {

  public static void main(String[] args) throws IOException {
    // преобразуем в число
    int coount = Integer.parseInt(args[0]);
    // путь к файлу преобразуем в файл
    File file = new File(args[1]);

    // данные
    List<GropeData> groups = generateGroups(coount);
    save(groups , file);

  }

  private static void save(List<GropeData> groups, File file) throws IOException {
    File currentDir = new File(".");
    currentDir.getAbsolutePath();
    System.out.println("qwerty " + currentDir.getAbsolutePath());
    // запись в файл
    Writer writer = new FileWriter(file);
    for (GropeData group : groups){
      writer.write(String.format("%s;%s;%s\n", group.getName(),group.getHeader(),group.getFooter()));
    }
    // закрываем файл
    writer.close();
  }
// создаем список групп
  private static List<GropeData> generateGroups(int coount) {
List<GropeData>  groups = new ArrayList<GropeData>();
for (int i=0; i< coount; i++){
  groups.add(new GropeData().withName(String.format("test %s", i))
  .withHeader(String.format("Header %s",i)).withFooter(String.format("Footer %s",i)));
}
    return groups;
  }
}
