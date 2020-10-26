package main.java.pak;
import main.java.pak.Kvadrat;
import main.java.pak.Priamougoknik;
import main.java.pak.Point;
public class
Test {

  public static void main(String[] args) {
    //вызов текстовой функции (вывод на экран т.к. в функции уже есть метод вывода)
    testtTekst("  qwerty ");
    // функция без класса  double l = 4;
    Kvadrat k = new Kvadrat(5);
    Priamougoknik p = new Priamougoknik(7, 8);
    //  p.a = 7;
    // p.b = 8;

//вызов числовой функции (вывод на экран)
    System.out.println(" s_1 " + k.l + " umnogenie " + k.testDoubl());
    System.out.println(" s_2 " + p.a + " and " + p.b + " = " + p.testDoubl2());



    System.out.println(" ------------  второе задание функция ----------");
    double x1 = 1;
    double x2 = 3;
    double y1 = 2;
    double y2 = 6;
    System.out.println(" расстояние между точками  "  + " точка А " + x1 + "-" + y1 + " точка Б " + x2 + "-" + y2 +
            "  расстояние " + String.format( "%.2f" , distance(x1, x2, y1, y2)));
    System.out.println(" ------------  второе задание класс(метод) ----------");



    Point po = new Point(1,2);
    Point po2 = new Point(3,6);
    System.out.println(" расстояние между точками  "  + " точка А " + po.x + "-" + po.y + " точка Б " + po2.x + "-" + po2.y +
            "  расстояние " + String.format( "%.2f" , po.distanceMetod(po2)));


  }

  // второе задание - вычисление длины (функция)
  public static double distance(double x1, double x2,double y1, double y2)
  {
    return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));

  }


//текстовая функция
  public static void testtTekst(String tekst) {
    System.out.println(" test !!!!!!!!!!!!!" + tekst + " testttt!!!!!!!!!!");
  }












  }


