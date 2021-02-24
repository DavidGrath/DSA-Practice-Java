//Mon 26 Oct 2020 08:32 AM WAT - I declare this imperfect

class LinkedList<T extends Number> {

   private Item<T>[] data;
   private int free;
   private int head;
   private static final int NIL = -1;
   private final int MAX_SIZE;
   public LinkedList(int max) {
      MAX_SIZE = max;
      data = new Item[MAX_SIZE];
      int i;
      for(i = 0; i < MAX_SIZE - 1; i++) {
         data[i] = new Item(null, -1, i + 1);
      }
      data[i] = new Item(-1, -1, -1);
      free = 0;
      head = NIL;
   }
   public void insert(T value) {
      Item item = new Item(value, -1, -1);
      int oldHead = head;
      head = allocate();
      item.next = oldHead;
      item.previous = NIL;
      if(oldHead != NIL)data[oldHead].previous = head;
      data[head] = item;
   }
   public T extract(int index) {
      Item item = data[index];
      T itemData = (T) item.data;
      if(item.previous != NIL) data[item.next].previous = item.previous;
      else head = item.next;
      data[item.previous].next = item.next;
      freeObject(index);
      return itemData;
   }
   private int allocate() {
      int x = free;
      free = data[free].next;
      return x;
   }
   private void freeObject(int x) {
      data[x].next = free;
      data[x].data = null;
      free = x;
   }
   public Item head() {
      return data[head];
   }
   public void printFull() {
      for(int i = 0; i < MAX_SIZE; i++) {
         System.out.print(data[i].next + "\t");
      }
      System.out.println();
      for(int i = 0; i < MAX_SIZE; i++) {
         if(data[i].data == null) System.out.print("null\t");
         else System.out.print(data[i].data + "\t");
      }
      System.out.println();
   }
   public void print() {
      Item item = data[head];
      if(data[head].data == null) {
         System.out.println("null");
         return;
      }
      do {
         System.out.print(item.data.toString() + " ");
         item = data[item.next];   
      } while(item.next != NIL);
      System.out.print(item.data.toString() + " ");
      System.out.println();
   }
   class Item<T> {
      public int next;
      public int previous;
      public T data;
      public Item(T data, int next, int previous) {
         this.data = data;
         this.next = next;
         this.next = previous;
      }
   }
   public static void main(String[] args) {
      LinkedList<Integer> list = new LinkedList(10);
      list.insert(2);
      list.insert(9);
      list.insert(19);
      list.insert(11);
      list.insert(4);
      list.insert(5);
      list.insert(15);
      list.insert(7);
      list.insert(6);
      list.insert(13);
      System.out.println("Extracted: " + list.extract(3));
      list.print();
      list.printFull();
   }
}