class OrderStatisticTree {
   private OrderStatisticNode OS_NIL = new OrderStatisticNode(-1);
   private OrderStatisticNode root = OS_NIL;
   private enum Color {OS_RED, OS_BLACK};
   
   public OrderStatisticNode osSelect(int i) {
      return osSelect(i, root);
   }
   private OrderStatisticNode osSelect(int i, OrderStatisticNode x) {
      int rank = x.size + 1;
      if(i == rank) {
         return x;
      } else if(i < rank) {
         return osSelect(i);
      } else {
         return osSelect(i - rank);
      }
   }
   class OrderStatisticNode {
      private int size;
      public final int key;
      private final boolean isNil;
      
      public OrderStatisticNode parent;
      public OrderStatisticNode left;
      public OrderStatisticNode right;
      
      public OrderStatisticNode(int key) {
         this.size = 0;
         this.parent = OS_NIL;
         this.left = OS_NIL;
         this.right = OS_NIL;
         this.key = key;
         isNil = false;
      }
   }
}