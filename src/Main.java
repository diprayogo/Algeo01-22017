
// text-based dan CLI (wajib)

import java.util.Scanner;
import operators.*;
import menu.*;

public class Main {
  public static void main(String[] args) {
    System.out.println("SELAMAT DATANG DI MATRIX CALCULATOR ");
    boolean isRunning = true;
    
    while (isRunning){
      Scanner scanner = new Scanner(System.in);
      int menu = 0 ;
      boolean isValidInput = false;

      do {
        System.out.println();
        System.out.println("                                 ANDA BERADA DI MENU UTAMA");
        System.out.println("1. Sistem Persamaan Linear");
        System.out.println("2. Determinan");
        System.out.println("3. Matriks Balikan");
        System.out.println("4. Interpolasi Polinom");
        System.out.println("5. Interpolasi Bicubic");
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
        Matrix matrix = new Matrix(3, 4);
        SPL spl = new SPL();
        matrix.readMatrix(3, 4);
        matrix.printMatrix(3, 4);
        spl.metodeGauss(matrix);
        matrix.printMatrix(3, 4);
        break;// REDIRECT KE SPL
        case 2:
        DeterminantMenu.menu();
        break;// REDIRECT KE DETERMINAN
        case 3:
        break;// DST
        case 4:
        break;
        case 5:
          break;
        case 6:
        Matrix Mat = new Matrix(20, 4);
        Mat.readMatrix(20, 4);
        Mat.printMatrix(20, 4);
        System.out.println("\n");
        RegresiLinearBerganda.getNormalEst(Mat).printMatrix(4, 5);;
        break;

        case 7:
        System.out.println("TERIMA KASIH SUDAH MENGGUNAKAN KALKULATOR MATRIX");
        isRunning = false ;
        break; // DEFAULT TIDAK PERLU, sudah dicheck di pilih Menu
      }
    }
  } 
}