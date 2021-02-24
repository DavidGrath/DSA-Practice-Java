class LeafPrint {
   public static void main(String[] args) {
      RedBlackTree rb = new RedBlackTree(26);
      int[] nums = new int[]{17, 41, 14, 21, 30, 47, 10, 16, 19, 23, 28, 38, 7, 12, 15, 20, 35, 39, 3};
      for(int n : nums) rb.insert(n);
      rb.printLeaves();
   }
}