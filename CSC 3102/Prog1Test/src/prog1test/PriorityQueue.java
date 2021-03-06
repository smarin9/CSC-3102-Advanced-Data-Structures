/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog1test;

/**
 *
 * @author nickdugal
 */
public class PriorityQueue {

    private static int size;
    private char[] array;

    /**
     * @param args the command line arguments
     */
    public PriorityQueue() {
        size = 0;
        array = new char[255];
    }

    public void clear() {
        array = new char[255];
        size = 0;
    }

    public void append(char a) {
        array[size] = a;
        size++;
    }

//    public boolean check2() {
//     int parent = 0, left = 1, right = 2;
//     while (left < size) {
//         if (right < size)
//         {
//             if (array[left] < array[parent] || array[right] < array[parent]) {
//                 char temp = array[parent];
//                 int comp = Character.compare(array[left], array[right]);
//                 if (comp < 1)
//                 {
//                     array[parent] = array[left];
//                     array[left] = temp;
//                 }
//                 else {
//                     array[parent] = array[right];
//                     array[right] = temp;
//                 }
//                 array[parent] = (array[left] < array[right] ? array[left] : array[right]);
//             }
//         }
//     }
//    }
    public boolean check() {
        if (size < 0) {
            System.out.print("Dude, something bad happened");
            return false;
        } else if (size < 2) {
            return true;
        } else {
            for (int i = size - 1; i > 0; i--) {
                int parent = (i - 1) / 2;
                if (array[parent] > array[i]) {
                    return false;
                }

            }
            return true;
        }
    }

    public int size() {
        return size;
    }

    public void insert(char a) {

        int index = size;
        append(a);
        while (!check()) {
            int parent = (index - 1) / 2;
            char temp = array[parent];
            array[parent] = array[index];
            array[index] = temp;
            index = parent;
        }

    }

    public char remove() {

        char deleted = array[0];
        if (size > 1)
            array[0] = array[size - 1];
        else
            clear();
        size--;
        int parent = 0, left = 1, right = 2;
        while (!check()) {
            char temp = array[parent];
            if (left < size && right < size) {
                int compare = Character.compare(array[left], array[right]);
                if (compare > 0) {
                    array[parent] = array[right];
                    array[right] = temp;
                    parent = right;
                }    
            } else {
                array[parent] = array[left];
                    array[left] = temp;
                    parent = left;   
            }
        }
        
        return deleted;
    }

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

    public static void main() {
        PriorityQueue Q = new PriorityQueue();
        test1(Q);
        test2(Q);
        test3(Q);
        test4(Q);
    }

}
