package addressbook.test.tests;

import model.GropeData;
import org.testng.annotations.*;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {

    app.gotoGroupPage();
    app.initGroupeCreation();
    app.fillGroupeForm(new GropeData("name", "header", "footer"));
    app.submitGroupeCreation();
    app.returnToGroupePage();
  }


}
