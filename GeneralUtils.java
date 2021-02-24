import java.util.Random;

class GeneralUtils {

   public static final int POSITIVE_INFINITY = 100_000;
   public static final int NEGATIVE_INFINITY = -100_000;

   private static Random random = new Random();

   public static <T> void printArray(T[] arr) {
      printArray(arr, false);
   }
   public static <T> void printArray(T[] arr, boolean tabsFormatted) {
      String whitespace = tabsFormatted? "\t" : " ";
      for (int i = 0; i < arr.length; i++) {
         System.out.print(arr[i].toString() + whitespace);
      }
               System.out.println();
   }
   //Max is exclusive
   public static Integer[] generateArray(int size, int min, int max) {
      Integer[] nums = new Integer[size];
      for(int i = 0; i < size; i++) {
         nums[i] = random.nextInt(max) - min;
      }
      return nums;
   }
   public static Integer[] generateArray(int size) {
      return generateArray(size, 500, 1001);
   }
   public static Integer[] generateArray() {
      int size = random.nextInt(91) + 10;
      return generateArray(size);
   }
}