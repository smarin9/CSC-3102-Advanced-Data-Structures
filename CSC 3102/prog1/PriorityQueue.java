import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Programming Assignment 1 - Heap-Based Priority Queue
 * CSC 3102 - Robert Kooima
 * 31 March 2016
 * @author seanmarino
 */
public final class PriorityQueue {

    private final char[] heap = new char[255];
    private int size = 0;
    
    /**
     * Eliminates all elements and sets the array size to zero.
     */
    public void clear()
    {
        size = 0;
    }
    /**
     * Appends an element a to the end of the array(regardless of the heap order property.)
     * @param a element
     */
    public void append(char a)
    {
        heap[size] = a;
        size++;
    }
    /**
     * Confirms that the array satisfies the heap order property
     * and returns true or false accordingly.
     * @return true or false
     */
    public boolean check()
    {
        if (size < 0)
        {
            System.out.println("Size must be greater than 0");
            return false;
        }
        for(int i = 0; i > (size - 2) / 2; i++)
        {
            if(heap[i] > heap[2*i+1])
                return false;
            if(heap[i] > heap[2*i+2])
                return false;
        }
        return true;
    }
    /**
     * Returns the number of elements in the heap
     * @return 
     */
    public int size()
    {
        return size;
    }
    /**
     * Adds an element a to the heap, re-heapifying as necessary.
     * @param c 
     */
    public void insert(char c)
    {
        
        int index = size;
        append(c);
        while (!check())
        {
            int parent = (index+2) % 2;
            char temp = heap[parent];
            heap[parent] = heap[index];
            heap[index] = temp;
        }
    }
    /**
     * Deletes the front element from the heap, re-heapifying as necessary.
     * @return 
     */
    public char remove()
    {
        char removed = heap[0];
        heap[0] = heap[size-1];
        int parent = 0;
        while (!check())
        {
            char temp = heap[parent];
            if (heap[(parent * 2) + 1] < heap[(parent * 2) + 2]) 
            {
                heap[parent] = heap[(parent * 2) + 1];
                heap[(parent * 2) + 1] = temp;
            }
            else
            {
                heap[parent] = heap[(parent * 2) + 2];
                heap[(parent * 2) + 2] = temp;
            }
        }
        return removed;
        
    }
    /**
     * Reads a sequence input one-by-one. The character is inserted into the queue if it is a letter.
     * If the character is an exclamation point, removes the minimum(alphabetically first) character from
     * the queue and prints it to the output. 
     */
    public PriorityQueue() { 
        try {
            Scanner scan = new Scanner(new File("input.txt"));
            size = 1;
            String input = scan.next();
            char[] array = input.toCharArray();
            System.out.println(Arrays.toString(array));
            System.out.println();
            heap[size] = array[0];
            size++;
            for (int i = 0; i < array.length; i++){ 
                if (array[i] != '!')
                    insert(array[i]);
                else
                { 
                    System.out.print(heap[0]);
                    remove();
                }
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.print("File not found!");
        }
    }
    /**
     * Unit Tests
     * @param Q 
     */
    public static void test1(PriorityQueue Q) {
        Q.clear();
        Q.append('a');
        Q.append('b');
        assert Q.size() == 2;
        assert Q.check() == true;
    }

    public static void test2(PriorityQueue Q) {
        Q.clear();
        Q.append('b');
        Q.append('a');
        assert Q.size() == 2;
        assert Q.check() == false;

    }

    public static void test3(PriorityQueue Q) {
        Q.clear();
        Q.insert('a');
        Q.insert('b');
        assert Q.size() == 2;
        assert Q.check() == true;
        assert Q.remove() == 'a';
        assert Q.size() == 1;
    }

    public static void test4(PriorityQueue Q) {
        Q.clear();
        Q.insert('b');
        Q.insert('a');
        assert Q.size() == 2;
        assert Q.check() == true;
        assert Q.remove() == 'a';
        assert Q.size() == 1;

    }
    /**
     * Main Method
     * @param args 
     */
    public static void main(String[] args) 
    {
        PriorityQueue Q = new PriorityQueue();
        test1(Q);
        test2(Q);
        test3(Q);
        test4(Q);
    }
    
}
