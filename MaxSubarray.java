class MaxSubarray {
   
   private final static int NEGATIVE_INFINITY = -10_000;
   private final static int POSITIVE_INFINITY = 10_000;
   private final static int LOW = 0;
   private final static int HIGH = 1;
   private final static int SUM = 2;

   public static void main(String[] args) {
      Integer[] nums = GeneralUtils.generateArray(20, 100, 100);
      GeneralUtils.printArray(nums);
      Integer[] maxSubarray = findMaxSubarray(nums, 0, nums.length - 1);
      GeneralUtils.printArray(maxSubarray);
   }
   
   private static Integer[] findMaxCrossingSubarray(Integer[] nums, int low, int mid, int high) {
      int leftSum = NEGATIVE_INFINITY;
      int sum = 0;
      int maxLeft = mid;
      int maxRight = mid + 1;
      for(int i = mid; i >= low; i--) {
         sum += nums[i];
         if(sum > leftSum) {
            leftSum = sum;
            maxLeft = i;
         }
      }
      int rightSum = NEGATIVE_INFINITY;
      sum = 0;
      for(int j = mid + 1; j <= high; j++) {
         sum += nums[j];
         if(sum > rightSum) {
            rightSum = sum;
            maxRight = j;
         }
      }
      return new Integer[] {
         maxLeft, maxRight, leftSum + rightSum
      };
   }
   private static Integer[] findMaxSubarray(Integer[] nums, int low, int high) {
      if(high == low) return new Integer[] {low, high, nums[low]};
      else {
         int mid = (int) Math.floor((low + high)/2);
         Integer[] leftTuple = findMaxSubarray(nums, low, mid);
         Integer[] rightTuple = findMaxSubarray(nums, mid + 1, high);
         Integer[] crossTuple = findMaxCrossingSubarray(nums, low, mid, high);
         if(leftTuple[SUM] >= rightTuple[SUM] && leftTuple[SUM] >= crossTuple[SUM]) {
            return leftTuple;
         } else if(rightTuple[SUM] >= leftTuple[SUM] && rightTuple[SUM] >= crossTuple[SUM]) {
            return rightTuple;
         } else return crossTuple;
      }
   }
}