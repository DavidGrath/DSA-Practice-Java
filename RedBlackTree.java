class RedBlackTree {
   private enum Color {RED, BLACK};
   private final RedBlackNode NIL = new RedBlackNode(Color.BLACK, true, -1);
   private RedBlackNode root;

   public RedBlackTree(RedBlackNode root) {
      this.root = root;
   }
   public RedBlackTree(int key) {
      root = new RedBlackNode(Color.BLACK, key);
   }
   public RedBlackTree() {
      root = NIL;
   }
   public RedBlackNode insert(int key) {
      return insert(new RedBlackNode(key));
   }
   public RedBlackNode insert(RedBlackNode z) {
      RedBlackNode y = NIL;
      RedBlackNode x = root;
      while(!x.isNil()) {
         y = x;
         if(z.key < x.key) {
            x = x.left;
         } else {
            x = x.right;
         }
      }
      z.parent = y;
      if(y.isNil()) {
         root = z;
      } else if(z.key < y.key) {
         y.left = z;
      } else {
         y.right = z;
      }
      rbInsertFixup(z);
      return z;
   }
   
   private void rbInsertFixup(RedBlackNode z) {
      while(z.parent.color == Color.RED) {
         if(z.parent.equals(z.parent.parent.left)) {
            RedBlackNode y = z.parent.parent.right;
            if(y.color == Color.RED) {
               z.parent.color = Color.BLACK;
               y.color = Color.BLACK;
               z.parent.parent.color = Color.RED;
               z = z.parent.parent;
            } else {
               if(z.equals(z.parent.right)){
                  z = z.parent;
                  leftRotate(z);
               }
               z.parent.color = Color.BLACK;
               z.parent.parent.color = Color.RED;
               rightRotate(z.parent.parent);
            }
         } else {
            RedBlackNode y = z.parent.parent.left;
            if(y.color == Color.RED) {
               z.parent.color = Color.BLACK;
               y.color = Color.BLACK;
               z.parent.parent.color = Color.RED;
               z = z.parent.parent;
            } else {
               if(z.equals(z.parent.left)) {
                  z = z.parent;
                  rightRotate(z);
               }
               z.parent.color = Color.BLACK;
               z.parent.parent.color = Color.RED;
               leftRotate(z.parent.parent);
            }
         }
      }
      root.color = Color.BLACK;
   }
   public void delete(RedBlackNode z) {
      RedBlackNode x;
      RedBlackNode y = z;
      Color yOriginalColor = y.color;
      if(z.left.isNil()) {
         x = z.right;
         rbTransplant(z, z.right);
      } else if(z.right.isNil()) {
         x = z.left;
         rbTransplant(z, z.left);
      } else {
         y = minimum(z.right);
         yOriginalColor = y.color;
         x = y.right;
         if(y.parent.equals(z)) {
            x.parent = y;
         } else {
            rbTransplant(y, y.right);
            y.right = z.right;
            y.right.parent = y;
         }
         rbTransplant(z, y);
         y.left = z.left;
         y.left.parent = y;
         y.color = z.color;
      }
      if(yOriginalColor == Color.BLACK) {
         rbDeleteFixup(x);
      }
   }
   private void rbDeleteFixup(RedBlackNode x) {
      while(!x.equals(root) && x.color == Color.BLACK) {
         if(x.equals(x.parent.left)) {
            RedBlackNode w = x.parent.right;
            if(w.color == Color.RED) {
               w.color = Color.BLACK;
               x.parent.color = Color.RED;
               leftRotate(x.parent);
               w = x.parent.right;
            }
            if(w.left.color == Color.BLACK && w.right.color == Color.BLACK) {
               x.color = Color.RED;
               x = x.parent;
            } else {
               if(w.right.color == Color.BLACK) {
                  w.left.color = Color.BLACK;
                  w.color = Color.RED;
                  rightRotate(w);
                  w = x.parent.right;
               }
               w.color = x.parent.color;
               x.parent.color = Color.BLACK;
               w.right.color = Color.BLACK;
               leftRotate(x.parent);
               x = root;
            }
         } else {
            RedBlackNode w = x.parent.left;
            if(w.color == Color.RED) {
               w.color = Color.BLACK;
               x.parent.color = Color.RED;
               rightRotate(x.parent);
               w = x.parent.left;
            }
            if(w.right.color == Color.BLACK && w.left.color == Color.BLACK) {
               x.color = Color.RED;
               x = x.parent;
            } else {
               if(w.left.color == Color.BLACK) {
                  w.right.color = Color.BLACK;
                  w.color = Color.RED;
                  leftRotate(w);
                  w = x.parent.left;
               }
               w.color = x.parent.color;
               x.parent.color = Color.BLACK;
               w.left.color = Color.BLACK;
               rightRotate(x.parent);
               x = root;
            }
         }
      }
      x.color = Color.BLACK;
   }
   public RedBlackNode minimum() {
      return minimum(root);
   }
   private RedBlackNode minimum(RedBlackNode x) {
      RedBlackNode xTemp = x;
      while(!xTemp.left.isNil()) {
         xTemp = xTemp.left;
      }
      return xTemp;
   }
   public RedBlackNode maximum() {
      return maximum(root);
   }
   private RedBlackNode maximum(RedBlackNode x) {
      RedBlackNode xTemp = x;
      while(!xTemp.right.isNil()) {
         xTemp = xTemp.right;
      }
      return xTemp;
   }
   private void leftRotate(RedBlackNode x) {
      RedBlackNode y = x.right;
      x.right = y.left;
      if(!y.left.isNil()) {
         y.left.parent = x;
      }
      y.parent = x.parent;
      if(x.parent.isNil()) {
         root = y;
      }
      else if(x.equals(x.parent.left)) {
         x.parent.left = y;
      } else {
         x.parent.right = y;
      }
      y.left = x;
      x.parent = y;
   }
   private void rightRotate(RedBlackNode y) {
      RedBlackNode x = y.left;
      y.left = x.right;
      if(!x.right.isNil()) {
         x.right.parent = y;
      }
      x.parent = y.parent;
      if(y.parent.isNil()) {
         root = x;
      } else if (y.equals(y.parent.right)) {
         y.parent.right = x;
      } else {
         y.parent.left = x;
      }
      x.right = y;
      y.parent = x;
   }
   private void rbTransplant(RedBlackNode x, RedBlackNode y) {
      if(x.parent.isNil()) {
         root = y;
      } else if(x.equals(x.parent.left)) {
         x.parent.left = y;
      } else {
         x.parent.right = y;
      }
      y.parent = x.parent;
   }
   public void inorderRbWalk() {
      System.out.println("Root: " + root);
      inorderRbWalk(root);
   }
   public void inorderRbWalk(RedBlackNode x) {
      if(!x.isNil()) {
         inorderRbWalk(x.left);
         System.out.println(x);
         inorderRbWalk(x.right);
      }
   }
   // File last modified 09 nov 2020 06:15:45 wat
   // 25-11-2020 Adding function to print from bottom to top with recursion
   public void printLeaves() {
      printLeaves(root);
   }
   private void printLeaves(RedBlackNode x) {
      if(x.left.isNil() || x.right.isNil()) {
         System.out.println(x);
      } else {
         printLeaves(x.left);
         printLeaves(x.right);
      }
      //System.out.println(x);
   }
   
   public static void main(String[] args) {
      RedBlackTree rb = new RedBlackTree(11);
      rb.insert(1);
      rb.insert(2);
      rb.insert(5);
      RedBlackNode seven = rb.insert(7);
      rb.insert(8);
      rb.insert(14);
      rb.insert(15);
      RedBlackNode four = rb.insert(4);
      System.out.println("Four: " + four);
      rb.inorderRbWalk();
      rb.delete(seven);
      System.out.println("Seven deleted: ");
      rb.inorderRbWalk();
   }
   class RedBlackNode {
      private Color color;
      public final int key;
      private final boolean isNil;
      private RedBlackNode left;
      private RedBlackNode right;
      private RedBlackNode parent;
      public RedBlackNode(Color color, boolean isNil, int key) {
         this.isNil = isNil;
         this.key = key;
         this.color = color;
         this.left = NIL;
         this.right = NIL;
         this.parent = NIL;
      }
      public RedBlackNode(int key) {
         this(Color.RED, false, key);
      }
      public RedBlackNode(Color color, int key) {
         this(color, false, key);
      }

      public Color getColor() {
         return this.color;
      }
      public boolean isNil() {
         return this.isNil;
      } 
      @Override
      public boolean equals(Object b) {
         RedBlackNode bNode = (RedBlackNode) b;
         return /*(this.color == bNode.color) && */(this.key == bNode.key);
      }
      @Override
      public String toString() {
         if(isNil) {
            return "NIL";
         } else {
            return String.valueOf(this.key) + ": " + (color == Color.RED? "RED" : "BLACK");
         }
      }
   }
}