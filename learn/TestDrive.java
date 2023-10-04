package learn;
import operators.*;

public class TestDrive {
  public static void main(String[] args){
    Matrix m = operators.Bicubic.getBicubicPolynomialMatrix();
    operators.Matrix.printMatrix(m);
  }
}
