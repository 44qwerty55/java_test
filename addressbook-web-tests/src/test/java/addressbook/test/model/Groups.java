package addressbook.test.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Groups extends ForwardingSet<GropeData> {
  private Set<GropeData> delegate;

  public Groups(Groups groups) {
    this.delegate = new HashSet<GropeData>(groups.delegate);
  }
  public Groups() {
    this.delegate = new HashSet<>();
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
