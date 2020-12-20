package mantis.tests;

import mantis.model.MailMessage;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPassword extends TestBase{

  public void startMailsServer() {
    app.mail().start();
  }


  @Test
  public void resetPassword() throws MessagingException, IOException {

String admin = app.getProperty("web.adminLogin");
String pass = app.getProperty("web.adminPassword");
String user = "user1608490390230";
    String passwordUsr  = "password";
    String email = "user1608490390230@localhost";
    app.james().drainEmail(user ,email);
app.registration().login(admin,pass);
app.registration().resetPassword(user);
// получаем письма
    List<MailMessage> mailMessages =   app.james().waitForMail(user ,email, 600000);
// поиск ссылки из письма
    String confirmationLink = findConfirmationLink(mailMessages, email);
    System.out.println(confirmationLink);
    app.registration().finish(confirmationLink , passwordUsr  );
// проверка что пользователь входит в систему
    assertTrue (app.newSession().login(user,passwordUsr));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    // ищем текст письма
    MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    // с помощью регулярки ищем ссылку
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  public void stopMailServer() {
    app.mail().stop();
  }

}
