package myUtils;

import operators.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class myUtils {
    private static Scanner scanner = new Scanner(System.in);

    // Mengembalikan nilai true jika ingin melakukan input dari file dan false jika tidak.
    // Input akan diulangi hingga masukan dari user valid.
    public static boolean inputSrcValidation(){
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
        return fromFile ;
    }
    
    // Membuat presisi sebuah double sesuai dengan decPlaces
    public static double setPrec(double num, int decPlaces) {
        BigDecimal bd = new BigDecimal(num).setScale(decPlaces, RoundingMode.HALF_UP);
        double res = bd.doubleValue();
        return res;
    }

    // return convert dari string ke double
    public static double strToDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException e) {
            System.out.println(("Masukan tidak valid. Tidak bisa di-parse ke double"));
            return Double.NaN;
        }
    }

    // Mengembalikan Matrix dengan elemen-elemen yang dibaca dari sebuah file.
    // Perlu diingat bahwa input untuk beberapa menu bukanlah hanya matrix, tetapi
    // ada parameter tambahan pada file
    // Jadi, perlu dilakukan split terlebih dahulu pada baris terakhir sebelum
    // dipakai.
    public static Matrix readMatrixFromFile() {
        String fileName = new String();
        boolean pathValid = false;

        // buat matrix res dengan colom 1000 dan row 1000 dan elemennya belum diisi
        Matrix res = new Matrix(0, 0);
        res.setCol(1000);
        res.setRow(1000);
        res.setMat(1000, 1000);
        while (!pathValid) {
            System.out.print("Masukkan nama file: ");
            fileName = scanner.nextLine();
            try {
                File file = new File(
                        "D:\\⭐⭐Kuliah Informatika⭐⭐\\Algeo\\Tubes\\Algeo01-22017\\test\\input\\" + fileName);
                Scanner fScanner = new Scanner(file);
                int i = 0, realCol = 0; // realcol adalah banyak kolom baris pertama matrix.
                double val = 0;
                boolean fileContentValid = true;
                while (fScanner.hasNextLine() & fileContentValid) {
                    String s = fScanner.nextLine();
                    String[] temp = s.split("\\s+");
                    if (i == 0)
                        realCol = temp.length; //
                    for (int j = 0; j < temp.length; j++) {
                        val = myUtils.strToDouble(temp[j]);
                        if (Double.isNaN(val)) {
                            System.out.println("Tolong input ulang file yang berisi nilai double yang valid.\n");
                            fileContentValid = false ;
                            break;
                        } else
                            res.setELMT(i, j, val);
                    }
                    i++;
                }
                if (fileContentValid) {
                    res.setRow(i);
                    res.setCol(realCol);
                    fScanner.close();
                    pathValid = true;
                }

            } catch (FileNotFoundException e) {
                System.out.println("File tidak ditemukan. Harap pastikan nama file benar dan file berada di dalam folder D:\\.Kuliah\\.Semester 3\\ALGEO\\TUBES\\Algeo01-22017\\test\\input\n");
            }
        }
        return res;
    }

    // Menyimpan Matrix m ke dalam sebuah file jika pengguna ingin menyimpan file.
    public static void matrixToFile(Matrix mSimpan) throws IOException {
        boolean isValid = false;
        String validation;
        while (!isValid) {
            System.out.print("Apakah Anda ingin menyimpan hasil ke dalam sebuah file (Y/N)? ");
            validation = (scanner.nextLine()).toUpperCase();
            switch (validation) {
                case "Y":
                    String path = new String();
                    System.out.print("Masukkan nama file(tanpa format ekstensi file): ");
                    path = "D:\\⭐⭐Kuliah Informatika⭐⭐\\Algeo\\Tubes\\Algeo01-22017\\test\\output\\"
                            + scanner.nextLine()
                            + ".txt";
                    File file = new File(path);
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
                    System.out.printf("Anda telah menyimpan matrix ke %s", path);
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
                    String path = new String();
                    System.out.print("Masukkan nama file(tanpa format ekstensi file): ");
                    path = "D:\\⭐⭐Kuliah Informatika⭐⭐\\Algeo\\Tubes\\Algeo01-22017\\test\\output\\"
                            + scanner.nextLine()
                            + ".txt";
                    File file = new File(path);
                    try {
                        FileWriter fWriter = new FileWriter(file);
                        fWriter.write(s);
                        fWriter.close();
                    } catch (IOException e) {
                        System.out.print(e.getMessage());
                    }
                    System.out.printf("Anda telah menyimpan string ke %s\n", path);
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
        System.out.println();
    }
}
