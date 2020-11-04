package addressbook.test;

public class AddFioContact {
  private final String firstname;
  private final String middlename;
  private final String lastname;

  public AddFioContact(String firstname, String middlename, String lastname) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
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
}
