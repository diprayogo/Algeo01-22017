package myUtils;

import operators.*;
import java.io.*;
import java.util.Scanner;

public class myUtils {
    private static Scanner scanner = new Scanner(System.in);

    // return convert dari string ke double
    public static double strToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Nilai double invalid: " + str, e);
        }
    }

    // Mengembalikan Matrix dengan elemen-elemen yang dibaca dari sebuah file.
    // Perlu diingat bahwa input untuk beberapa menu bukanlah hanya matrix, tetapi
    // ada parameter tambahan pada file
    // Jadi, perlu dilakukan split terlebih dahulu pada baris terakhir sebelum
    // dipakai.
    public static Matrix readMatrixFromFile() {
        String fileName = new String();
        System.out.print("Masukkan nama file: ");
        fileName = scanner.nextLine();

        // buat matrix res dengan colom 1000 dan row 1000 dan elemennya belum diisi
        Matrix res = new Matrix(0, 0);
        res.setCol(1000);
        res.setRow(1000);
        res.setMat(1000, 1000);
        try {
            File file = new File("D:\\⭐⭐Kuliah Informatika⭐⭐\\Algeo\\Tubes\\Algeo01-22017\\test\\input\\" + fileName);
            System.out.println(file);
            Scanner fScanner = new Scanner(file);
            int i = 0;
            int realCol = 0;
            while (fScanner.hasNextLine()) {
                String s = fScanner.nextLine();
                String[] temp = s.split("\\s+");
                if (i == 0)
                    realCol = temp.length; //
                for (int j = 0; j < temp.length; j++) {
                    res.setELMT(i, j, myUtils.strToDouble(temp[j]));
                }
                i++;
            }
            res.setRow(i);
            res.setCol(realCol);
            fScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return res;
    }

    // Menyimpan Matrix m ke dalam sebuah file jika pengguna ingin menyimpan file.
    public static void matrixToFile(Matrix mSimpan) throws IOException {
        boolean isValid = false;
        System.out.println();
        String validation;
        while (!isValid) {
            System.out.print("Apakah Anda ingin menyimpan hasil ke dalam sebuah file (Y/N)? ");
            validation = (scanner.nextLine()).toUpperCase();
            switch (validation) {
                case "Y":
                    String fileName = new String();
                    System.out.print("Masukkan nama file(tanpa format ekstensi file): ");
                    fileName = "D:\\⭐⭐Kuliah Informatika⭐⭐\\Algeo\\Tubes\\Algeo01-22017\\test\\output\\"
                            + scanner.nextLine() + ".txt";
                    File file = new File(fileName);
                    try {
                        FileWriter fWriter = new FileWriter(file);
                        for (int i = 0; i < mSimpan.getRow(); i++) {
                            for (int j = 0; j < mSimpan.getCol(); j++) {
                                String temp = String.format("%.5f", mSimpan.getELMT(i, j) + 0.00000000);
                                fWriter.write(temp);
                                if (j != mSimpan.getCol() - 1) {
                                    fWriter.write(" ");
                                }
                            }
                            fWriter.write("\n");
                        }
                        fWriter.close();
                    } catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                    System.out.println("Anda telah menyimpan matrix ke file");
                    isValid = true;
                    break;
                case "N":
                    System.out.println("Anda tidak melakukan penyimpanan hasil.");
                    isValid = true;
                    break;
                default:
                    System.out.println("Masukan tidak valid. Mohon hanya masukkan (Y/N)\n");
            }
        }
    }

    // I.S. : String s terdefinisi
    // F.S. : String s disimpan dalam sebuah file jika pengguna ingin menyimpan
    // file.
    public static void strToFile(String s) {
        System.out.println();
        boolean isValid = false;
        System.out.print("Apakah Anda ingin menyimpan hasil ke dalam sebuah file (Y/N)? ");
        String validation = (scanner.nextLine()).toUpperCase();
        while (!isValid) {
            switch (validation) {
                case "Y":
                    String fileName = new String();
                    System.out.print("Masukkan nama file(tanpa format ekstensi file): ");
                    fileName = "D:\\⭐⭐Kuliah Informatika⭐⭐\\Algeo\\Tubes\\Algeo01-22017\\test\\output\\"
                            + scanner.nextLine() + ".txt";
                    File file = new File(fileName);
                    try {
                        FileWriter fWriter = new FileWriter(file);
                        fWriter.write(s);
                        fWriter.close();
                    } catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                    System.out.println("Anda telah menyimpan string ke file");
                    isValid = true;
                    break;
                case "N":
                    System.out.println("Anda tidak melakukan penyimpanan hasil.");
                    isValid = true;
                    break;
                default:
                    System.out.println("Input tidak dikenali. Mohon hanya masukkan (Y/N)");
                    break;
            }
        }
    }
}
