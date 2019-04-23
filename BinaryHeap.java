//Sepid Ebrahimi, CS 245, Section 1

public class BinaryHeap {
    private int[] array;
    private int size;

    public BinaryHeap() {
        array = new int[10];//starting with array of size 10 as mentioned in the assignment
        size = 0;
    }


    int parent(int data) { //find the parent
        return (data - 1) / 2;
    }

    int left_child(int data) { //get index of left child
        return (data * 2) + 1;
    }

    int right_child(int data) { //get index of right child
        return (data * 2) + 2;
    }

    private void swap(int pos1, int pos2) { //swap position 1 & 2
        int temp;
        temp = array[pos1];
        array[pos1] = array[pos2];
        array[pos2] = temp;
    }

    private void grow_array() {
        int[] new_arr = new int[array.length * 2];
        System.arraycopy(array, 0, new_arr, 0, array.length);
        array = new_arr;
    }

    public void add (int item) { //adds an item
        if(array.length <= size) { //check if the array is full
            grow_array();
        }
        array[size++] = item;
        int current = size - 1;
        while(array[current] < array[parent(current)]) {
            swap(current, parent(current)); //compare higher priority children and swap if necessary
            current = parent(current);
        }
    }

    public int remove() { //removes the root
        int removed = array[0];
        swap(0, --size); //place last child in the root position
        if (size != 0) {
            shiftdown(0);
        }
        return removed;
    }
    private void shiftdown(int position) {
        if (left_child(position) >= size)
            return;
        int child = left_child(position);
        //if the child has a sibling and the child's value is less than its sibling's
        if (right_child(position) < size && array[right_child(position)] < array[child])
            child = right_child(position); //then point to the right child instead
        if (array[child] < array[position]) {
            swap(child, position);
            shiftdown(child);
        }
    }


}