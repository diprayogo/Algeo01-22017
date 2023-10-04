package operators;

import java.util.Scanner;

import myUtils.myUtils;

public class InterpolasiPolinom {
  private static Scanner scanner = new Scanner(System.in);

  public static Matrix pointToMatrix(Matrix matPoint, int n) {
    Matrix matPolinom = new Matrix(n, n + 1);

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n + 1; j++) {
        if (j == n) {
          matPolinom.setELMT(i, j, matPoint.getELMT(i, 1));
        } else {
          matPolinom.setELMT(i, j, Math.pow(matPoint.getELMT(i, 0), j));
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

  public static String inputFromFile(boolean fromFile) {
    String result = new String();
    Matrix matrixPoint = new Matrix(0, 0);
    double titik = 0;

    if (fromFile) {
      // isi matrix matrixPoint dengan nilai dari file. Matrix matrixPoint akan berisi
      // matrix yang
      // ingin dicari
      matrixPoint = myUtils.readMatrixFromFile();

      titik = matrixPoint.getELMT(matrixPoint.getRow() - 1, 0);

      // hilangkan baris terakhir matrixPoint
      matrixPoint.setRow(matrixPoint.getRow() - 1);

    } else {
      // masukan dari keyboard akan langsung menghasilkan matrix matrixPoint
      matrixPoint = InterpolasiPolinom.readFromKeyboard();
      System.out.print("Masukkan nilai x: ");
      titik = scanner.nextDouble();

    }

    Matrix m = pointToMatrix(matrixPoint, matrixPoint.getRow());

    double[] rootSolution = new double[m.getRow()];
    for (int i = 0; i < rootSolution.length; i++) {
      rootSolution[i] = 1;
    }

    double[] b = new double[m.getRow()];
    for (int i = 0; i < b.length; i++) {
      b[i] = m.getELMT(i, m.getCol() - 1);
    }

    SPL.metodeGaussJordan(m);

    for (int i = 0; i < rootSolution.length; i++) {
      rootSolution[i] = m.getELMT(i, m.getCol() - 1);
    }

    double taksir = taksirNilai(rootSolution, titik);

    // Mencetak polinomial interpolasi
    result = String.format("Polinom interpolasi yang melalui ke-%d buah titik tersebut adalah p_%d(x) = ",
        matrixPoint.getRow(), matrixPoint.getRow() - 1);

    boolean isFirstTerm = true;

    for (int i = 0; i < rootSolution.length; i++) {
      if (rootSolution[i] != 0) {
        if (!isFirstTerm) {
          if (rootSolution[i] > 0) {
            result += " + ";
          } else {
            result += " - ";
          }
        } else {
          isFirstTerm = false;
        }

        double coef = Math.abs(rootSolution[i]);
        if (i == 0) {
          result += String.format("%.4f", coef);
        } else {
          result += String.format("%.4f", coef);
          result += "x";
          if (i > 1) {
            result += "^" + (i);
          }
        }
      }
    }

    result += "\nf(" + titik + ") = ";
    result += String.format("%.4f", taksir);

    return result;

  }

  public static Matrix readFromKeyboard() {

    System.out.print("Masukkan jumlah titik: ");
    int n = scanner.nextInt();
    Matrix matInput = new Matrix(n, 2);
    matInput.readMatrix(n, 2);

    return matInput;

  }
}