package lab0;

public class Main {
    public static void main(String[] args){
        double x1 = Math.pow(10, -4);
        double x2 = Math.pow(10, -6);
        double x3 = Math.pow(10, -8);
        double a = x1;
        double nx1 = Math.sqrt(x1 * x1 + x2 * x2 + x3 * x3);
        double nx2 = a * Math.sqrt((x1 / a)*(x1 / a) + (x2 / a)*(x2 / a) + (x3 / a)*(x3 / a));
        float nx3 = (float)Math.sqrt(x1 * x1 + x2 * x2 + x3 * x3);
        float nx4 = (float)(a * Math.sqrt((x1 / a)*(x1 / a) + (x2 / a)*(x2 / a) + (x3 / a)*(x3 / a)));
        System.out.println("Double:");
        System.out.println(nx1);
        System.out.println(nx2);
        System.out.println("Float:");
        System.out.println(nx3);
        System.out.println(nx4);
        System.out.println("Error:");
        System.out.println(Math.abs(nx1 - nx3));
        System.out.println(Math.abs(nx2 - nx4));
    }



}
