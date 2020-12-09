package addressbook.test.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GropeData> {
  private Set<GropeData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GropeData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<>();
  }

  // конструктор для групп из бд, что бы построить по произвольной коллекции
  public Groups(Collection<GropeData> groups) {
    // строим множество объектов HashSet из коллекции
    this.delegate = new HashSet<GropeData>(groups);
  }

  @Override
  protected Set<GropeData> delegate() {
    return delegate;
  }
  public  Groups withAdded(GropeData group){
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }
  public  Groups without(GropeData group){
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }

}
