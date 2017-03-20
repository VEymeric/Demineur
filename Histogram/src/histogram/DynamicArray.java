package histogram;

public class DynamicArray {

    private static final int BASE_SIZE = 3; // Default array size
    private int iMax = 0; // Index of the most frequently occuring value
    private int length = 0; // Index+1 of the higher value
    private int[] array; // Array of values

    public int getiMax() {
        return iMax;
    }

    public int getLength() {
        return length;
    }

    public DynamicArray() {
        array = new int[BASE_SIZE];
    }

    public int get(int i) throws IndexOutOfBoundsException {
        if (i >= array.length) {
            throw new IndexOutOfBoundsException();
        }

        return array[i];
    }

    public void add(int i) throws IndexOutOfBoundsException {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }

        while (i >= array.length) {
            int[] newArray = new int[2 * array.length]; // Doubling the size of the array
            System.arraycopy(array, 0, newArray, 0, array.length); // Optimized memcpy
            array = newArray; // Dereferencing the old array
        }

        array[i]++;

        if (array[i] > array[iMax]) {
            iMax = i;
        }

        if (i >= length) {
            length = i + 1;
        }
    }
}
