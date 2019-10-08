package lab0;

public class Variant11 {

    public enum worldSide {
        s, v, u, z
    }

    /**
     *Даны два ненулевых числа. Найти сумму, разность, произведение и частное их модулей.
     * @param k number not zero
     * @param p number not zero
     * @return sum,min,mul,div
     */
    public FirstTaskResult inputOutputTask(int k, int p) {
        FirstTaskResult result = new FirstTaskResult();

        result.sum = Math.abs(k)+Math.abs(p);
        result.min = Math.abs(k)-Math.abs(p);
        result.mul = Math.abs(k)*Math.abs(p);
        result.div = Math.abs(k)/(float)Math.abs(p);

        return result;
    }

    /**
     *Дано трехзначное число. Найти сумму и произведение его цифр.
     * @param k first number
     * @param c second number
     * @param v third number
     * @return sum,mul
     */
    public SecondTaskResult integerNumbersTask(int k, int c, int v) {
        SecondTaskResult result = new SecondTaskResult();

        result.sum = k+c+v;
        result.mul = k*c*v;

        return result;
    }


    /**
     *Даны два целых числа: A, B. Проверить истинность высказывания: «Числа A и B имеют одинаковую четность».
     * @param number1 first number
     * @param number2 second number
     * @return true or false
     */
    public boolean booleanTask(int number1, int number2) {

        if((number1%2 == 0 && number2%2 == 0)||((number1%2 != 0) && (number2%2 != 0)))
            return true;
        else
            return false;
    }

    /**
     * Даны две переменные целого типа: A и B. Если их значения не равны, то присвоить каждой переменной
     * большее из этих значений, а если равны, то присвоить переменным нулевые значения.
     * Вывести новые значения переменных A и B.
     * @param param1 first number
     * @param param2 second number
     * @return param1 and param2
     */
    public ThirdTaskResult ifTask(int param1,int param2) {


        if( param1 > param2 )
            return new ThirdTaskResult(param1,param1) ;
        if ( param2 > param1 )
            return new ThirdTaskResult(param2,param2) ;

        return new ThirdTaskResult(0,0) ;
    }

    /**
     * Локатор ориентирован на одну из сторон света («С» — север, «З» — запад, «Ю» — юг, «В» — восток)
     * и может принимать три цифровые команды поворота: 1 — поворот налево, –1 — поворот направо,
     * 2 — поворот на 180°. Дан символ C — исходная ориентация локатора и целые числа N1 и N2 — две
     * посланные команды. Вывести ориентацию локатора после выполнения этих команд.
     * @param c start
     * @param n1 first command
     * @param n2 second command
     * @return c or v or u or z
     */
    public worldSide switchTask(worldSide c, int n1, int n2) {

        int start = 0;
        switch (c) {
            case v:
                start = 1;
                break;
            case u:
                start = 2;
                break;
            case z:
                start = 3;
                break;
        }

        int result = Math.abs((start + n1 + n2) % 4);

        switch (result) {
            case 0:
                return worldSide.s;
            case 1:
                return worldSide.v;
            case 2:
                return worldSide.u;
        }
        return worldSide.z;
    }


    /**
     *Дано целое число N (> 0). Найти сумму
     * N2 + (N + 1)2 + (N + 2)2 + … + (2·N)2
     * (целое число).
     * @param n int number
     * @return sum
     */
    public double forTask(int n) {

    int sum=0;
       for(int i=0;i<=n;i++){
        sum+=(n+i)*(n+i);

       }
        return sum ;
    }

    /**
     * Дано целое число N (> 1). Вывести наименьшее из целых чисел K, для которых сумма 1 + 2 + … + K
     * будет больше или равна N, и саму эту сумму.
     * @param n int number
     * @return k,sum
     */
    public FirthTaskResult  whileTask(int n) {

         int k = 1;
         int sum = 0;
        while (sum <= n) {
            sum += k++;
        }
        return new FirthTaskResult(k-1,sum) ;
    }

    /**
     *Array11. Дан массив a размера N и целое число K (1 Ј K Ј N). Вывести элементы массива с порядковыми номерами,
     *  кратными K: AK, A2·K, A3·K, … . Условный оператор не использовать.
     * @param n size
     * @param k serial number
     * @param a array
     * @return result
     */
    public int[] arrayTask(int n, int k, int a[]) {

        int index = 0;
        int[] result = new int[n / k];
        for(int i=k-1;i<n;i+=k){
            result[index++] = a[i];
        }
        return result;
    }

    /**
     *  Дана матрица размера M ґ N. Вывести ее элементы в следующем порядке: первая строка слева направо,
     * вторая строка справа налево, третья строка слева направо, четвертая строка справа налево и т. д.
     * @param n length matrix
     * @param m width matrix
     * @param arr array
     * @return temp matrix
     */
    public int[][]  twoDimensionArrayTask( int n, int m, int arr[][]) {
        int[][] temp = new int[n][m];
       for(int i=0;i<n;i++) {

           if(i%2==0) {
               for(int j=0;j<m;j++) {
                   temp[i][j] = arr[i][j];
               }
           }
           else
               for(int j=m-1; j>=0; j--) {
                   temp[i][m-j-1] = arr[i][j];
               }
       }
        return temp;
    }

}
