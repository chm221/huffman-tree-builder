/*
Data Structures 2203
Assignment 3 Huffman Tree
Chelsea McFarland
6/21/15
This class is responsible for handling input from and ouput to the user. 
 */package assign3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Chelsea
 */
public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        //create the unsorted array with chars
        //create second array for printing
        ArrayList<Node> alphabet = new ArrayList();
        
        //create a node for each char in the alphabet
        for(int i=0; i<26; i++){
            alphabet.add(new Node(0, (char)(97+i)));
        }
        
        System.out.println("Enter strings to be encoded. When finished, enter "
                + "#");
        while(true){  
            String temp = sc.next();
            if(temp.equals("#"))
                break;
            for(int i=0; i<temp.length(); i++){
                //if a lowercase letter
                if((int)temp.charAt(i) < 123 && (int)temp.charAt(i) > 96){
                    alphabet.get((int)(temp.charAt(i)) - 97).incWeight();
                }
                
                //if an uppercase letter
                else if((int)temp.charAt(i) < 91 && (int)temp.charAt(i) > 64){
                    alphabet.get((int)(temp.charAt(i)) - 65).incWeight();
                }
                
                else{
                    System.out.println("Invalid input, please enter only "
                            + "chars");
                }                                              
            }
        }
        //remove nodes with zero weight
        for(int i = 0; i<alphabet.size(); i++){
            if(alphabet.get(i).getWeight() == 0){
                alphabet.remove(i);
                i--;
            }
        }
        ArrayList<Node> unsorted = alphabet;
        ArrayList<Node> printUnsorted = new ArrayList(unsorted);
        
        //create a heap from the unsorted ArrayList
        //create a second heap for printing
        HeapArray heap = new HeapArray(unsorted);
        HeapArray printHeap = new HeapArray(printUnsorted);
        
        //build the tree using the min heap
        HuffTree tree = HuffTree.buildTree(heap);
        
        tree.addCode();
        
        //print results
        System.out.println("Input analysis complete");
        for(int i =0; i<printHeap.size(); i++){
            System.out.println(printHeap.get(i));
        }
        System.out.println();
        System.out.println("Binary tree height: " + tree.treeHeight());
        System.out.println("Unique characters in the tree: " 
                + printHeap.size());
        System.out.println();
        printHeap.reHeap();
        for(int i =0; i<printHeap.size(); i++){
            System.out.println(printHeap.get(i).getLetter() + " "
                    + printHeap.get(i).code);
        }
        
        System.out.println("Shortest encoding: " +printHeap.get(printHeap.size()
                -1).getLetter() + " = " + 
                printHeap.get(printHeap.size()-1).code);
        System.out.println("Longest encoding: " + printHeap.get(0).getLetter()
                + " = " + printHeap.get(0).code);
        
        //wait for input
        System.out.println("Enter a string to be decoded: ");
        while(true){
            if(sc.hasNext("quit")){
                break;
            }
            if(sc.hasNext()){
                String codedMessage = sc.next(); 
                System.out.println(codedMessage + " = " 
                        + tree.decode(codedMessage));
            }
        }
        
        System.out.println("bye");
    }    
}
