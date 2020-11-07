package addressbook.test;

import org.testng.annotations.*;

public class GroopCreationTeste extends TestBase {

  @Test
  public void testGroopCreation() throws Exception {

    gotoGroupPage();
    initGroupeCreation();
    fillGroupeForm(new GropeData("name", "header", "footer"));
    submitGroupeCreation();
    returnToGroupePage();
  }


}
