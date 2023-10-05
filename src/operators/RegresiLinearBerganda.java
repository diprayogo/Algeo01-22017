package operators;
import java.util.Scanner;

import myUtils.myUtils;

public class RegresiLinearBerganda {
    private static Scanner scanner = new Scanner(System.in);

    // Mengembalikan Persamaan Normal Estimasi dari Matrix Maug
    public static Matrix getNormalEst(Matrix Maug){
        // Mengembalikan Persamaan Normal Estimasi dari Matrix Maug
        Matrix Mnorm  = new Matrix(Maug.getCol(), Maug.getCol() + 1);
        int i,j,n;
        for(i = 0; i< Mnorm.getRow(); i++){
            for(j = 0 ; j < Mnorm.getCol(); j++){
                Mnorm.setELMT(i, j , 0);
                for(n = 0; n < Maug.getRow(); n++){
                    if (i == 0 && j == 0) Mnorm.setELMT(i, j ,  Mnorm.getELMT(i, j) + 1);
                    else if (i == 0) Mnorm.setELMT(i, j ,  Mnorm.getELMT(i, j) + Maug.getELMT(n , j -1));
                    else if (j == 0) Mnorm.setELMT(i, j ,  Mnorm.getELMT(i, j) + Maug.getELMT(n , i -1 ));
                    else Mnorm.setELMT(i, j ,  Mnorm.getELMT(i, j) + (Maug.getELMT(n , i -1 ) * (Maug.getELMT(n , j -1))));
                }
            }
        }
        return Mnorm ; 
    }

    // Mengembalikan hasil taksiran dari Matrix param berdasarkan data yang ingin di taksir pada Matrix input
    public static double taksir(Matrix param, Matrix input){
        // Mengembalikan hasil taksiran dari Matrix param berdasarkan data yang ingin di taksir pada Matrix input
        // param.getCol() = input.getCol()  + 1 
        double res = 0 ;
        for (int j = 0; j < input.getCol(); j++) {
            res += param.getELMT(0, j + 1) * input.getELMT(0, j);
        }
        res += param.getELMT(0, 0);
        return res ;
    }

    // Mengembalikan output berupa string yang berisi persamaan dan hasil taksiran dari data peubah yang di-input  oleh user. 
    // Parameter fromFile akan menentukan source input user. Jika true, maka dari file, jika false maka dari keyboard
    public static String isRegresiFromFile(boolean fromFile){
        Matrix ret = new Matrix(0, 0) ;
        Matrix peubah = new Matrix(0, 0) ;
        
        if (fromFile){
            // isi matrix ret dengan nilai dari file. Matrix ret akan berisi matrix yang ingin dicari dan nilai-nilai 
            // peubah untuk taksiran
            ret = myUtils.uniqueMatrixFromFile();
            // isi matrix peubah dengan baris terakhir matrix ret
            peubah.setRow(1); peubah.setCol(ret.getCol() -1); peubah.setMat(peubah.getRow(), peubah.getCol());
            int j ;
            for (j = 0 ; j < peubah.getCol(); j ++){
                peubah.setELMT(0, j, ret.getELMT(ret.getRow() -1, j));
            }
            
            // hilangkan baris terakhir ret
            ret.setRow(ret.getRow() - 1);
            Matrix.printMatrix(ret); 
            
        } else{
            // I.S : Matrix ret dan Matrix peubah bebas
            //F.S : Matrix ret berisi sample data yang terdiri dari peubah dan hasilnya, matrix peubah berisi nilai yang ingin ditaksir

            int n, m;
            System.out.print("Masukkan jumlah peubah (n): ");
            n = scanner.nextInt();
            System.out.print("Masukkan jumlah sampel (m): ");
            m = scanner.nextInt();
            System.out.println("Masukkan x1i, x2i,... xni, y: ");

            // create matrix ret
            // minta input matrix dari user, readUniqueMat akan mengeluarkan matrix yang row-nya unique
            // ret.setCol(n +1); ret.setRow(m); ret.setMat(n+ 1, m);
            // ret = Matrix.readUniqueMat(m, n + 1);

            // create matrix ret
            ret.setRow(m); ret.setCol(n + 1); ret.setMat(ret.getRow(), ret.getCol());
            ret.readUniqueMat(m, n + 1);

            // peubah
            peubah.setRow(1); peubah.setCol(n); peubah.setMat(peubah.getRow(), peubah.getCol());
            System.out.printf("Masukkan nilai yang ingin ditaksir yaitu sebanyak %d peubah\n", n);
            int j;
            for (j = 0; j < peubah.getCol(); j++) {
                System.out.printf("Masukkan nilai peubah ke-%d : ", j + 1);
                peubah.setELMT(0, j, scanner.nextDouble());
            }
        }
        
        // Matrix ret dan matrix peubah dari masukan file ataupun keyboard akan sama
        Matrix Mnorm = new Matrix(0,0);
        Mnorm = RegresiLinearBerganda.getNormalEst(ret);
        Mnorm.strictGaussJordan();

        Matrix param = new Matrix(0 , 0) ;
        param.setRow(1); param.setCol(Mnorm.getCol() -1); param.setMat(param.getRow(), param.getCol());
        int j; 

        String persamaan = "y = " ;
        for (j = 0 ; j < param.getCol(); j ++){
            if (j == 0) persamaan += String.format("%.5f", Mnorm.getELMT(j, Mnorm.getCol()-1), j);
            else persamaan += String.format("%.5f x%d", Mnorm.getELMT(j, Mnorm.getCol()-1), j);
            param.setELMT(0, j, Mnorm.getELMT(j, Mnorm.getCol()-1));
            if (j != param.getCol() -1 ) persamaan += " + ";
        }

        String taksiran = "f(";
        for (j = 0 ; j < peubah.getCol(); j ++){
            taksiran += String.format("%.5f", peubah.getELMT(0, j));
            if (j == peubah.getCol() -1 ) taksiran += ")" ;
            else taksiran += ", " ;
        }

        double hasilTaksiran = RegresiLinearBerganda.taksir(param, peubah);
        taksiran += String.format(" = %f", hasilTaksiran);

        String output = persamaan + ", " + taksiran;
        System.out.println(output);
        Matrix.printMatrix(ret);
        return output ;
    }
}



