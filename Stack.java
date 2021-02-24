class Stack {
   private Integer[] data;
   private int top;
   private final int MAX_SIZE;
   public Stack(int max) {
      MAX_SIZE = max;
      data = new Integer[MAX_SIZE];
      top = -1;
   }
   public void push(Integer value) {
      top += 1;
      data[top] = value;
   }
   public Integer pop() {
      Integer value = data[top];
      data[top] = null;
      top--;
      return value;
   }
   public boolean empty() {
      return top == -1;
   }
}