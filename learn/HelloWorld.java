
// import java.util.*;
import javax.swing.*;

public class HelloWorld {

  public static void main(String[] args) {
    // System.out.println("Hello World");

    // Scanner input = new Scanner(System.in);
    // System.out.print("nama: ");
    // String namaCons = input.nextLine();

    // System.out.print("usia: ");
    // int usiaCons = input.nextInt();

    // System.out.println("Nama saya " + namaCons + ", " + usiaCons + " tahun");

    // int i;
    // for (i = 1; i <= usiaCons; i++) {
    //   System.out.println("i = " + i);
    // }
    
    // deklarasi + inisialisasi by GUI input
    String nama = JOptionPane.showInputDialog("nama: ");
    String usia = JOptionPane.showInputDialog("usia: ");
    int UsiaInt = Integer.parseInt(usia);

    String intro = "Nama saya " + nama + ", " + UsiaInt + " tahun";
    System.out.println(intro);
    JOptionPane.showMessageDialog(null, intro);
    System.exit(0);
    
  }
}