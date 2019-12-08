package lab0;
import java.util.*;

public class Main {


    private static double F1(double x, double y) {
        double res;

        res = 2 * x * x + 3 * y * y - 6 * y - 4;

        return res;
    }

    private static double F2(double x, double y) {
        double res;

        res = x * x - 3 * y * y + 6 * x - 2;

        return res;
    }

    private static double DetJ(double x, double y) {
        double res;

        res = -24 * x * y - (6 * y - 6)*(2 * x + 4);

        return res;
    }

    private static double DetJ1(double x, double y) {
        double res;

        res = 6 * y * F1(x, y) + (6 * y - 6) * F2(x, y);

        return res;
    }

    private static double DetJ2(double x, double y) {
        double res;

        res = -4 * x * F2(x, y) + (2 * x + 4) * F1(x, y);

        return res;
    }

    public static void main(String args[]) {

        double delta1, delta2,  xk, yk, eps, q = 0;
        int k;


        Scanner scanner = new Scanner(System.in);



        System.out.println("Enter eps:");
        eps = scanner.nextDouble();
        System.out.println("Enter start approximation:");
        xk = scanner.nextDouble();
        yk = scanner.nextDouble();
        k = 0;


        do {
            if (DetJ(xk, yk) != 0) {
                delta1 = DetJ1(xk, yk) / DetJ(xk, yk);
                delta2 = DetJ2(xk, yk) / DetJ(xk, yk);

                if (Math.abs(delta1) > Math.abs(delta2))
                    q = Math.abs(delta1);
                else
                    q = Math.abs(delta2);

                xk += delta1;
                yk += delta2;

                k++;
            }
            else
                System.out.println("Enter another start approximation:");





        } while (Math.abs(q) > eps);

        System.out.println("xk:" + xk );
        System.out.println("yk:" + yk );
        System.out.println("Count of iteration:"+ k );

        System.out.println("F1:" + F1(xk, yk));
        System.out.println("F2:" + F2(xk, yk));






    }
}