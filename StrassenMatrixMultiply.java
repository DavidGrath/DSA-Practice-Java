class StrassenMatrixMultiply {

   private final static int SIZE = 8;
   
   public static void main(String[] args) {
      Integer[][] A = new Integer[SIZE][];
      Integer[][] B = new Integer[SIZE][];
      for(int i = 0; i < SIZE; i++) {
         A[i] = GeneralUtils.generateArray(SIZE, 0, 20);
         B[i] = GeneralUtils.generateArray(SIZE, 0, 20);
      }
      Matrix aMatrix = new Matrix(A);
      Matrix bMatrix = new Matrix(B);
      Matrix cMatrix = aMatrix.multiply(bMatrix);
      Matrix cRecursive = multiplySquareMatricesRecursive(aMatrix, bMatrix, SIZE);
      Matrix cStrassen = strassenMultiply(aMatrix, bMatrix, SIZE);
      System.out.println("A: ");
      aMatrix.print();
      System.out.println("B: ");
      bMatrix.print();
      System.out.println("C: ");
      cMatrix.print();
      System.out.println("CRecursive: ");
      cRecursive.print();
      System.out.println("CStrassen: ");
      cStrassen.print();
   }
   
   
   private static Matrix multiplySquareMatricesRecursive(Matrix A, Matrix B, int size) {
      Matrix C = new Matrix(size);
      if(size == 1) C.assignValue(A.element(0, 0) * B.element(0, 0), 0, 0);
      else {
         int half = (int) Math.floor(size/2);
         Matrix A_1_1 = A.subMatrix(0, 0, half, half);
         Matrix A_1_2 = A.subMatrix(0, half, half, half);
         Matrix A_2_1 = A.subMatrix(half, 0, half, half);
         Matrix A_2_2 = A.subMatrix(half, half, half, half);
         
         Matrix B_1_1 = B.subMatrix(0, 0, half, half);
         Matrix B_1_2 = B.subMatrix(0, half, half, half);
         Matrix B_2_1 = B.subMatrix(half, 0, half, half);
         Matrix B_2_2 = B.subMatrix(half, half, half, half);
         
         C.assignValues(multiplySquareMatricesRecursive(A_1_1, B_1_1, half)
            .add(multiplySquareMatricesRecursive(A_1_2, B_2_1, half)).toArray(), 0, 0);
            
         C.assignValues(multiplySquareMatricesRecursive(A_1_1, B_1_2, half)
            .add(multiplySquareMatricesRecursive(A_1_2, B_2_2, half)).toArray(), 0, half);
            
         C.assignValues(multiplySquareMatricesRecursive(A_2_1, B_1_1, half)
            .add(multiplySquareMatricesRecursive(A_2_2, B_2_1, half)).toArray(), half, 0);
            
         C.assignValues(multiplySquareMatricesRecursive(A_2_1, B_1_2, half)
            .add(multiplySquareMatricesRecursive(A_2_2, B_2_2, half)).toArray(), half, half);
      }
      return C;
   }
   
   private static Matrix strassenMultiply(Matrix A, Matrix B, int size) {
      System.out.println("STRASSEN");
      Matrix C = new Matrix(size);
      if(size == 1) C.assignValue(A.element(0, 0) * B.element(0, 0), 0, 0);
      else {
         int half = (int) Math.floor(size/2);
         Matrix A_1_1 = A.subMatrix(0, 0, half, half);
         Matrix A_1_2 = A.subMatrix(0, half, half, half);
         Matrix A_2_1 = A.subMatrix(half, 0, half, half);
         Matrix A_2_2 = A.subMatrix(half, half, half, half);
         
         Matrix B_1_1 = B.subMatrix(0, 0, half, half);
         Matrix B_1_2 = B.subMatrix(0, half, half, half);
         Matrix B_2_1 = B.subMatrix(half, 0, half, half);
         Matrix B_2_2 = B.subMatrix(half, half, half, half);
         
         Matrix S1 = B_1_2.subtract(B_2_2);
         Matrix S2 = A_1_1.add(A_1_2);
         Matrix S3 = A_2_1.add(A_2_2);
         Matrix S4 = B_2_1.subtract(B_1_1);
         Matrix S5 = A_1_1.add(A_2_2);
         Matrix S6 = B_1_1.add(B_2_2);
         Matrix S7 = A_1_2.subtract(A_2_2);
         Matrix S8 = B_2_1.add(B_2_2);
         Matrix S9 = A_1_1.subtract(A_2_1);
         Matrix S10 = B_1_1.add(B_1_2);
         
         Matrix P1 = A_1_1.multiply(S1);
         Matrix P2 = S2.multiply(B_2_2);
         Matrix P3 = S3.multiply(B_1_1);
         Matrix P4 = A_2_2.multiply(S4);
         Matrix P5 = S5.multiply(S6);
         Matrix P6 = S7.multiply(S8);
         Matrix P7 = S9.multiply(S10);
                 
         C.assignValues(P5.add(P4).subtract(P2).add(P6).toArray(), 0, 0);
            
         C.assignValues(P1.add(P2).toArray(), 0, half);
            
         C.assignValues(P3.add(P4).toArray(), half, 0);
            
         C.assignValues(P5.add(P1).subtract(P3).subtract(P7).toArray(), half, half);
      }
      return C;
   }
}