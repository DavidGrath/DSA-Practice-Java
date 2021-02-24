class Matrix {
   private Integer[][] value;
   private int rows;
   private int columns;
   public Matrix(Integer[][] value) {
      this.value = value;
      this.rows = value[0].length;
      this.columns = value.length;
   }
   public Matrix(int size) {
      this.value = allZeroes(size, size);
      this.rows = size;
      this.columns = size;
   }
   public Matrix(int rows, int columns) {
      this.value = allZeroes(rows, columns);
      this.rows = rows;
      this.columns = columns;
   }
   
   public int columns() {
      return columns;
   }
   public int rows() {
      return rows;
   }
   private Integer[][] allZeroes(int rows, int columns) {
      Integer[][] zeroes = new Integer[rows][columns];
      for(int i = 0; i < rows; i++) {
         for(int j = 0; j < columns; j++) {
            zeroes[i][j] = 0;
         }
      }
      return zeroes;
   }
   public void assignValues(Integer[][] values, int row, int column) {
      for(int j = 0; j < values.length; j++) {
         for(int i = 0; i < values[j].length; i++) {
            value[i + row][j + column] = values[i][j];
         }
      }
   }
   public void assignValue(Integer number, int row, int column) {
      value[row][column] = number;
   }
   public Matrix subMatrix(int row, int column, int rowWidth, int columnHeight) {
      Integer[][] subValue = new Integer[rowWidth][columnHeight];
      for(int i = 0; i < rowWidth; i++) {
         for(int j = 0; j < columnHeight; j++) {
            subValue[i][j] = value[i + row][j + column];
         }
      }
      return new Matrix(subValue);
   }
   public Integer[][] toArray() {
      return value;
   }
   public Matrix add(Matrix b) {
      Matrix c = new Matrix(rows, columns);
      for(int i = 0; i < rows; i++) {
         for(int j = 0; j < columns; j++) {
            c.value[i][j] = this.element(i, j) + b.element(i, j);
         }
      }
      return c;
   }
   public Matrix subtract(Matrix b) {
      Matrix c = new Matrix(rows, columns);
      for(int i = 0; i < rows; i++) {
         for(int j = 0; j < columns; j++) {
            c.value[i][j] = this.element(i, j) - b.element(i, j);
         }
      }
      return c;
   }
   public Matrix multiply(Matrix b) {
      //TODO Add Exception for incompatible matrices
      int rows = this.rows();
      int columns = b.columns();
      Matrix c = new Matrix(rows, columns);
      for(int i = 0; i < rows; i++) {
         for(int j = 0; j < columns; j++) {
            int sum = 0;
            for(int k = 0; k < rows; k++) {
               sum += this.element(i,k) * b.element(k,j);
            }
            c.assignValue(sum, i, j);
         }
      }
      return c;
   }
   public Integer element(int row, int column) {
      return value[row][column];
   }
   public void print() {
      for(Integer[] row : value) {
         GeneralUtils.printArray(row, true);
      }
   }
   public static void main(String[] args) {
      int size = 8;
      Integer[][] matrix = new Integer[size][size];
      for(int i = 0; i < size; i++) {
         matrix[i] = GeneralUtils.generateArray(size, 0, 20);
      }
      Matrix mat = new Matrix(matrix);
      System.out.println("Values: ");
      mat.print();
      Integer[][] subArray = {
         {0, 1, 2, 3},
         {4, 5, 6, 7},
         {8, 9, 10, 11},
         {12, 13, 14, 15}
      };
      mat.assignValues(subArray, 2, 2);
      System.out.println("New values: ");
      mat.print();
      System.out.println("Submatrix: ");
      Matrix subMatrix = mat.subMatrix(2, 2, 4, 4);
      subMatrix.print();
   }
}