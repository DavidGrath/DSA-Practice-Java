class Queue {
   private int head;
   private int tail;
   private Integer[] data;
   private final int MAX_SIZE;
   public Queue(int max) {
      MAX_SIZE = max;
      data = new Integer[MAX_SIZE + 1];
      head = tail = 0;
   }
   public void enqueue(int value) {
      data[tail] = value;
      tail = (tail + 1) % (MAX_SIZE + 1);
   }
   public Integer dequeue() {
      Integer value = data[head];
      data[head] = null;
      head = (head + 1) % (MAX_SIZE + 1);
      return value;
   }
   public boolean empty() {
      return head == tail;
   }
   public boolean full() {
      return (head % (MAX_SIZE + 1)) - (tail % (MAX_SIZE + 1)) == 1;
   }
   public void print() {
      for(int i = head; i < tail;  i = (i + 1) % (MAX_SIZE + 1)) {
         System.out.print(data[i].toString() + " ");
      }
      System.out.println();
   }
   public void printFull() {
      for(int i = 0; i < MAX_SIZE + 1; i++) {
         if(i == head) System.out.print("|");
         if(data[i] == null) System.out.print("null ");
         else System.out.print(data[i] + " ");
      }
      System.out.println();
   }
   
   private static final int SIZE = 10;
   public static void main(String[] args) {
      Queue q = new Queue(SIZE);
      q.enqueue(2);
      q.dequeue();
      q.enqueue(3);
      q.enqueue(6);
      q.enqueue(14);
      q.dequeue();
      q.enqueue(28);
      q.enqueue(34);
      q.enqueue(5);
      q.enqueue(8);
      q.enqueue(19);
      System.out.println("Full: " + q.full());
      System.out.println("Empty: " + q.empty());
      q.print();
      q.printFull();
   }
}