package addressbook.test.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "group_list")
public class GropeData {

@Id
@Column(name = "group_id")
  private  int id =Integer.MAX_VALUE;;
@Expose
@Column(name = "group_name")
  private  String name;
  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  private  String header;
  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  private  String footer;

/*
  public GropeData(int id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;

  }



  public GropeData(String name, String header, String footer) {
    this.id = Integer.MAX_VALUE;
    this.name = name;
    this.header = header;
    this.footer = footer;

  }
*/

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getHeader() {
    return header;
  }

  public String getFooter() {
    return footer;
  }


  public GropeData withId(int id) {
    this.id = id;
    return this;
  }


  public GropeData withName(String name) {
    this.name = name;
    return this;
  }

  public GropeData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GropeData withFooter(String footer) {
    this.footer = footer;
    return this;
  }

  @Override
  public String toString() {
    return "GropeData{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GropeData gropeData = (GropeData) o;
    return id == gropeData.id &&
            Objects.equals(name, gropeData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
