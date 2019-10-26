package operations;

import ru.ssau.tk.Lab2.LabOOP.functions.Point;
import ru.ssau.tk.Lab2.LabOOP.functions.TabulatedFunction;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.Lab2.LabOOP.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    TabulatedFunctionFactory factory;

    public void setFactory(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedFunctionFactory getFactory(){
        return factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i=0;
        for (Point point: tabulatedFunction){
            points[i++]=point;
        }
        return points;
    }
}
