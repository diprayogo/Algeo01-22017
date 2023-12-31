package menu;

import operators.*;
import java.util.*;

import myUtils.myUtils;

public class SPLMenu {
  private static Scanner scanner = new Scanner(System.in);

  public static void menu() {
    System.out.println();
    System.out.println("                          ANDA BERADA DI SUBMENU SPL");

    // SPL spl = new SPL();
    String result = new String();

    boolean inputValid = false, fromFile = true;
    int inputSrc = 0;
    System.out.println("1. Masukan dari file");
    System.out.println("2. Masukan dari keyboard ");
    while (!inputValid) {
        System.out.print("Pilih Sumber input : ");
        try {
            inputSrc = scanner.nextInt();
            switch (inputSrc) {
            case 1: // fromFile = true;
                inputValid = true;
                break;
            case 2:
                inputValid = true;
                fromFile = false;
                break;
            default:
                System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
        }
    }

    // re-assign value dari inputValid untuk validasi masukan metode
    inputValid = false;
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
    }

    
  }
}