class RadixSort implements Sorter<Integer> {

   private final static int SIZE = 20;
   private final static int DIGITS = 3;
   private final static int MIN = -100;
   //TODO Gotta fix the generateArray function to be less confusing
   private final static int MAX = 1000 + MIN;
   public static void main(String[] args) {
      Integer[] numbers = GeneralUtils.generateArray(SIZE, MIN, MAX);
      System.out.println("Before: ");
      GeneralUtils.printArray(numbers);
      numbers = radixSort(numbers);
      System.out.println("After: ");
      GeneralUtils.printArray(numbers);
   }
   
   private static Integer[] radixSort(Integer[] unsorted) {
      Integer[][] digitList = new Integer[unsorted.length][DIGITS];
      for(int i = 0; i < SIZE; i++) {
         digitList[i] = split(unsorted[i], DIGITS);
      }
      for(int j = DIGITS - 1; j >= 0 ; j--) {
         digitList = countingSortInternal(digitList, j);
         System.out.println("Temp:");
         for(Integer[] temp : digitList) {   
            GeneralUtils.printArray(temp);
         }
      }
      Integer[] rejoined = new Integer[SIZE];
      for(int k = 0; k < SIZE; k++) {
         rejoined[k] = join(digitList[k], DIGITS);
      }
      return rejoined;
   }
   private static Integer[][] countingSortInternal(Integer[][] digitList, int index) {
      Integer[] count = new Integer[MAX];
      Integer[][] sorted = new Integer[digitList.length][DIGITS];
      for(int i = 0; i < MAX; i++) {
         count[i] = 0; 
      }
      for(int j = 0; j < digitList.length; j++) {
         count[digitList[j][index]] += 1;
      }
      for(int k = 1; k < MAX; k++) {
         count[k] += count[k - 1];
      }
      for(int m = digitList.length - 1; m >= 0; m--) {
         sorted[count[digitList[m][index]] - 1] = digitList[m];
         count[digitList[m][index]]--;
      }
      return sorted;
   }
   private static Integer[] split(Integer number, int digits) {
      Integer[] splitList = new Integer[digits];
      Integer temp;
      Integer divisor = 1;
      Integer modulo = 10;
      for(int i = digits - 1; i >= 0; i--) {
         temp = number;
         temp %= modulo;
         temp = (int)Math.floor(temp/divisor);
         splitList[i] = temp;
         divisor *= 10;
         modulo *= 10;
      }
      return splitList;
   }
   private static Integer join(Integer[] number, int digits) {
      Integer value = 0;
      int multiplier = 1;
      for(int i = digits - 1; i >= 0; i--) {
         value += (number[i] * multiplier);
         multiplier *= 10;
      }
      return value;
   }
   @Override
   public void sortNumbersInPlace(Integer[] unsorted) {
      radixSort(unsorted);
   }
   @Override
   public Integer[] sortNumbers(Integer[] unsorted) {
      return radixSort(unsorted);
   }
}