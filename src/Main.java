
// text-based dan CLI (wajib)

import java.util.Scanner;

import menu.DeterminantMenu;
import operators.*;
import menu.SPLMenu;

public class Main {
  static Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
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
          SPLMenu.menu();
          break;// REDIRECT KE SPL
        case 2:
          DeterminantMenu.menu();
          break;// REDIRECT KE DETERMINAN
        case 3:
          break;// DST
        case 4:
          InterpolasiPolinom interpolasi = new InterpolasiPolinom();
          interpolasi.menuInterpolasi();
          break;
        case 5:
          break;
        case 6:
          break;
        case 7:
          isRunning = false;
          break; // DEFAULT TIDAK PERLU, sudah dicheck di pilih Menu
      }
    }
  }
}