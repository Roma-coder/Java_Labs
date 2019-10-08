package lab0;

public class SecondTaskResult {
    public int sum;
    public int mul;



    public SecondTaskResult()
    {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SecondTaskResult that = (SecondTaskResult) o;

        if (sum != that.sum) return false;
        return mul == that.mul;
    }

    @Override
    public String toString() {
        return "SecondTaskResult{" +
                "Sum=" + sum +
                ", Mul=" + mul +
                '}';
    }

    public SecondTaskResult(int sum, int mul) {
        this.sum = sum;
        this.mul = mul;
    }

    @Override
    public int hashCode() {
        int result = sum;
        result = 31 * result + mul;
        return result;
    }
}
