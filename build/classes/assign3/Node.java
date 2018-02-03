/*
Data Structures 2203
Assignment 3 Huffman Tree
Chelsea McFarland
6/21/15
This class will be the building block for the heap and the Huffman tree. Each 
node will conatin the weight of it's tree, and a character if it is a leaf. The 
nodes will contain '$' to indicate that they do not contain relevent characters.
The methods output either an int or a char, and the expected inputs are ints and
chars.
 */
package assign3;


public class Node implements Comparable<Node> {
    private int weight;
    private char letter;
    Node right;
    Node left;
    StringBuilder code;
    
    //create a new node with weight and char
    //leaf
    public Node(int weight, char letter){
        this.weight = weight;
        this.letter = letter;
        this.right = null;
        this.left = null;
    }
    
    //create a new node with weight
    //letter = '$', central node 
    public Node(int weight){
        this.weight = weight;
        this.letter = '$';
    }
    
    //create a new node combining 2
    public Node(Node leftTree, Node rightTree){
        this.weight = leftTree.getWeight() + rightTree.getWeight();
        this.letter = '$';
        this.left = leftTree;
        this.right = rightTree;
    }
    
    //used for incrementing a node's weight by one
    public void incWeight(){
        this.weight ++;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setLetter(char letter){
        this.letter = letter;
    }
    
    public char getLetter(){
        return this.letter;        
    }
    
    //returns true if the node is a leaf
    public boolean isLeaf(){
        return this.letter != '$';
    }
    
    @Override
    public String toString(){
        return  this.letter + " " + this.weight;
    }
    
    @Override
    public int compareTo(Node data){
        if(this.weight > data.getWeight())
            return 1;
        else if(this.weight < data.getWeight())
            return -1;
        else
            return 0;
    }
    
    //used to order nodes based on code
    public int compareCode(Node data){
        if(this.code.length() > data.code.length())
            return 1;
        else if(this.code.length() < data.code.length())
            return -1;
        else
            return 0;
    }
}
