package test1;

import org.testng.Assert;
import org.testng.annotations.Test;
import pak.Primes;

public class PrimeTests {

  @Test
  public void testPrimes() {
    Assert.assertTrue(Primes.isPrimeFaste(Integer.MAX_VALUE));
  }
  @Test(enabled = false)
  public void testPrimesLong() {
    long n=Integer.MAX_VALUE;
    Assert.assertTrue(Primes.isPrime(n));
  }
  @Test
  public void testNonePrimes() {
    Assert.assertFalse(Primes.isPrime(Integer.MAX_VALUE -2 ));
  }


}
