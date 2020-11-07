package addressbook.test.tests;

import org.testng.annotations.Test;

public class ContactDeleteTest extends TestBase{

  @Test
  public void testContactDelete() throws Exception {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().gotoContactPage();
  }

}
