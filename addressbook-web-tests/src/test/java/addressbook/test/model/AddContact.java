package addressbook.test.model;

import com.google.gson.annotations.Expose;

import java.io.File;
import java.util.Objects;

public class AddContact  {
  private  int id = Integer.MAX_VALUE;
  @Expose
  private  String firstname;
  @Expose
  private  String middlename;
  @Expose
  private  String lastname;
  @Expose
  private  String company;



  private  String address;
  @Expose
  private  String home;
  @Expose
  private  String mobile;
  @Expose
  private  String work;


  private  String allPhones;
  @Expose
  private  String email;
  private  String email2;
  private  String email3;
  private  String allEmails;

  @Expose
  private  String bday;
  @Expose
  private  String bmonth;
  @Expose
  private  String byear;
  @Expose
  private  String new_group;


  private File photo;

/*
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
*/

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

  public String getAddress() {
    return address;
  }




  public String getHome() {    return home;  }
  public String getMobile() {
    return mobile;
  }
  public String getWork() {
    return work;
  }



  public String getAllPhones() {
    return allPhones;
  }


  public String getEmail() {
    return email;
  }
  public String getEmail2() {
    return email2;
  }
  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
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

  public File getPhoto() {
    return photo;
  }

  public AddContact withPhoto(File photo) {
    this.photo = photo;
    return this;
  }





  public AddContact withId(int id) {
    this.id = id;
    return this;
  }

  public AddContact withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public AddContact withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public AddContact withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public AddContact withCompany(String company) {
    this.company = company;
    return this;
  }

  public AddContact withAddress(String address) {
    this.address = address;
    return this;
  }



  public AddContact withHome(String home) {
    this.home = home;
    return this;
  }

  public AddContact withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }
  public AddContact withWork(String work) {
    this.work = work;
    return this;
  }
  public AddContact withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }


  public AddContact withEmail(String email) {
    this.email = email;
    return this;
  }
  public AddContact withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }
  public AddContact withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public AddContact withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public AddContact withBday(String bday) {
    this.bday = bday;
    return this;
  }

  public AddContact withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public AddContact withByear(String byear) {
    this.byear = byear;
    return this;
  }
  public AddContact withGroup(String new_group) {
    this.new_group = new_group;
    return this;
  }

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
    return id == contact.id &&
            Objects.equals(firstname, contact.firstname) &&
            Objects.equals(lastname, contact.lastname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname);
  }
}
