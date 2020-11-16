package addressbook.test.model;

import java.util.Objects;

public class GropeData {
  private final String id;
  private final String name;
  private final String header;
  private final String footer;


  public GropeData(String id, String name, String header, String footer) {
    this.id = id;
    this.name = name;
    this.header = header;
    this.footer = footer;

  }
  public GropeData( String name, String header, String footer) {
    this.id = null;
    this.name = name;
    this.header = header;
    this.footer = footer;

  }


  public String getId() {
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

  @Override
  public String toString() {
    return "GropeData{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GropeData gropeData = (GropeData) o;
    return Objects.equals(id, gropeData.id) &&
            Objects.equals(name, gropeData.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
