/*
Data Structures 2203
Assignment 3 Huffman Tree
Chelsea McFarland
6/21/15
This class builds a Huffman tree given a min heap. It also adds the coding to 
each nod, decodes a string of binary digits, and returns the height of the 
tree.
 */
package assign3;


public class HuffTree {
    private Node root;
    
    public HuffTree(){
    }
    
    public HuffTree(Node root){
        this.root = root;
    }
    
    //create a new HuffTree given a left and right tree
    public HuffTree(HuffTree leftTree, HuffTree rightTree){
        this.root = new Node(leftTree.getRoot(), rightTree.getRoot());
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    //returns a new huffman tree given a min heap
    public static HuffTree buildTree(HeapArray heap){
        HuffTree returnTree = new HuffTree();
        
        while(heap.size() > 1){
            HuffTree treeOne = new HuffTree(heap.remove());
            HuffTree treeTwo = new HuffTree(heap.remove());
            HuffTree sum = new HuffTree(treeOne, treeTwo);
            heap.insert(sum.getRoot());
            returnTree = sum;
        }
        return returnTree;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }
    
    //used to print out a string representation of the tree
    //helpful for debugging
    private void preOrderTraverse(Node node, int depth, StringBuilder sb){
        for(int i=0; i<depth; i++)
            sb.append("      ");
        
        if(node == null)
            sb.append("null\n");
        
        else{
            sb.append(node.toString());
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
       
    }
    
    //wrapper for tree height method
    public int treeHeight(){
        return treeHeight(root);
    }
    
    //find the tree height
    private int treeHeight(Node node){
        if(node == null){
            return 0;
        }
        else{
            return 1 + Math.max(treeHeight(node.left), treeHeight(node.right));
        }
    }
    
    //wrapper for addCode
    public void addCode(){
        StringBuilder sb = new StringBuilder();
        addCode(root, 1, sb);
    }
    
    //adds the code to the leaf nodes
    private void addCode(Node node, int depth, StringBuilder code){
        if(node.getLetter() != '$'){
            node.code = code;
        }
        else{
            StringBuilder leftCode = new StringBuilder(code);
            leftCode.append(0);
            addCode(node.left, depth + 1, leftCode);
            StringBuilder rightCode = new StringBuilder(code);
            rightCode.append(1);
            addCode(node.right, depth + 1, rightCode);
        }
    }
    
    //decodes the given string
    public String decode(String codedMessage){
        StringBuilder result = new StringBuilder();
        
        //start at the top
        Node currentNode = root;
        for(int i=0; i<codedMessage.length(); i++){
            //if it's a one, go right
            if(codedMessage.charAt(i) == '1'){
                currentNode = currentNode.right;
            }
            //if it's a zero, go left
            else{
                currentNode = currentNode.left;
            }
            //if it's a leaf, add that char to the string
            if(currentNode.isLeaf()){
                result.append(currentNode.getLetter());
                currentNode = root;
            }
        }
        return result.toString();
    }
}
