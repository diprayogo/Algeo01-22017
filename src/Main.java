

// text-based dan CLI (wajib)

import java.util.Scanner;

public class Main {
  static int pilihMenu() {
    Scanner scanner = new Scanner(System.in);
    int menu = 0;
    boolean isValidInput = false;

    do {
        System.out.print("Masukkan sebuah bilangan bulat: ");
        try {
            menu = scanner.nextInt();
            if (menu >= 1 && menu <= 7){
                isValidInput = true; // Input valid, keluar dari loop
            }
            else{
              System.out.println("Input yang Anda masukkan di luar range 1-7. Tolong input ulang");
              scanner.nextLine(); // Hapus baris yang tidak valid

            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Input yang Anda masukkan bukan angka bulat. Coba lagi.");
            scanner.nextLine(); // Hapus baris yang tidak valid
        }
    } while (!isValidInput);
    scanner.close();

    return menu;
  }
  public static void main(String[] args) {

    boolean isRunning = true;
    //  PRINT SELAMAT DATANG KE MATRIX CALCULATOR//
    do{ 
        // PRINT MENU YANG ADA 
        int menu = pilihMenu();
        switch (menu) {
          case 1: break;// REDIRECT KE SPL
          case 2: break;// REDIRECT KE DETERMINAN 
          case 3: break;// DST
          case 4: break;
          case 5: break;
          case 6: break;
          case 7: break; // DEFAULT TIDAK PERLU, sudah dicheck di pilih Menu
        }
      }while (isRunning) ;
  }
}