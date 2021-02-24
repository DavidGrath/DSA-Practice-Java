class HeapSort {
   private static int heapSize;
   public static void main(String[] args) {
      //Integer[] nums = GeneralUtils.generateArray(20);
      Integer[] nums = new Integer[]{34, 5, -2, 67, 23, 111, 0 , 1, 1, 6, 4};
      System.out.println("Before: ");
      GeneralUtils.printArray(nums);
      heapSort(nums);
      System.out.println("After: ");
      GeneralUtils.printArray(nums);
   }
   
   private static void maxHeapify(Integer[] nums, int i) {
      int l = left(i);
      int r = right(i);
      int largest;
      if(l <= heapSize && nums[l] > nums[i]) {
         largest = l;
      } else {
         largest = i;
      }
      if(r <= heapSize && nums[r] > nums[largest]) {
         largest = r;
      }
      if(largest != i) {
         Integer temp = nums[i];
         nums[i] = nums[largest];
         nums[largest] = temp;
         maxHeapify(nums, largest);
      }
   }
   private static void buildMaxHeap(Integer[] nums) {
      heapSize = nums.length - 1;
      int mid = (int) Math.floor((nums.length - 2)/2);
      for(int i = mid; i >= 0; i--) {
         maxHeapify(nums, i);
      }
   }
   private static void heapSort(Integer[] nums) {
      buildMaxHeap(nums);
      for(int i = nums.length - 1; i >= 1; i--) {
         Integer temp = nums[i];
         nums[i] = nums[0];
         nums[0] = temp;
         heapSize--;
         maxHeapify(nums, 0);
      }
   }
   private static int left(int i) {
      return (2 * i) + 1;
   }
   private static int right(int i) {
      return (2 * i) + 2;
   }
   private static int parent(int i) {
      return 1;
      //return (int) Math.floor(i/2);
   }
   private static void insert(Integer[] heap, Integer x) {
      
   }
   private static Integer maximum(Integer[] heap) {
      return heap[0];
   }
   private static int extractMax(Integer[] heap) {
      
   }
}