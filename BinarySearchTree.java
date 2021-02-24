class BinarySearchTree {
   private final Node NIL = new Node(true, -1);
   private Node root = NIL;
   public BinarySearchTree(int rootKey) {
      root = new Node(rootKey);
   }
   public BinarySearchTree() {
   }
   public Node insert(int z) {
      return insert(new Node(z));
   }
   public Node insert(Node z) {
      Node y = NIL;
      Node x = root;
      int level = 0;
      while(!x.isNil()) {
         y = x;
         if(z.key < x.key) {
            x = x.left;
         } else {
            x = x.right;
         }
         level++;
      }
      z.parent = y;
      if(y.isNil())  {
         root = y;
      } else if (z.key < y.key) {
         y.left = z;
      } else {
         y.right = z;
      }
      y.level = level;
      return z;
   }
   public Node extract(Node z) {
      if(z.left.isNil()) {
         transplant(z, z.right);
      } else if(z.right.isNil()) {
         transplant(z, z.left);
      } else {
         Node y = minimum(z.right);
         if(!y.parent.equals(z)) {
            transplant(y, y.right);
            y.right = z.right;
            y.right.parent = y;
         }
         transplant(z, y);
         y.left = z.left;
         y.left.parent = y;
      }
      return z;
   }
   public void transplant(Node x, Node y) {
      if(x.parent.isNil()) {
         root = y;
      } else if(x.parent.left.equals(x)) {
         x.parent.left = y;
      } else {
         x.parent.right = y;
      }
      if(!y.isNil()) {
         y.parent = x.parent;
      }
   }
   public Node search(int key) {
      Node x = root;
      while(!x.isNil()) {
         if(x.key < key) {
            x = x.right;
         } else if (x.key > key) {
            x = x.left;
         } else {
            return x;
         }
      }
      return x;
   }
   public Node minimum() {
      return minimum(root);
   }
   public Node minimum(Node x) {
      while(!x.left.isNil()) {
         x = x.left;
      }
      return x;
   }
   public Node maximum() {
      return maximum(root);
   }
   public Node maximum(Node x) {
      while(!x.right.isNil()) {
         x = x.right;
      }
      return x;
   }
   public Node successor(Node x) {
      if(!x.right.isNil()) {
         return minimum(x.right);
      }
      Node y = x.parent;
      while(y.parent.isNil() && x.equals(y.right)) {
         x = y;
         y = y.parent;
      }
      return y;
   }
   public Node predecessor(Node x) {
      if(!x.left.isNil()) {
         return maximum(x.left);
      }
      Node y = x.parent;
      while(y.parent.isNil() && x.equals(y.left)) {
         x = y;
         y = y.parent;
      }
      return y;
   }
   public void inorderWalk() {
      inorderWalk(root);
      System.out.println();
   }
   public void inorderWalk(Node x) {
      if(!x.isNil()) {
         inorderWalk(x.left);   
         System.out.print(x.key + " ");
         inorderWalk(x.right);
      }
   }
   public void levelPrint() {
      
   }
   
   
   public static void main(String[] args) {
      BinarySearchTree tree = new BinarySearchTree(3);
      Node two = tree.insert(2);
      tree.insert(5);
      Node eight = tree.insert(8);
      tree.insert(10);
      tree.insert(7);
      tree.inorderWalk();
      System.out.println("Minimum: " + tree.minimum());
      System.out.println("Maximum: " + tree.maximum());
      System.out.println("Two Successor: " + tree.successor(two));
      System.out.println("Eight Successor: " + tree.successor(eight));
      System.out.println("Eight Predecessor: " + tree.predecessor(eight));
      System.out.println("Search for 4: " + tree.search(4));
      System.out.println("Search for 10: " + tree.search(10));
      System.out.println("Remove 7: " + tree.extract(tree.search(7)));
      tree.inorderWalk();
   }
   
   class Node {
      public int level;
      Node parent;
      Node left;
      Node right;
      private final boolean isNil;
      public int key = -1;
      public Node(boolean isNil, int key) {
         this.isNil = isNil;
         this.parent = NIL;
         this.left = NIL;
         this.right = NIL;
         this.level = -1;
         this.key = key;
      }
      public Node(int key) {
         this(false, key);
      }
      public boolean isNil() {
         return isNil;
      }
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
         return this.key == ((Node)b).key;
      }
   }
}