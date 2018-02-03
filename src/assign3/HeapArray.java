/*
Data Structures 2203
Assignment 3 Huffman Tree
Chelsea McFarland
6/21/15
This class will be used for sorting the list of nodes by their weights. It will 
later be converted into the Huffman tree. It also contains methods for removing 
nodes, adding nodes, and reordering the heap based on code length.
 */
package assign3;

import java.util.ArrayList;

/**
 *
 * @author Chelsea
 */
public class HeapArray {
    private ArrayList<Node> heapArray;
    
    public HeapArray(ArrayList<Node> unsorted){
        buildHeap(unsorted);
        shrinkHeap();
    }
    
    //build a min heap given an unsorted ArrayList
    public void buildHeap(ArrayList<Node> unsorted){
        this.heapArray = unsorted;
        
        //sort array into heap
        int n = 1;
        while(n < heapArray.size()){
            n++;
            int child = n-1;
            int parent = (child - 1) / 2;
            while(parent >= 0 && heapArray.get(parent).compareTo(
                    heapArray.get(child)) < 0){
                swap(parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }                
        }            
    }  
    
    //reorder heap using heapsort
    //will be in order from least to greatest
    public void shrinkHeap(){
        int n = heapArray.size();
        while(n>0){
            n--;
            swap(0,n);
            int parent = 0;
            while(true){
                int leftChild = 2 * parent + 1;
                if(leftChild >= n){
                    break;
                }
                int rightChild = leftChild + 1;
                int maxChild = leftChild;
                if(rightChild < n && heapArray.get(leftChild).compareTo(
                    heapArray.get(rightChild)) < 0)
                    maxChild = rightChild;
                if(heapArray.get(parent).compareTo(heapArray.get(
                        maxChild)) < 0){
                    swap(parent, maxChild);
                    parent = maxChild;
                }
                else
                    break;
            }
        } 
    }
    
    public Node get(int i){
        return heapArray.get(i);
    }
     
    public int size(){
        return heapArray.size();
    }
   
    //insert node into heap
    public void insert(Node newNode){
        heapArray.add(newNode);
        int child = heapArray.size()-1;
        int parent = (child - 1) / 2;
        while(parent >= 0 && heapArray.get(parent).compareTo(
                    heapArray.get(child)) > 0){
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
    }
    
    public Node remove(){
        if(heapArray.isEmpty()){
            return null;
        }
        
        Node returnNode = heapArray.get(0);
        
        if(heapArray.size() == 1){
            heapArray.remove(0);
            return returnNode;
        }
            
        heapArray.set(0, heapArray.remove(heapArray.size()-1));
        int parent = 0;
        while(true){
            int leftChild = (2 * parent) + 1;
            int rightChild = 1 + leftChild;
            int minChild = leftChild;
            if(leftChild >= heapArray.size())
                break;
            if(rightChild < heapArray.size() && heapArray.get(rightChild)
                    .compareTo(heapArray.get(leftChild)) < 0)
                minChild = rightChild;
            if(heapArray.get(parent).compareTo(heapArray.get(minChild)) > 0){
                swap(parent, minChild);
                parent = minChild;
            }
            else
                break;
        }
        return returnNode;
    }
    
    //swap elements at i and j 
    private void swap(int i, int j){
        Node temp = heapArray.get(i);
        heapArray.set(i, heapArray.get(j));
        heapArray.set(j, temp);
    }
    
    //reorder the heap based on code length
    public void reHeap(){
        //this.heapArray = unsorted;
        
        //sort array into heap
        int n = 1;
        while(n < heapArray.size()){
            n++;
            int child = n-1;
            int parent = (child - 1) / 2;
            while(parent >= 0 && heapArray.get(parent).compareCode(
                    heapArray.get(child)) > 0){
                swap(parent, child);
                child = parent;
                parent = (child - 1) / 2;
            }                
        }
        
        int k = heapArray.size();
        while(k>0){
            k--;
            swap(0,k);
            int parent = 0;
            while(true){
                int leftChild = 2 * parent + 1;
                if(leftChild >= k){
                    break;
                }
                int rightChild = leftChild + 1;
                int maxChild = leftChild;
                if(rightChild < k && heapArray.get(leftChild).compareCode(
                    heapArray.get(rightChild)) > 0)
                    maxChild = rightChild;
                if(heapArray.get(parent).compareCode(heapArray.get(
                        maxChild)) > 0){
                    swap(parent, maxChild);
                    parent = maxChild;
                }
                else
                    break;
            }
        }
    }  
}
