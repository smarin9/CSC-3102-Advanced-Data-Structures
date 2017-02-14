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
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Prog1Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        try {
            Scanner in = new Scanner(new File("input.txt"));
            size = 1;
            String fileContents = in.next();
            char[] charArray = fileContents.toCharArray();
            System.out.println(Arrays.toString(charArray));
            heap[size] = charArray[0];
            size++;
            for (int i = 1; i < charArray.length; i++){ 
                if (charArray[i] != '!')
                    insert(size, charArray[i]);
                else{ //prints the character before removing it
                    System.out.print(heap[1]);
                    remove();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.print("File not found!");
        }
 
    }
        
        
         
        File file = new File("input.txt");
        try {
            Scanner in = new Scanner(file);
            PriorityQueue Q = new PriorityQueue();
            Q.main();
            String input = new String();
            
                for(int i = 0; i < input.length(); i++) {
                    char y = input.charAt(i);
                    if (y == '!') {
                        char done = Q.remove();
                        System.out.print(done);
                    }
                    if (Character.isLetter(y)) {
                        //System.out.print("Ugh");
                        Q.insert(y);
                    }
                    
                    else { 
                        if (Character.compare(y, '!') == 0) {
                        
                    }
                    else {
                        System.out.print("WHAT THE HELL");
                    }
                }
                
            }
            
            
            
            
        }  
        } catch (FileNotFoundException ex) {
            System.out.print("No Input.txt file found");
            Logger.getLogger(Prog1Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    

