package mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<User> {

  private Set<User> delegate;

  public Users(Users users) {
    this.delegate = new HashSet<User>(users.delegate);
  }

  // конструктор для контактов из бд, что бы построить по произвольной коллекции
  public Users(Collection<User> users) {
    // строим множество объектов HashSet из коллекции
    this.delegate = new HashSet<User>(users);
  }



  public Users(){
    this.delegate = new HashSet<>();
  }

  @Override
  protected Set<User> delegate() {
    return delegate;
  }


}
