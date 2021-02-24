class CountingSort implements Sorter<Integer> {
   private static final int MAX = 10;
   public static void main(String[] args) {
      Integer[] nums = GeneralUtils.generateArray(15, 0, MAX);
      nums[0] = 0;
      System.out.println("Before: ");
      GeneralUtils.printArray(nums);
      Integer[] sorted =  countingSort(nums);
      System.out.println("After: ");
      GeneralUtils.printArray(sorted);
   }
   private static Integer[] countingSort(Integer[] array) {
      Integer[] count = new Integer[MAX];
      Integer[] sorted = new Integer[array.length];
      for(int i = 0; i < MAX; i++) {
         count[i] = 0; 
      }
      for(int j = 0; j < array.length; j++) {
         count[array[j]] += 1;
      }
      GeneralUtils.printArray(count);
      for(int k = 1; k < MAX; k++) {
         count[k] += count[k - 1];
      }
      GeneralUtils.printArray(count);
      for(int m = array.length - 1; m >= 0; m--) {
         sorted[count[array[m]] - 1] = array[m];
         count[array[m]]--;
      }
      GeneralUtils.printArray(count);
      return sorted;
   }
   
   @Override
   public void sortNumbersInPlace(Integer[] unsorted) {
      countingSort(unsorted);
   }
   @Override
   public Integer[] sortNumbers(Integer[] unsorted) {
      return countingSort(unsorted);
   }
}