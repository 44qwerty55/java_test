package addressbook.test.appmanager;

import addressbook.test.model.AddContact;
import addressbook.test.model.Contacts;
import addressbook.test.model.GropeData;
import addressbook.test.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {

  // A SessionFactory is set up once for an application!
  final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
          //   .configure(new File("src\\test\\resources\\hibernate.cfg.xml")) // configures settings from hibernate.cfg.xml
          .configure().build();


    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();

}
public Groups groups() {
  Session session = sessionFactory.openSession();
  session.beginTransaction();
  List<GropeData> result = session.createQuery("from GropeData").list();
  session.getTransaction().commit();
  session.close();
  // используем конструктор из public class Groups
  return new Groups(result);
}
  public Contacts contacts() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<AddContact> result = session.createQuery("from AddContact where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

}
