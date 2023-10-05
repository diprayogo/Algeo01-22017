package menu;

import myUtils.myUtils;
import operators.InterpolasiPolinom;

public class InterpolasiMenu {

  public static void menu() {
    System.out.println();
    System.out.println("                   ANDA BERADA DI SUBMENU Interpolasi Polinom");

    boolean  fromFile  ;
    fromFile = myUtils.inputSrcValidation();

    String output;
    output = InterpolasiPolinom.isInterpolasiFromFile(fromFile);
    System.out.println(output);
    myUtils.strToFile(output);
  }
}
