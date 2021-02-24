abstract class BST {
   // BSTNode minimum(BSTNode x);
//    BSTNode maximum(BSTNode x);
//    BSTNode search(int key);
//    BSTNode insert(int key);
//    BSTNode delete(BSTNode z);
//    BSTNode transplant(BSTNode x, BSTNode y);
   abstract BSTNode NIL();
   abstract class BSTNode {
      BSTNode NIL = NIL();
      BSTNode parent = NIL;
      BSTNode left = NIL;
      BSTNode right = NIL;
      private boolean isNil = false;
      public int key = -1;
      @Override
      public String toString() {
         if(isNil) {
            return "NIL";
         } else {
            return String.valueOf(this.key);
         }
      }
      //Assuming distinct keys
      @Override
      public boolean equals(Object b) {
         return this.key == ((BSTNode)b).key;
      }
   }
}