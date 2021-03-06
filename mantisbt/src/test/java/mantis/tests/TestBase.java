package mantis.tests;


import mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;


public class TestBase {


  protected static final ApplicationManager app =
          new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite (alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
    app.ftp().upload(new File("src\\test\\resources\\config_inc.php"), "config_inc.php" ,"config_inc.php.bak");
  }
  public boolean isIssueOpen(int issueId) throws IOException, ServiceException {
    if (app.soap().issueStatus(issueId) != 80) {
      return true;
    }
    return false;
  }

  public void skipIfNotFixed(int issueId) throws IOException, ServiceException{
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }


  public void skipIfNotFixedBugify(int issueId) throws IOException, ServiceException {
    if (isIssueOpenBugify(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  public boolean isIssueOpenBugify(int issueId) throws IOException, ServiceException {
    if (app.rest().getIssueStatus(issueId) != 2) {
      return true;
    }
    return false;
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.ftp().restore("config_inc.php.bak" ,"config_inc.php");
    app.stop();

  }


}
