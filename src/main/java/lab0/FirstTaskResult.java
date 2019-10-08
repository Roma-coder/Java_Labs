package lab0;

public  class FirstTaskResult {
    public int sum;
    public int min;
    public int mul;
    public float div;

    public FirstTaskResult()
    {
    }

    @Override
    public String toString() {
        return "FirstTaskResult{" +
                "Sum=" + sum +
                ", Min=" + min +
                ", Mul=" + mul +
                ", Div=" + div +
                '}';
    }

    public FirstTaskResult(int sum, int min, int mul, float div) {
        this.sum = sum;
        this.min = min;
        this.mul = mul;
        this.div = div;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FirstTaskResult that = (FirstTaskResult) o;

        if (sum != that.sum) return false;
        if (min != that.min) return false;
        if (mul != that.mul) return false;
        return Float.compare(that.div, div) == 0;
    }

    @Override
    public int hashCode() {
        int result = sum;
        result = 31 * result + min;
        result = 31 * result + mul;
        result = 31 * result + (div != +0.0f ? Float.floatToIntBits(div) : 0);
        return result;
    }
}
