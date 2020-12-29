package mantis.tests;

import mantis.model.MailMessage;
import mantis.model.User;
import mantis.model.Users;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import javax.xml.rpc.ServiceException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPassword extends TestBase{




  @BeforeMethod
  public void ensurePrecondition() throws MessagingException, IOException, ServiceException {


    // проверка что пользователей больше чем 1 , тогда создаем пользователя
    if(app.db().users().size() <= 1)
    { long now = System.currentTimeMillis();
      String email = String.format("user%s@localhost", now);
      String user = "user" + now;
      String password = "password";
      app.james().createUser(user ,email);
      app.registration().start(user ,email);
      List<MailMessage> mailMessages =   app.james().waitForMail(user ,email, 60000);
      String confirmationLink = findConfirmationLink(mailMessages, email);
      System.out.println(confirmationLink);
      app.registration().finish(confirmationLink , password);
      assertTrue (app.newSession().login(user,password));}
  }

  @Test
  public void resetPassword() throws MessagingException, IOException {
  // берем из бд имя и почту пользователя , если он не админ т.е. не пользователь с id =1
    String userName = null;
    String emailUser = null;
    Users result = app.db().users();
    for (User user : result)  {
      if (user.getId() != 1){
        userName = user.getUsername();
      emailUser = user.getEmail();
      System.out.println("user   " + userName + "  email " + emailUser);
      break;}
    }


String admin = app.getProperty("web.adminLogin");
String pass = app.getProperty("web.adminPassword");
String user = userName;//"user1608569409825";
    String passwordUsr  = "password";
    String email = emailUser; // "user1608569409825@localhost";
// чистим почту выбранного пользователя
app.james().drainEmail(user ,email);
// вход под админом
app.registration().login(admin,pass);
// сброс пароля
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



}
