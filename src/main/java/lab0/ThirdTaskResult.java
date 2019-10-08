package lab0;

public class ThirdTaskResult {
    public int p1;
    public int p2;

    public ThirdTaskResult(){

    }

    public ThirdTaskResult(int p1, int p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public String toString() {
        return "ThirdTaskResult{" +
                "p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThirdTaskResult that = (ThirdTaskResult) o;

        if (p1 != that.p1) return false;
        return p2 == that.p2;
    }

    @Override
    public int hashCode() {
        int result = p1;
        result = 31 * result + p2;
        return result;
    }
}
