package addressbook.test.tests;



import addressbook.test.model.AddContact;
import addressbook.test.model.GropeData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.io.File;
import java.util.List;

public class HbConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
         //   .configure(new File("src\\test\\resources\\hibernate.cfg.xml")) // configures settings from hibernate.cfg.xml
        .configure().build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      e.printStackTrace();
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }

  @Test(enabled = true)
  public void testHbConnectionContact() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<AddContact> result = session.createQuery("from AddContact where deprecated = '0000-00-00'").list();

    session.getTransaction().commit();
    session.close();

    for (AddContact contact : result) {
      System.out.println("qqq   " + contact);
      System.out.println(contact.getGroups());
    }
  }

  @Test(enabled = true)
  public void testHbConnectionGroupe() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GropeData> result = session.createQuery("from GropeData").list();
    for (GropeData group : result) {
      System.out.println("ggg   "  + group);
      System.out.println("ggg   "  + group.getContacts());
    }
    session.getTransaction().commit();
    session.close();
    for (GropeData group : result) {
      System.out.println("ggg   "  + group);
      System.out.println("ggg   "  + group.getContacts());
    }
  }

}