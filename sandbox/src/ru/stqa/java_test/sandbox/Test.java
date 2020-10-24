package ru.stqa.java_test.sandbox;

public class Test {

  public static void main(String[] args) {
    testtTekst("  qwerty ");
    double l = 4;
    double a = 7;
    double b = 8;


    System.out.println(" s_1 " + testDoubl(l));
    System.out.println(" s_2 " + testDoubl(a, b));
  }


  public static double testDoubl(double l) {
    return l * l;
  }

  public static double testDoubl(double a, double b) {
    return a * b;
  }

  public static void testtTekst(String tekst) {
    System.out.println(" test !!!!!!!!!!!!!" + tekst + " testttt!!!!!!!!!!");
  }
}
