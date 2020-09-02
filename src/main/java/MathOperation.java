
@FunctionalInterface
interface IMathFunction{
    int calculate(int a,int b);
        }
public class MathOperation {
    public static void main(String[] args) {
        IMathFunction add = Integer::sum;
        IMathFunction multiply = (x, y) -> x * y;
        IMathFunction devide = (int x, int y) -> x / y;
        System.out.println("Addition is " + add.calculate(6, 3));
        System.out.println("multiplication is " + multiply.calculate(6, 3));
    }
}

