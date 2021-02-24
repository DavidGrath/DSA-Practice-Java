//15 Oct 2020 18:18 PM WAT - I declare this as STUCK
class NonDivisibleSubset {
   public static void main(String[] args) {
      int[] numbers = new int[]{582740017, 954896345, 590538156, 298333230, 859747706, 155195851, 331503493,
       799588305, 164222042, 563356693, 80522822, 432354938, 652248359};
      System.out.println(nonDivisibleSubset(11, numbers));
   }
   private static int nonDivisibleSubset(int k, int[] numbers) {
      if(numbers.length <= 1) return 0;
      int highestCount = 0;
      for(int i = 0; i < numbers.length; i++) {
         int count = nonDivisibleSubrange(numbers, i);
         if(highestCount < count) highestCount = count;
      }
      return highestCount;
   }
   private static int nonDivisibleSubrange(int[] numbers, int start) {
      int count = 0;
      boolean nonDivs = new boolean[numbers.length - start];
      boolean satisfies = false;
      return nonDivisibleSubrange(numbers, start) + satisfies? 1 : 0;
   }
}