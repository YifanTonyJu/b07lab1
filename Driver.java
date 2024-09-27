import java.io.File;

public class Driver {
    public static void main(String [] args) {
            Polynomial p = new Polynomial();
            System.out.println(p.evaluate(3));
            double [] c1 = {6,5};
            int [] c1_f = {0,3};
            Polynomial p1 = new Polynomial(c1, c1_f);
            double [] c2 = {-2,-9};
            int [] c2_f = {1, 4};
            Polynomial p2 = new Polynomial(c2, c2_f);
            Polynomial s = p1.add(p2);
            System.out.println("s(0.1) = " + s.evaluate(0.1));
            if(s.hasRoot(1))
                    System.out.println("1 is a root of s");
            else
                    System.out.println("1 is not a root of s");
            Polynomial t = p1.multiply(p2);
            System.out.println("t(1) = " + t.evaluate(1));
            System.out.println("t(2) = " + t.evaluate(2));
            System.out.println("t(0) = " + t.evaluate(0));
            p1.saveToFile("jyfnb");
            File jyf = new File("jyfnb");
            Polynomial j = new Polynomial(jyf);
            Polynomial s2 = j.add(p2);
            System.out.println("s2(0.1) = " + s2.evaluate(0.1));
            if(s2.hasRoot(1))
                    System.out.println("1 is a root of s2");
            else
                    System.out.println("1 is not a root of s2");
    }
}