package addressbook.test.model;

public class AddContact  {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String company;
  private final String email;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String new_group;

  public AddContact(String firstname, String middlename, String lastname, String company, String email, String bday, String bmonth, String byear, String new_group) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.company = company;
    this.email = email;
    this.bday = bday;
    this.bmonth = bmonth;
    this.byear = byear;
    this.new_group = new_group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {
    return lastname;
  }

  public String getCompany() {
    return company;
  }

  public String getEmail() {
    return email;
  }

  public String getBday() {
    return bday;
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  public String getNew_group() {
    return new_group;
  }
}

