class HashTable {
   private static final int DEFAULT_HASH_SIZE = 20;
   private static final int TEMP_DEFAULT_QUEUE_SIZE = 5;
   
   private Queue[] data;
   private final int HASH_SIZE;
   public HashTable() {
      this(DEFAULT_HASH_SIZE);
   }
   public HashTable(int hashSize) {
      HASH_SIZE = hashSize;
      data = new Queue[hashSize];
      for(int i = 0; i < hashSize; i++) {
         data[i] = new Queue(TEMP_DEFAULT_QUEUE_SIZE);
      }
   }
   public void insert(int value) {
      int hashValue = hash(value);
      data[hashValue].enqueue(value);
   }
   public int search(int value) {
      int hashValue = hash(value);
      data[hashValue]
   }
   private int hash(int object) {
      return object %  HASH_SIZE;
   }
   public void print() {
      for(int q = 0; q < HASH_SIZE; q++) {
         System.out.print(q + ": ");
         data[q].print();
      }
   }
   
   public static void main(String[] args) {
      HashTable h = new HashTable();
      h.insert(2);
      h.insert(9);
      h.insert(22);
      h.print();
   }
}