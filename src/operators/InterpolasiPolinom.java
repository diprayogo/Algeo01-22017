package operators;

import java.util.Scanner;

public class InterpolasiPolinom {
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
    boolean isRunning = true;

    while (isRunning) {
      System.out.print("Masukkan jumlah titik: ");
      int n = scanner.nextInt();
      Matrix m = pointToMatrix(n);
      SPL spl = new SPL();

      double[] rootSolution = new double[m.getRow()];
      for (int i = 0; i < rootSolution.length; i++) {
        rootSolution[i] = 1;
      }

      double[] b = new double[m.getRow()];
      for (int i = 0; i < b.length; i++) {
        b[i] = m.getELMT(i, m.getCol() - 1);
      }

      System.out.print("Masukkan nilai x: ");
      double x = scanner.nextDouble();

      spl.metodeGaussJordan(m);

      for (int i = 0; i < rootSolution.length; i++) {
        rootSolution[i] = m.getELMT(i, m.getCol() - 1);
      }

      // for (int i = 0; i < rootSolution.length; i++) {
      // System.out.println(rootSolution[i] + "\n");
      // }
      double taksir = taksirNilai(rootSolution, x);

      System.out.print("f(" + x + ") = ");
      System.out.printf("%.4f\n", taksir);

      System.out.println("Lanjut masukkan nilai x? : ");
      System.out.println("1. Ya");
      System.out.println("2. Tidak");
      int pil = scanner.nextInt();
      if (pil == 2) {
        isRunning = false;
      }
    }
  }
}