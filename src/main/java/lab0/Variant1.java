package lab0;



public class Variant1 {

    public enum DAY_OF_WEEK{
        MONDAY, TUESDAY, WEDNESDAY
    }
    public  static class FirstTaskResult {
        public int Sum;
        public int Min;
        public int Mul;
        public float Div;
    }
    /**
     *
     * @param k is square side
     * @return perimeter
     */
    public FirstTaskResult inputOutputTask(int k, int p) {
        FirstTaskResult result = new FirstTaskResult();

        result.Sum = Math.abs(k)+Math.abs(p);
        result.Min = Math.abs(k)-Math.abs(p);
        result.Mul = Math.abs(k)*Math.abs(p);
        result.Div = Math.abs(k)/Math.abs(p);

        return result;
    }

    /**
     *
     * @param k is distance in cm
     * @return distance in m
     */

    public FirstTaskResult integerNumbersTask(int k, int c, int v) {
        FirstTaskResult result = new FirstTaskResult();

        result.Sum = k+c+v;
        result.Mul = k*c*v;

        return result;
    }

    /**
     *
     * @param number1
     * @return true, if number is possitive
     */
    public boolean booleanTask(int number1, int number2) {

        if(((number1%2 == 0) && (number2%2 == 0))||((number1%2 != 0) && (number2%2 != 0)))
        return true;
        else
            return false;
    }


    /**
     *
     * @param param1 is integer number
     * @return transformed number
     */
    public int ifTask(int param1,int param2) {

        if( param1 > param2 )
            param1=param2;
        else if ( param2 > param1 )
            param2=param1;
        else {
            param1 = 0;
            param2 = 0;
        }
        return 0;
    }


    /**
     *
     * @param number1
     * @return day of week day represented number1
     */
    public DAY_OF_WEEK switchTask(int number1) {
        return DAY_OF_WEEK.MONDAY;
    }


    /**
     *
     * @param n is integer number
     * @return approximated value of exp(1)
     */
    public double forTask(int n) {
        assert n >0: "Argument should be more than zero";
        return 0;
    }


    public int whileTask(int a, int b) {
        assert (a >0 && b > 0): "Argument should be more than zero";
        return 0;
    }

    public double arrayTask(double[] array) {
        return 0;
    }

    /**
     *
     * @param array
     * @param k1
     * @param k2
     * @return transformed array where row with indexes k1 and k2 was changed
     */
    public int[][]  twoDimensionArrayTask(int[][] array, int k1, int k2) {
        //return null;
        return array;
    }


}
