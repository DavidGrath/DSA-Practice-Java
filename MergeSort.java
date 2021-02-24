class MergeSort {
   public static void main(String[] args) {
      Integer[] nums = GeneralUtils.generateArray(29);
      System.out.println("The unsorted sequence: ");
      GeneralUtils.<Integer>printArray(nums);
      sort(nums, 0, nums.length - 1);
      System.out.println("The sorted sequence: ");
      GeneralUtils.<Integer>printArray(nums);
   }
   
   private static void sort(Integer[] nums, int p, int r) {
      if(p < r) {
         int q = (int) Math.floor((float)(p + r)/2);
         sort(nums, p, q);
         sort(nums, q + 1, r);
         merge(nums, p, q, r);
      }
   }
   
   private static void merge(Integer[] nums, int p, int q, int r) {
      int n1 = q - p + 1;
      int n2 = r - q;
      Integer[] left = new Integer[n1 + 1], right = new Integer[n2 + 1];
      int i = 0, j = 0;
      for(i = 0; i < n1; i++) {
         left[i] = nums[p + i];
      }
      left[n1] = GeneralUtils.POSITIVE_INFINITY;
      for(j = 0; j < n2; j++) {
         right[j] = nums[q + j + 1];
      }
      right[n2] = GeneralUtils.POSITIVE_INFINITY;
      i = 0;
      j = 0;
      for(int k = p; k <= r; k++) {
         if(left[i] <= right[j]) {
            nums[k] = left[i];
            i++;
         } else {
            nums[k] = right[j];
            j++;
         }
      }
      System.out.println("p: " + p);
      System.out.println("q: " + q);
      System.out.println("r: " + r);
      System.out.println("Left: ");
      GeneralUtils.<Integer>printArray(left);
      System.out.println("Right: ");
      GeneralUtils.<Integer>printArray(right);
      System.out.println("Merge: ");
      GeneralUtils.<Integer>printArray(nums);
   }
}