class QuickSort {

   private static final int LENGTH = 20;

   public static void main(String[] args) {
      Integer[] nums = GeneralUtils.generateArray(LENGTH, 0, 100);
      System.out.println("Before: ");
      GeneralUtils.printArray(nums);
      quickSort(nums, 0, LENGTH - 1);
      System.out.println("After: ");
      GeneralUtils.printArray(nums);
   }
   private static void quickSort(Integer[] nums, int p, int r) {
      if(p < r) {
         int q = partition(nums, p, r);
         quickSort(nums, p, q - 1);
         quickSort(nums, q + 1, r);
      }
   }
   
   private static int partition(Integer[] nums, int p, int r) {
      int i = p - 1;
      int pivot = nums[r];
      for(int j = p; j < r; j++) {
         if(nums[j] < pivot) {
            i++;
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
         }
      }
      int temp = nums[r];
      nums[r] = nums[i + 1];
      nums[i + 1] = temp;
      return i + 1;
   }
}