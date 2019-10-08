package lab0;

public class FirthTaskResult {
    public int k;
    public int sum;

    public FirthTaskResult (){

    }
    public FirthTaskResult(int k, int sum) {
        this.k = k;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "FirthTaskResult{" +
                "k=" + k +
                ", sum=" + sum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirthTaskResult that = (FirthTaskResult) o;

        if (k != that.k) return false;
        return sum == that.sum;
    }

    @Override
    public int hashCode() {
        int result = k;
        result = 31 * result + sum;
        return result;
    }
}
