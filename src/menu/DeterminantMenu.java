package menu;
import java.util.*;
import operators.*;
import myUtils.myUtils;


public class DeterminantMenu {
    private static Scanner scanner = new Scanner(System.in);
    
    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU DETERMINAN");

        double det = 0 ;
        int method = 0  ;
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

        inputValid = false ;  // re-assing untuk validasi metode
        System.out.println("\n1. Matrix Segitiga");
        System.out.println("2. Determinan Kofaktor");
        while (!inputValid){
            System.out.print("Pilih Metode penyelesaian :");
            try {
                method = scanner.nextInt();
                switch (method) {
                    case 1:
                        if (fromFile) det = operators.Matrix.detMatrixSegitiga(myUtils.readMatrixFromFile());
                        else det = operators.Matrix.detMatrixSegitiga(Matrix.readMatSquare());
                        inputValid = true ;
                        break;
                        
                    case 2:
                        if (fromFile) det = operators.Matrix.detKofaktor(myUtils.readMatrixFromFile());
                        else det = operators.Matrix.detKofaktor(Matrix.readMatSquare());
                        inputValid = true ;
                        break;
                    default:
                        System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
                }
            }
            catch (java.util.InputMismatchException e) {
                scanner.nextLine(); 
                System.out.println("Input tidak valid. Mohon hanya masukkan 1 atau 2.\n");
            }
        }

        if (inputValid){
            if (!Double.isNaN(det)){
                System.out.printf("Determinan: %.5f", det);
                myUtils.strToFile(String.format("Determinan: %.5f", det));
            }
        }
    }  
}