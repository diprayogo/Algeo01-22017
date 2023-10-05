package menu;

import operators.RegresiLinearBerganda;
import myUtils.*;

public class RegresiMenu {
    public static void menu(){
        System.out.println();
        System.out.println("                   ANDA BERADA DI SUBMENU Regresi Linear Berganda");

        boolean fromFile  = false; 
        fromFile = myUtils.inputSrcValidation();

        String output ;
        output = RegresiLinearBerganda.isRegresiFromFile(fromFile);
        myUtils.strToFile(output);
    }
}
