class SelectionSort {
   public static void main(String[] args) {
      Integer[] nums = GeneralUtils.generateArray(20);
      System.out.println("The unsorted sequence: ");
      GeneralUtils.<Integer>printArray(nums);
      sort(nums);
      System.out.println("The sorted sequence: ");
      GeneralUtils.<Integer>printArray(nums);
   }
   private static void sort(Integer[] nums) {
      final int size = nums.length;
      for(int i = 0; i < size - 1; i++) {
         int smallest = nums[i], pos = i;
         for(int j = i; j < size; j++) {
            if(nums[j] < smallest) {
               smallest = nums[j];
               pos = j;
            }
         }
         //Swap the smallest element with the next unsorted element
         int temp = nums[i];
         nums[i] = nums[pos];
         nums[pos] = temp;
         GeneralUtils.<Integer>printArray(nums);
      }
   }
}