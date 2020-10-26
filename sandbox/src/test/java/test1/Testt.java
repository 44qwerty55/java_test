package test1;

import main.java.pak.Kvadrat;
import org.testng.Assert;
import org.testng.annotations.Test;


public class Testt {
  @Test
  public void KvadratTest() {

    main.java.pak.Kvadrat kv = new Kvadrat(5);
    Assert.assertEquals(kv.testDoubl(), 25.0);
    //assert kv.testDoubl() == 25;
  }
}
