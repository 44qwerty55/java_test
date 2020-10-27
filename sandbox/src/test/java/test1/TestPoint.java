package test1;

import main.java.pak.Point;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestPoint {
  @Test

  public void PointTest() {
    Point po = new Point(1, 2);
    Point po1 = new Point(3, 6);
    Assert.assertEquals(String.format("%.2f", po.distanceMetod(po1)), "4,47");
    //   Assert.assertEquals(  po.distanceMetod(po1) , 4.47213595499958);

  }
}
