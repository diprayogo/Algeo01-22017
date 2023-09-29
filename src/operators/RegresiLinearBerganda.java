package operators;
import operators.*;

public class RegresiLinearBerganda {
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
        return Mnorm;
    }


}
