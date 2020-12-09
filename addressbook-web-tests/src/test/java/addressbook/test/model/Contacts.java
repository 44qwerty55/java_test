package addressbook.test.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<AddContact> {

  private Set<AddContact> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<AddContact>(contacts.delegate);
  }

  // конструктор для контактов из бд, что бы построить по произвольной коллекции
  public Contacts(Collection<AddContact> contacts) {
    // строим множество объектов HashSet из коллекции
    this.delegate = new HashSet<AddContact>(contacts);
  }



public Contacts(){
    this.delegate = new HashSet<>();
}

  @Override
  protected Set<AddContact> delegate() {
    return delegate;
  }

  public Contacts withAdded(AddContact contact){
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }
  public Contacts withRemove(AddContact contact){
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }

}
