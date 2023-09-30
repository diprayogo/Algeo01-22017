package operators;
import java.util.Scanner;


import myUtils.myUtils;

public class RegresiLinearBerganda {
    private static Scanner scanner = new Scanner(System.in);

    public static void readKey(Matrix ret, Matrix peubah) {
        int n, m;
        System.out.print("Masukkan jumlah peubah (n): ");
        n = scanner.nextInt();
        System.out.print("Masukkan jumlah sampel (m): ");
        m = scanner.nextInt();
        System.out.println("Masukkan x1i, x2i,... xni, y: ");

        // create matrix ret
        ret.setRow(m); ret.setCol(n + 1); ret.setMat(ret.getRow(), ret.getCol());
        ret.readMatrix(ret.getRow(), ret.getCol());

        peubah.setRow(1); peubah.setCol(n); peubah.setMat(peubah.getRow(), peubah.getCol());
        
        System.out.printf("Masukkan nilai yang ingin ditaksir yaitu sebanyak %d peubah\n", n);
        int j;
        for (j = 0; j < peubah.getCol(); j++) {
            System.out.printf("Masukkan nilai peubah ke-%d : ", j + 1);
            peubah.setELMT(0, j, scanner.nextDouble());
        }
    }

    public static Matrix getNormalEst(Matrix Maug){
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
        Mnorm.strictGaussJordan();
        return Mnorm ; 
    }

    public static double taksir(Matrix param, Matrix input){
        // param.getCol() = input.getCol()  + 1 
        double res = 0 ;
        for (int j = 0; j < input.getCol(); j++) {
            res += param.getELMT(0, j + 1) * input.getELMT(0, j);
        }
        res += param.getELMT(0, 0);
        return res ;
    }

    public static String inputFromFile(boolean fromFile){
        Matrix ret = new Matrix(0, 0) ;
        Matrix peubah = new Matrix(0, 0) ;
        
        if (fromFile){
            // isi matrix ret dengan nilai dari file. Matrix ret akan berisi matrix yang ingin dicari dan nilai-nilai 
            // peubah untuk taksiran
            ret = myUtils.readMatrixFromFile();

            // isi matrix peubah dengan baris terakhir matrix ret
            peubah.setRow(1); peubah.setCol(ret.getCol() -1); peubah.setMat(peubah.getRow(), peubah.getCol());
            int j ;
            for (j = 0 ; j < peubah.getCol(); j ++){
                peubah.setELMT(0, j, ret.getELMT(ret.getRow() -1, j));
            }

            // hilangkan baris terakhir ret
            ret.setRow(ret.getRow() - 1);
            
            
        }
        else{
            // masukan dari keyboard akan langsung menghasilkan matrix ret dan matrix peubah
            RegresiLinearBerganda.readKey(ret, peubah);
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

        return output ;

    }
}


