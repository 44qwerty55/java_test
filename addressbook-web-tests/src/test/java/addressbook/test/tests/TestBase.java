package addressbook.test.tests;

import addressbook.test.appmanager.ApplicationManager;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
//import sun.plugin2.util.BrowserType;

public class TestBase {

 // protected static final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite (alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }
// сравнение данных из бд и из ui (для бдберем тока id  и name )
  public void verifyGroupListInUI() {
    // преобразование значения в булевскую велечену Boolean.getBoolean  (-DverifyUI=true)
    if (Boolean.getBoolean("verifyUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.groupe().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GropeData().withId(g.getId()).withName(g.getName()))
              .collect(Collectors.toSet())));
    }
  }
}
