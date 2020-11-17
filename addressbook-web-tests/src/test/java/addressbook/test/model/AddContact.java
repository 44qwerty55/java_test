package addressbook.test.model;

import java.util.Objects;

public class AddContact  {
  private  int id;
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String company;
  private final String email;
  private final String bday;
  private final String bmonth;
  private final String byear;
  private final String new_group;
  //private final String new_group;

  public AddContact(String firstname, String middlename, String lastname, String company, String email, String bday, String bmonth, String byear, String new_group) {
    this.id = Integer.MAX_VALUE;
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


    public AddContact(int id, String firstname,String lastname ) {
      this.id = id;
      this.firstname = firstname;
      this.middlename = null;
      this.lastname = lastname;
      this.company = null;
      this.email = null;
      this.bday = null;
      this.bmonth = null;
      this.byear = null;
      this.new_group = null;
    }


  public int getId() {
    return id;
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

  public String getNew_group() {    return new_group;  }

  @Override
  public String toString() {
    return "AddContact{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AddContact contact = (AddContact) o;
    return Objects.equals(firstname, contact.firstname) &&
            Objects.equals(lastname, contact.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstname, lastname);
  }
}
