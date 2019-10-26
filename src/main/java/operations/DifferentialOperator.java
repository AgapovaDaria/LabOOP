package operations;

import ru.ssau.tk.Lab2.LabOOP.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
