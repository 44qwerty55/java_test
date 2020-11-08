package test1;

import org.testng.Assert;
import org.testng.annotations.Test;
import pak.Equation;

public class EquationTests {

  @Test
  public void tests0 (){
    Equation e = new Equation(1,1,1);
    Assert.assertEquals(e.rootNumber(),0);
  }

  @Test
  public void tests1 (){
    Equation e = new Equation(1,2,1);
    Assert.assertEquals(e.rootNumber(),1);
  }
  @Test
  public void tests2 (){
    Equation e = new Equation(1,5,6);
    Assert.assertEquals(e.rootNumber(),2);
  }



}
