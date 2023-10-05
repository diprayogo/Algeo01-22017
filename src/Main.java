// text-based dan CLI (wajib)

import java.util.Scanner;

import menu.BicubicMenu;
import menu.DeterminantMenu;
import menu.InterpolasiMenu;
import menu.InverseMenu;
import menu.RegresiMenu;
import menu.SPLMenu;
import operators.*;

public class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    //testing();
    System.out.println("SELAMAT DATANG DI MATRIX CALCULATOR ");
    boolean isRunning = true;

    while (isRunning) {
      int menu = 0;
      boolean isValidInput = false;
      do {
        System.out.println();
        System.out.println("                                 ANDA BERADA DI MENU UTAMA");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic Spline");
        System.out.println("6. Regresi Linear Berganda");
        System.out.println("7. Keluar");
        System.out.print("Pilih Menu: ");
        try {
          menu = scanner.nextInt();
          if (menu >= 1 && menu <= 7) {
            isValidInput = true; // Input valid, keluar dari loop
          } else {
            System.out.println("Input yang Anda masukkan di luar range 1-7. Tolong input ulang");
            scanner.nextLine(); // Hapus baris yang tidak valid

          }
        } catch (java.util.InputMismatchException e) {
          System.out.println("Input yang Anda masukkan bukan angka bulat. Coba lagi.");
          scanner.nextLine(); // Hapus baris yang tidak valid
        }
      } while (!isValidInput);

      // PRINT SELAMAT DATANG KE MATRIX CALCULATOR//
      // PRINT MENU YANG ADA
      switch (menu) {
        case 1:
          SPLMenu.menu(); // REDIRECT KE SPL
          break; 
        case 2:
          DeterminantMenu.menu(); // REDIRECT KE DETERMINAN
          break; 
        case 3:
          InverseMenu.menu(); // REDIRECT KE INVERS
          break; 
        case 4:
          InterpolasiMenu.menu(); // REDIRECT KE INTERPOLASI
          break;
        case 5:
          BicubicMenu.menu(); // REDIRECT KE BICUBIC
          break;
        case 6:
          RegresiMenu.menu(); // REDIRECT KE REGRESI
          break;

        case 7:
          System.out.println("TERIMA KASIH SUDAH MENGGUNAKAN KALKULATOR MATRIX");
          isRunning = false;
          break; // DEFAULT TIDAK PERLU, sudah dicheck di pilih Menu
      }
    }
  }

  public static void testing() {
    Matrix m1 = new Matrix(2, 2);
    Matrix m2 = new Matrix(2, 2);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        m1.setELMT(i, j, i+j+1);
        m2.setELMT(i, j, i+j+4);
      }
    }

    // Konstruktor, setter, getter: cukup aman
    // getMatConst: harusnya getRow() tanpa -1 -> return nonAugmented

    // System.out.println("m1: ");
    // Matrix.printMatrix(m1);
    // System.out.println("m2: ");
    // Matrix.printMatrix(m2);
    // System.out.println("m1 x m2: ");
    // Matrix.printMatrix(m1.mult(m2));
    
    
    // System.out.println("m1 -> 2*r2: ");
    // double[] tes = m1.multipliedRowELMT(1, 2);
    // for (int i = 0; i < m1.getCol(); i++){
    //   System.out.printf("%f ", tes[i]);
    // }
    // System.out.println("m3 proc r1 + 2*r2: (refrence to m1)");
    // Matrix m3 = m1;
    // m3.addRow(0, 1, 2);
    // Matrix.printMatrix(m3);

    // System.out.println("\nTEST INVERSE functions and methods:\nm3 with 2*row1:");
    // m3.pMultRow(0, 2);
    // Matrix.printMatrix(m3);


    // Matrix m4 = new Matrix(m3.getRow(), m3.getCol());
    // m4 = m3.inverseGaussJordan();
    // // System.out.println("m3 adjoin: ");
    // // Matrix.printMatrix(m3.getAdj());
    // System.out.println("m4 balikan pake GJ: ");
    // Matrix.printMatrix(m4);
    // m4 = m4.inverseGaussJordan();


    // System.out.println("m4 balik lagi:");
    // m4 = m4.inverseEkspansiCofactor();
    // m4 = m4.inverseEkspansiCofactor(); // harusnya balik lagi ke m4 awal klo m4 diinvers lagi
    // Matrix.printMatrix(m4);

    // Matrix m5 = new Matrix(6, 6);
    // System.out.println("try read matrix m5 6X6: ");
    // m5.readMatrix(6, 6);
    // Matrix.printMatrix(m5);
    // System.out.println("3*m5 (scalar mult): ");
    // m5.scalarMultiply(3);;
    // Matrix.printMatrix(m5);
    // System.out.println("m5 after strictGauss: ");
    // m5.strictGauss();
    // Matrix.printMatrix(m5);
    // System.out.println("m5 after strictGaussJordan: ");
    // m5.strictGaussJordan();
    // Matrix.printMatrix(m5);
    
    // System.out.println("\nOVERWRITING M3, not reference m1 more, but from input: ");
    // System.out.println("try read matrix m3 3x3 by input: ");
    // m3 = Matrix.readMatSquare();
    // Matrix.printMatrix(m3);
    // System.out.println("try read matrix m3 2x3: ");
    // m3.readMatrix(2, 3);
    // Matrix.printMatrix(m3);
    // System.out.println("swap row 1<->2 matrix m3 2x3: ");
    // m3.swapRow(0, 1);
    // Matrix.printMatrix(m3);
    // System.out.println("subtract row1 -= 2*row2 matrix m3 2x3: ");
    // m3.subtractRow(0, 1, 2);
    // Matrix.printMatrix(m3);
    // System.out.println("isSquare m1 & m3: ");
    // System.out.print(m1.isSquare());
    // System.out.println(m3.isSquare());

    
    // System.out.println("try transpose matrix m3 to 3x2: ");
    // m3.transpose();
    // Matrix.printMatrix(m3);
    // System.out.println("try copy2 matrix m1 to m3: ");
    // m3.copyMatrix(m1);
    // Matrix.printMatrix(m3);
    // System.out.println("try copy matrix m2 to m3: ");
    // m3 = Matrix.copyMatrix2(m2);
    // Matrix.printMatrix(m3);

  }
}