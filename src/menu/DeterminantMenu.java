package menu;
import java.util.*;
import operators.*;
import myUtils.myUtils;


public class DeterminantMenu {
    private static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println();
        System.out.println("                          ANDA BERADA DI SUBMENU DETERMINAN");

<<<<<<< HEAD

=======
        boolean inputValid = false, fromfile = true;
>>>>>>> d39ac29d29d73cb72986734671a20996e4e241e5
        double det = 0 ;
        int method = 0  ;
        boolean inputValid = false, fromFile;
        fromFile = myUtils.inputSrcValidation();


        inputValid = false ; 

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
                System.out.printf("Determinan: %.4f", det);
                myUtils.strToFile(String.format("Determinan: %.4f", det));
                System.out.println("\n");
            }
        }
    }  
}