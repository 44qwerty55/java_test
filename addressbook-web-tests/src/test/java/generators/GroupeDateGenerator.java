package generators;

import addressbook.test.model.GropeData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupeDateGenerator {
  // параметры для работы с командной строкой

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "file")
  public String file;

  public static void main(String[] args) throws IOException {

   // переменные с учетом вывода из cmd
    GroupeDateGenerator  generator = new GroupeDateGenerator();
    // вывод описания в консоль
 //   JCommander.newBuilder().addObject(generator).build().parse(args);
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

/* данные без выводав командную строку
    // преобразуем в число
    int count = Integer.parseInt(args[0]);
    // путь к файлу преобразуем в файл
    File file = new File(args[1]);

    // данные
    List<GropeData> groups = generateGroups(count);
    save(groups , file);
*/
  }

  private void run() throws IOException {
    List<GropeData> groups = generateGroups(count);
    save(groups , new File(file));
  }

  // для вызова без командной строки нужно прописать методам значение static (private static void save)
  private  void save(List<GropeData> groups, File file) throws IOException {
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
// для вызова без командной строки нужно прописать методам значение static (  private static List<GropeData>)
  private  List<GropeData> generateGroups(int coount) {
List<GropeData>  groups = new ArrayList<GropeData>();
for (int i=0; i< coount; i++){
  groups.add(new GropeData().withName(String.format("test %s", i))
  .withHeader(String.format("Header %s",i)).withFooter(String.format("Footer %s",i)));
}
    return groups;
  }
}
