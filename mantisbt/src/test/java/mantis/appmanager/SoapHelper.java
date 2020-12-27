package mantis.appmanager;

import biz.futureware.mantis.rpc.soap.client.*;
import mantis.model.Issue;
import mantis.model.Project;
import org.openqa.selenium.support.ui.Select;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class SoapHelper {
  private final ApplicationManager app;

  public SoapHelper(ApplicationManager app) {
    this.app = app;
  }

  public Set<Project> getProjects() throws MalformedURLException, RemoteException, ServiceException {
    MantisConnectPortType mc = getMantisConnect();
    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    // преобразуем полученные данные в модельные объекты  , преобразуем в инт p.getId().intValue()
   return Arrays.asList(projects).stream()
           .map((p) -> new Project().withId(p.getId().intValue()).withName(p.getName())).collect(Collectors.toSet());
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    MantisConnectPortType mc = new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("soap.connectString")));
    return mc;
  }

  public Issue addIssue(Issue issue) throws MalformedURLException, ServiceException, RemoteException {
    // Открытие соединения
    MantisConnectPortType mc = getMantisConnect();
    // Получение списка категорий для баг-репортов
    String[] categories = mc.mc_project_get_categories
            ("administrator", "root",
                    BigInteger.valueOf(issue.getProject().getId()));
    // Создание из нашего модельного объекта структуры объекта Mantis SOAP
    IssueData issueData = new IssueData();
    issueData.setSummary(issue.getSummary() );
    issueData.setDescription(issue.getDescription());
    // setProject ожидает в параметре ObjectRef project, т.е. ссылку на проект
    // ObjectRef, в свою очередь, ожидает два параметра, причем один из них с типом BigInteger
    issueData.setProject(new ObjectRef(BigInteger.valueOf(issue.getProject().getId()), issue.getProject().getName()));
    // Категория - обязательное поле, список выбора. Выбираем первую попавшуюся из списка categories
    issueData.setCategory(categories[0]);
    // Создание баг-репорта
    BigInteger issueId =  mc.mc_issue_add("administrator", "root", issueData);
    // Обратное преобразование объекта Mantis SOAP в новый модельный объект. Получение объекта
    IssueData createdIssueData = mc.mc_issue_get("administrator", "root", issueId);
    // Обратное преобразование объекта Mantis SOAP в новый модельный объект. Заполнение модели
    return new Issue().withId(createdIssueData.getId().intValue())
            .withSummary(createdIssueData.getSummary()).withDescription(createdIssueData.getDescription())
            .withProject(new Project().withId(createdIssueData.getId().intValue())
            .withName(createdIssueData.getProject().getName()));
  }
}
