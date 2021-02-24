class InsertionSort {

	public static void main(String[] args) {
		Integer[] insertArray = {85, 12, 345, 10, -2, 9, 100, 54, 8};
		Integer i, j, key;
		for(j = 1; j < insertArray.length; j++) {
			key = insertArray[j];
			i = j - 1;
			while(i >= 0 && insertArray[i] < key) {
				insertArray[i + 1] = insertArray[i];
            i = i - 1;
			}
			insertArray[i + 1] = key;
         System.out.println("Key: " + key + ", i: " + i);
         GeneralUtils.<Integer>printArray(insertArray);
		}
		GeneralUtils.<Integer>printArray(insertArray);
	}
}