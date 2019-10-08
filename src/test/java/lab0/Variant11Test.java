package lab0;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class Variant11Test {

    Variant11 variant11 = new Variant11();

    @Test(dataProvider = "inputProvider")
    public void inputTest(int k, int p, FirstTaskResult expected) {
        assertEquals(variant11.inputOutputTask(k, p), expected);
    }

    @DataProvider
    public Object[][] inputProvider() {
        return new Object[][] { { 10, 2, new FirstTaskResult(12, 8, 20, 5) }, { -6, 1 , new FirstTaskResult(7, 5, 6, 6) } };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "integerProvider")
    public void integerTest(int k, int p, int c, SecondTaskResult expected) {
        assertEquals(variant11.integerNumbersTask(k, p, c), expected);
    }

    @DataProvider
    public Object[][] integerProvider() {
        return new Object[][] { {3, 5, 4, new SecondTaskResult(12, 60)} };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "booleanProvider")
    public void booleanTest(int number1, int number2, boolean expected) {
        assertEquals(variant11.booleanTask(number1, number2), expected);
    }

    @DataProvider
    public Object[][] booleanProvider() {
        return new Object[][] { {2, 4, true},{3, 5, true},{2, 9, false} };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "ifProvider")
    public void ifTest(int param1, int param2, ThirdTaskResult expected) {
        assertEquals(variant11.ifTask(param1, param2), expected);
    }

    @DataProvider
    public Object[][] ifProvider() {
        return new Object[][] { {5, 2, new ThirdTaskResult(5,5) },{7, 3, new ThirdTaskResult(7,7) },{4, 4, new ThirdTaskResult(0,0) } };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "switchProvider")
    public void switchTest(Variant11.worldSide C, int n1, int n2, Variant11.worldSide expected ) {
        assertEquals(variant11.switchTask(C, n1, n2), expected);
    }

    @DataProvider
    public Object[][] switchProvider() {
        return new Object[][] { {Variant11.worldSide.s, 1, 1, Variant11.worldSide.u},{Variant11.worldSide.s, -1, -1, Variant11.worldSide.u} };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "forProvider")
    public void forTest(int n, int expected) {
        assertEquals(variant11.forTask(n), expected);
    }

    @DataProvider
    public Object[][] forProvider() {
        return new Object[][] { {3, 86} , {1,5}};
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "whileProvider")
    public void whileTest(int n, FirthTaskResult  expected) {
        assertEquals(variant11.whileTask(n), expected);
    }

    @DataProvider
    public Object[][] whileProvider() {
        return new Object[][] { {3, new FirthTaskResult(3,6) },{7, new FirthTaskResult(4,10)} };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(dataProvider = "arrayProvider")
    public void arrayTest(int n, int k, int A[], int expected[]) {
        assertEquals(variant11.arrayTask(n, k, A), expected);
    }

    @DataProvider
    public Object[][] arrayProvider() {
        return new Object[][] { {5, 3, new int[]{2, 4, 6, 1, 1}, new int[]{6} } };
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

  @Test(dataProvider = "twoDimensionArrayProvider")
    public void twoDimensionArrayTest(int n, int m, int arr[][], int expected[][]) {
        assertEquals(variant11.twoDimensionArrayTask(n, m, arr), expected);
}

    @DataProvider
    public Object[][] twoDimensionArrayProvider() {

        int[][] input = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
                };
        int[][] output = {
                {1, 2, 3},
                {6, 5, 4},
                {7, 8, 9}
        };
        return new Object[][] { {3, 3, input, output} };
    }

}
