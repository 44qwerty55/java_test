package mantis.tests;

import mantis.model.MailMessage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTest extends TestBase{
/*  тест для регистрации  с использованием внутренней почты
  @BeforeMethod
  public void startMailsServer() {
    app.mail().start();
  }


  @Test
  public void  testRegistration() throws IOException, MessagingException {
    // непосредственное обращение к браузеру registration()
    // создание уникальных идентификаторов currentTimeMillis - возвращает текущее время
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);
    String user = "user" + now;
    String password = "password";
app.registration().start(user ,email);
// получаем письма
List<MailMessage> mailMessages =   app.mail().waitForMail(2,10000);
// поиск ссылки из письма
String confirmationLink = findConfirmationLink(mailMessages, email);
app.registration().finish(confirmationLink , password);
// проверка что пользователь входит в систему
assertTrue (app.newSession().login(user,password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    // ищем текст письма
  MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
  // с помощью регулярки ищем ссылку
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  // выполнение даже если были ошибки alwaysRun = true
@AfterMethod(alwaysRun = true)
 public void stopMailServer() {
  app.mail().stop();
    }
*/

  public void startMailsServer() {
    app.mail().start();
  }


  @Test
  public void  testRegistration() throws IOException, MessagingException {

    // создание уникальных идентификаторов currentTimeMillis - возвращает текущее время
    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);

    String user = "user" + now;
    String password = "password";
    // создаем пользователя на почтовом сервере
    app.james().createUser(user ,email);
    // непосредственное обращение к браузеру registration()
    app.registration().start(user ,email);
// получаем письма
    List<MailMessage> mailMessages =   app.james().waitForMail(user ,email, 60000);
// поиск ссылки из письма
    String confirmationLink = findConfirmationLink(mailMessages, email);
    System.out.println(confirmationLink);
    app.registration().finish(confirmationLink , password);
// проверка что пользователь входит в систему
    assertTrue (app.newSession().login(user,password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    // ищем текст письма
    MailMessage mailMessage =  mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    // с помощью регулярки ищем ссылку
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


  // выполнение даже если были ошибки alwaysRun = true
  public void stopMailServer() {
    app.mail().stop();
  }

}
