package menu;

import operators.*;
import java.util.Scanner;

public class InterpolasiMenu {
  private static Scanner scanner = new Scanner(System.in);

  public static Matrix pointToMatrix(int n) {
    Matrix matInput = new Matrix(n, 2);
    Matrix matPolinom = new Matrix(n, n + 1);

    matInput.readMatrix(n, 2);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n + 1; j++) {
        if (j == n) {
          matPolinom.setELMT(i, j, matInput.getELMT(i, 1));
        } else {
          matPolinom.setELMT(i, j, Math.pow(matInput.getELMT(i, 0), j));
        }
      }
    }

    return matPolinom;
  }

  public static double taksirNilai(double[] root, double x) {
    double sum = 0;
    for (int i = 0; i < root.length; i++) {
      sum += root[i] * Math.pow(x, i);
    }
    return sum;
  }

  public void menuInterpolasi() {
    System.out.print("Masukkan jumlah titik: ");
    int n = scanner.nextInt();
    Matrix m = pointToMatrix(n);
    SPL spl = new SPL();

    System.out.print("Masukkan nilai x: ");
    double x = scanner.nextDouble();

    m.printMatrix(m.getRow(), m.getCol());

    spl.metodeGauss(m);

    double[] root = spl.metodeGauss(m);
    double taksir = taksirNilai(root, x);

    System.out.print("f(" + x + ") = ");
    System.out.printf("%.4f\n", taksir);
  }
}
