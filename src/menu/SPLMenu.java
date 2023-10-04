package menu;

import operators.*;
import java.util.*;

import myUtils.myUtils;

public class SPLMenu {
  // private static Scanner scanner = new Scanner(System.in);

  // public static void menu() {
  // System.out.println();
  // System.out.println(" ANDA BERADA DI SUBMENU SISTEM PERSAMAAN LINEAR");

  // System.out.print("Pilih Metode penyelesaian :");

  // boolean inputValid = true;
  // int method = 0;
  // try {
  // method = scanner.nextInt();
  // } catch (Exception e) {
  // e.printStackTrace();
  // }

  // switch (method) {
  // case 1:
  // result = spl.metodeGauss(matrixAugmented);
  // break;
  // case 2:
  // result = spl.metodeGaussJordan(matrixAugmented);
  // break;
  // case 3:
  // result = spl.metodeMatriksBalikan(matrixAugmented);
  // break;
  // case 4:
  // result = spl.kaidahCramer(matrixAugmented);
  // break;
  // default:
  // inputValid = false;
  // System.out.println("Input tidak valid. Mohon hanya masukkan 1 sampai 4.\n");
  // }
  // if (inputValid) {
  // System.out.println(result);
  // }
  // }
  private static Scanner scanner = new Scanner(System.in);

  public static void menu() {
    System.out.println();
    System.out.println("                          ANDA BERADA DI SUBMENU SPL");

    // SPL spl = new SPL();
    String result = new String();

    boolean fromFile ;
    fromFile = myUtils.inputSrcValidation();

    boolean inputValid = false;
    int method = 0;
    System.out.println("\n1. Metode eliminasi Gauss");
    System.out.println("2. Metode eliminasi Gauss-Jordan");
    System.out.println("3. Metode matriks balikan");
    System.out.println("4. Kaidah Cramer");
    while (!inputValid) {
      System.out.print("Pilih Metode penyelesaian :");
      try {
        method = scanner.nextInt();
        switch (method) {
          case 1:
            if (fromFile)
              result = operators.SPL.metodeGauss(myUtils.readMatrixFromFile());
            else
              result = operators.SPL.metodeGauss(Matrix.readMatNXM());
            inputValid = true;
            break;

          case 2:
            if (fromFile)
              result = operators.SPL.metodeGaussJordan(myUtils.readMatrixFromFile());
            else
              result = operators.SPL.metodeGaussJordan(Matrix.readMatNXM());
            inputValid = true;
            break;

          case 3:
            if (fromFile)
              result = operators.SPL.metodeInverse(myUtils.readMatrixFromFile());
            else
              result = operators.SPL.metodeInverse(Matrix.readMatNXM());
            inputValid = true;
            break;

          case 4:
            if (fromFile)
              result = operators.SPL.kaidahCramer(myUtils.readMatrixFromFile());
            else
              result = operators.SPL.kaidahCramer(Matrix.readMatNXM());
            inputValid = true;
            break;

          default:
            System.out.println("Input tidak valid. Mohon hanya masukkan 1 sampai 4.\n");
        }
      } catch (java.util.InputMismatchException e) {
        scanner.nextLine();
        System.out.println("Input tidak valid. Mohon hanya masukkan 1 sampai 4.\n");
      }
    }

    if (inputValid) {
      System.out.println(result);
      myUtils.strToFile(result);
      System.out.println("\n");
    }
  }
}