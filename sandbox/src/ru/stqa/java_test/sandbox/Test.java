package ru.stqa.java_test.sandbox;

public class Test {

  public static void main(String[] args) {
    //вызов текстовой функции (вывод на экран т.к. в функции уже есть метод вывода)
    testtTekst("  qwerty ");
    // функция без класса  double l = 4;
    Kvadrat k = new Kvadrat(5);
    Priamougoknik p = new Priamougoknik(7, 8);
    //  p.a = 7;
    // p.b = 8;

//вызов числовой функции (вывод на экран)
    System.out.println(" s_1 " + k.l + " umnogenie " + testDoubl(k));
    System.out.println(" s_2 " + p.a + " and " + p.b + " = " + testDoubl2(p));
  }


  public static double testDoubl(Kvadrat k) {
    return k.l * k.l;
  }

  public static double testDoubl2(Priamougoknik p) {
    return p.a * p.b;
  }

  public static void testtTekst(String tekst) {
    System.out.println(" test !!!!!!!!!!!!!" + tekst + " testttt!!!!!!!!!!");
  }
}
