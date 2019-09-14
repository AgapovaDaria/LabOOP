package functions;

public class SqrFunction implements MathFunction{

    public SqrFunction(){

    }

    @Override
    public double apply(double x) {
        return Math.pow(x,2);
    }
}
