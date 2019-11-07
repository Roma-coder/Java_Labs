package lab0;
import java.util.*;

public class Main {
    public static void main(String args[]) {
        int n, m;
        int i, j;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter number of equestions");
        n = scanner.nextInt();
        System.out.println("Enter number of variables");
        m = scanner.nextInt();
        m += 1;
        float[][] matrix = new float[n][m];
        System.out.println("Enter matrix elements");



        for (i = 0; i < n; i++)
            for (j = 0; j < m; j++)
                matrix[i][j] = scanner.nextFloat();

        System.out.println("Matrix:");
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < m; j++)
                System.out.println(matrix[i][j] + " ");

            System.out.println("");
        }
        System.out.println("");
        /*Гаус--------------------------------------------------------------------------------------------------------------------*/
        float tmp;
        float[] xx = new float[100];
        int k;

        for (i = 0; i < n; i++)
        {
            tmp = matrix[i][i];
            for (j = n; j >= i; j--)
                matrix[i][j] /= tmp;
            for (j = i + 1; j < n; j++)
            {
                tmp = matrix[j][i];
                for (k = n; k >= i; k--)
                    matrix[j][k] -= tmp * matrix[i][k];
            }
        }
        System.out.println("Matrix:");
        for (i = 0; i < n; i++)
        {
            for (j = 0; j < m; j++)
                System.out.println(matrix[i][j] + " ");
            System.out.println("");

        }
        System.out.println("");
        /*зворотній хід-------------------------------------------------------------------------------------------------------------*/
        xx[n - 1] = matrix[n - 1][n];
        for (i = n - 2; i >= 0; i--)
        {
            xx[i] = matrix[i][n];
            for (j = i + 1; j < n; j++) xx[i] -= matrix[i][j] * xx[j];
        }

        for (i = 0; i < n; i++)
            System.out.println(xx[i] + " ");
        System.out.println("");
    }
}