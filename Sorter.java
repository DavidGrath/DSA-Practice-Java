interface Sorter<T extends Number> {
   public Number[] sortNumbers(T[] unsorted);
   public void sortNumbersInPlace(T[] unsorted);
}