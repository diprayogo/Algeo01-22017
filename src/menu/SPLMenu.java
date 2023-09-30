package menu;

import java.util.Scanner;
import operators.Matrix;
import operators.SPL;

public class SPLMenu {
  private static Scanner scanner = new Scanner(System.in);

  public static void menu() {
    System.out.println();
    System.out.println("                  ANDA BERADA DI SUBMENU SISTEM PERSAMAAN LINEAR");
    System.out.println("1. Metode eleminasi Gauss");
    System.out.println("2. Metode eleminasi Gauss-Jordan");
    System.out.println("3. Metode matriks balikan");
    System.out.println("4. Kaidah Cramer");
    System.out.print("Pilih Metode penyelesaian :");

    SPL spl = new SPL();
    String result = new String();

    boolean inputValid = true;
    int method = 0;
    try {
      method = scanner.nextInt();
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.printf("\nAnda akan menginput matriks augmented dengan ukuran n x m.\n");
    System.out.print("Masukkan jumlah n: ");
    int nRow = scanner.nextInt();
    System.out.print("Masukkan jumlah m: ");
    int nCol = scanner.nextInt();
    Matrix matrixAugmented = new Matrix(nRow, nCol);
    matrixAugmented.readMatrix(nRow, nCol);

    switch (method) {
      case 1:
        result = spl.metodeGauss(matrixAugmented);
        break;
      case 2:
        result = spl.metodeGaussJordan(matrixAugmented);
        break;
      case 3:
        result = spl.metodeMatriksBalikan(matrixAugmented);
        break;
      case 4:
        result = spl.kaidahCramer(matrixAugmented);
        break;
      default:
        inputValid = false;
        System.out.println("Input tidak valid. Mohon hanya masukkan 1 sampai 4.\n");
    }
    if (inputValid) {
      System.out.println(result);
    }
  }
}