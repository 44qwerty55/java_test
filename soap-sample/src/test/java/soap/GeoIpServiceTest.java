package soap;

import com.lavasoft.*;
import com.lavasoft.GeoIPServiceHttpGet;
import com.lavasoft.GeoIPServiceSoap;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class GeoIpServiceTest {

  @Test
  public void testMyIp()   {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.168.1.38");
  Assert.assertTrue(geoIP.contains("CA"));

  }
  @Test
  public void testErrIp()   {
    String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("192.1.38.xxx");
    Assert.assertTrue(geoIP.contains("CA"));

  }
}
