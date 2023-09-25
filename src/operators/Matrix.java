package operators;

import java.util.Scanner;

public class Matrix {

  // Atribut
  // atribut default ukuran baris dan kolom, amankah sesuai harapan?
  int rowSize;
  int colSize;
  double[][] Mat = new double[rowSize][colSize];

  // atribut isSquare untuk Matrix persegi
  boolean isSquare = (rowSize == colSize) ? true : false;
  boolean isAugmented = false; // default

  // Methods
  // ------------------------------ KONSTRUKTOR ------------------------------//
  public Matrix(int rowSize, int colSize) {
    this.rowSize = rowSize;
    this.colSize = colSize;
    this.Mat = new double[rowSize][colSize];

    int i, j;
    for (i = 0; i < rowSize; i++) {
      for (j = 0; j < colSize; j++) {
        this.Mat[i][j] = 0;
      }
    }
  }

  // overloading for default params
  Matrix() {
    this.rowSize = 10;
    this.colSize = 10;

    int i, j;
    for (i = 0; i < rowSize; i++) {
      for (j = 0; j < colSize; j++) {
        this.Mat[i][j] = 0;
      }
    }
  }

  // ------------------------------ GETTER ------------------------------//
  public int getRow() { // mendapatkan rowSize
    return this.rowSize;
  }

  public int getCol() { // mendapatkan colSize
    return this.colSize;
  }

  public double getELMT(int i, int j) { // mendapatkan ELMT pada baris i, kolom j
    return (this.Mat[i][j]);
  }

  public double[] getRowELMT(int i) {
    return (this.Mat[i]);
  }

  public Matrix getMatConst(Matrix Mat) { // Mengembalikan Matrix konstanta
    // Prekondisi : isAugmented
    Matrix MatConst = new Matrix(Mat.getRow(), Mat.getCol() - 1);
    int i, j;
    for (i = 0; i < Mat.getRow() - 1; i++) {
      for (j = 0; j < Mat.getCol() - 1; j++) {
        setELMT(i, j, getELMT(i, j));
      }
    }
    return MatConst;
  }

  // ------------------------------ SETTER ------------------------------//
  public void setRow(int row) { // Mengisi rowSize
    this.rowSize = row;
  }

  public void setCol(int col) { // Mengisi colSize
    this.colSize = col;
  }

  public void setELMT(int i, int j, double val) { // Mengisi ELMT(i,j) dengan val
    this.Mat[i][j] = val;
  }

  public void setRowELMT(int row, double[] rowELMT) {
    this.Mat[row] = rowELMT;
  }

  // ------------------------------ OPERATORS ------------------------------//
  public void transpose() {
    int i, j;
    Matrix tempMat = new Matrix(getCol(), getRow());

    for (i = 0; i < getRow(); i++) {
      for (j = 0; j < getCol(); j++) {
        tempMat.setELMT(i, j, getELMT(i, j));;
      }
    }
    
    copyMatrix(tempMat);
  }
  
   public void copyMatrix(Matrix Mat){
    this.rowSize = Mat.getRow();
    this.colSize = Mat.getCol();
    int i, j ; 
    for(i = 0; i< this.getRow(); i ++ ){
      for(j = 0 ; j < this.getCol() ; j ++){
        setELMT(i, j , Mat.getELMT(i, j));
      }
    }
  }
  
  public static Matrix getMinorMat(Matrix Mat, int a, int b) { // Mengembalikan Matrix Minor
    // Prekondisi : isAugmented
    Matrix MinorMat = new Matrix(Mat.getRow() - 1, Mat.getCol() - 2);
    int i, j;
    for (i = 0; i < MinorMat.getRow(); i++) {
      for (j = 0; j < MinorMat.getCol(); j++) {
        if (i >= a && j >= b) {
          MinorMat.setELMT(i, j, Mat.getELMT(i + 1, j + 1));
        } else if (i >= a) {
          MinorMat.setELMT(i, j, Mat.getELMT(i + 1, j));
        } else if (j >= b) {
          MinorMat.setELMT(i, j, Mat.getELMT(i, j + 1));
        }
      }
    }
    return MinorMat;
  }
  
  public static double getKofaktor(Matrix Mat, int a, int b){    // Mengembalikan Kofaktor
    // Prekondisi isAugmented
    return detKofaktor((Matrix.getMinorMat(Mat, a, b)));
  }

  public void swapRow(int row1, int row2) {
    // swap doang gess
    double[] temp;
    temp = getRowELMT(row1);
    setRowELMT(row1, getRowELMT(row2));
    setRowELMT(row2, temp);
  }

  public void subtractRow(int row, int subtractorRow, double subtractorMagnitude) {
    for (int j = 0; j < getCol(); j++) {
      setELMT(row, j, getELMT(row, j) - (getELMT(subtractorRow, j) * subtractorMagnitude));
    }
  }

  // ------------------------------ MENCARI DETERMINAN  -------------------------------- //
  public static double detKofaktor(Matrix Mat) {
    // Prekondisi : isSquare, isAugmented
    if (Mat.getCol() == 1) { // Basis
      return Mat.getELMT(0, 0);
    } else { // Rekursi
      int j = 0;
      double det = 0;
      for (j = 0; j < Mat.getCol(); j++) {
        int sign = ((j) % 2 == 0) ? 1 : -1;
        det += sign * Mat.getELMT(0, j) * detKofaktor(Matrix.getMinorMat(Mat, 0, j));
      }
      return det;
    }
  }

  public static double detMatrixSegitiga(Matrix Mat) {
    // Prekondisis isSquare, isAugmented
    Matrix matDet = new Matrix();
    matDet.getMatConst(Mat);
    double det, subtractorMagnitude;
    int i = 0, j = 0, cntSwap = 0, k;

    // Membentuk matrix segitiga bawah
    while (i < matDet.getRow() && j < matDet.getCol()) {
      int pivotRow = i;
      // mencari leading 1
      while (pivotRow < matDet.getRow() && matDet.getELMT(pivotRow, j) == 0) {
        pivotRow++;
      }

      if (matDet.getELMT(pivotRow, j) != 0) { // Ada elemen bukan 0 pada row pivotRow
        if (pivotRow != i) {
          matDet.swapRow(pivotRow, i);
          cntSwap++;
        }
        for (k = i + 1; k < matDet.getRow(); k++) {
          subtractorMagnitude = matDet.getELMT(k, j) / matDet.getELMT(i, j);
          matDet.subtractRow(k, pivotRow, subtractorMagnitude);
        }
        i++;
      }
      j++;
    }

    // Terbentuk matrix segitiga bawah
    int l;
    det = (cntSwap % 2 == 0) ? 1 : -1;
    for (l = 1; l < matDet.getCol(); l++) {
      det *= matDet.getELMT(l, l);
    }
    return det;
  }

  //-------------------- MENCARI INVERS --------------------//
  public static Matrix adj(Matrix Mat){
    Matrix Madj = new Matrix(Mat.getRow(), Mat.getCol()-1);
    int i, j;
    for(i = 0 ; i < Madj.getRow(); i ++){
      for(j = 0 ; j < Madj.getCol(); j ++){
        Madj.setELMT(i, j, Matrix.getKofaktor(Mat,i,j));
      }
    }
    Madj.transpose();
    return Madj;
  }
}