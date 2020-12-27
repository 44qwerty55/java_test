package mantis.model;

public class Issue {


  private int id;
  private String summary;
  private String description;
  private Project project;
  private int state;
  // служебная характеристика category - будет рассмотрена позже

  public int getStatus() {
    return state;
  }

  public int getId() {
    return id;
  }


  public String getSummary() {
    return summary;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getDescription() {
    return description;
  }
  public Project getProject() {
    return project;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }


  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }



  public Issue withProject(Project project) {
    this.project = project;
    return this;
  }
}
