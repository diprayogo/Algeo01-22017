import java.util.Scanner;

import Matrix.*;

// text-based dan CLI (wajib)
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int menu;

    menu = pilihMenu(scanner);
    switch (menu) {
      case 1:
        Matrix matrix = new Matrix(3, 4);
        matrix.readMatrix(3, 4);
        SPL.metodeGauss(matrix);
        matrix.printMatrix(3, 4);

        break;
      case 2:
        break;
      case 3:
        break;
      case 4:
        break;
      case 5:
        break;
      case 6:
        break;
      case 7:
        break;
      case 8:
        break; // submenu 1.1
      case 9:
        break; // submenu 1.2
      case 10:
        break; // submenu 1.3
      case 11:
        break; // submenu 1.4
      case 12:
        break; // submenu 2.1 determinan reduksi baris
      case 13:
        break; // submenu 2.2 determinan ekspansi kofaktor
      case 14:
        break; // submenu 3.1 matriks balikan dengan gauss jordan [A|I] ~ OBE ~ [I|A^-1]
      case 15:
        break; // submenu 3.2 matriks balikan dengan kofaktor adjoin determinan, cek dulu var
               // det != 0
    }
  }

  static int pilihMenu(Scanner scanner) {
    return scanner.nextInt();
  }
}
