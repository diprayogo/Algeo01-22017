package operators;

// import operators.*;

public class Determinant {
    
    // public static double detMatrixSegitiga(Matrix Mat){
    // }

    public static double detKofaktor(Matrix Mat){
        // Prekondisi : isSquare, isAugmented
        if(Mat.getCol() == 1){              // Basis
            return Mat.getELMT(0,0);
        }
        else{                               // Rekursi
            int j = 0; 
            double temp = 0 ;
            for(j = 0; j < Mat.getCol(); j ++){
                temp += Mat.getELMT(0,j) * detKofaktor(Mat.getKofaktor(Mat, 0, j));
            }
            return temp;
        }                               
    }
}
