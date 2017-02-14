/**
 * CSC 3102 - Advanced Data Structures and Algorithm Analysis
 * Dr. Kooima
 * Programming Project 2
 * 28 April 2016
 * @author seanmarino
 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.function.BiConsumer;
import java.util.Hashtable;
import java.util.*;
import java.io.*;

public class Graph 
{   
        public static Hashtable<Character, LinkedList> nodes = new Hashtable<>();
        
        /*
        Creates initial node
        */
	public void insertNode(Node n)
	{
		nodes.put(n.character, new LinkedList<>());
	}
        /*
        Inserts two nodes into the graph
        */
        public void insert(Node node1, Node node2)
        {
            nodes.putIfAbsent(node1.character, new LinkedList<>());
            nodes.putIfAbsent(node2.character, new LinkedList<>());
            addNodes(node1, node2);
        }
        /*
        Ensure that vertices with label X and Y are present in the graph
        and add a directed edge from X to Y
        - Connects the nodes
        */
	public void addNodes(Node start,Node end)
	{
		LinkedList<Node> newList = nodes.get(start.character);
                newList.add(end);
                nodes.replace(start.character, newList);
	}
        /*
        Removes the edge from X to Y. Don't worry about
        removing any associated vertices.
        - Disconnects the nodes
        */
        public void removeNodes(Node start, Node end)
        {
                LinkedList<Node> newList = nodes.get(start.character);
                newList.remove(end);
                nodes.replace(start.character, newList);
        }
        /*
        Returns the child node
        */
	private Node getChildNode(Character n)
	{
		LinkedList<Node> children = nodes.get(n);
                for (Node child : children) {
                    if (child.visited == false) {
                        return child;
                    }
                }
                return null;
	}
        /*
        Breadth First Search Method
        Prints a breadth-first traversal of the graph beginning
        at the vertex labeled X.
        */
	public void breadth(Character key)
	{
		Queue q=new LinkedList();
		q.add(key);
                System.out.print(key + " ");
                    visited(key);
		while(!q.isEmpty())
		{
			char n = (Character)q.remove();
                        Node child=null;
                            LinkedList<Node> weightList = new LinkedList();
                            while((child=getChildNode(n))!=null)
                            {
                                visited(child.character);
                                System.out.print(child.character + " ");
                                q.add(child.character);
                                        weightList.add(child);
                            }
		}
		clearNodes();
	}
        /*
        Depth First Search Method
        Print a depth-first traversal of the graph beginning at
        the vertex labeled X.
        */
	public void depth(Character character)
	{
		Stack<Character> s=new Stack();
		s.push(character);
                visited(character);
		while(!s.isEmpty())
		{
			Character n = s.pop();
                        System.out.print(n + " ");
			Node child = null;
                         while((child = getChildNode(n)) != null)
			{
				visited(child.character);
				//printNode(child);
				s.push(child.character);
			}
		}
		clearNodes();
	}	
        
        private void visited(Character key)
        {
            BiConsumer<? super Character,? super LinkedList<Node>> func;
            func = (Character character, LinkedList<Node> connections) -> {
                connections.stream().filter((child) -> (child.character == key)).forEach((child) -> {
                    child.visited = true;
                });
            };
            nodes.forEach((BiConsumer<? super Character, ? super LinkedList>) func);
        }
	/*
        Clears visited nodes
        */
	private void clearNodes()
	{
		BiConsumer<? super Character,? super LinkedList<Node>> func;
            func = (Character character, LinkedList<Node> connections) -> {
                connections.stream().forEach((node) -> {
                    node.visited = false;
                });
                };
            nodes.forEach((BiConsumer<? super Character, ? super LinkedList>) func);
	}
    /*
    Creates a new Graph
    */
    public static Graph g = new Graph();
    
    /*
    Main Method
    */
    public static void main(String[] args) throws FileNotFoundException
    {
        Scanner scan = new Scanner(new File("input.txt"));
        String input;
        String[] inputArray;
        String inputType;
        LinkedList<Character> added = new LinkedList();
        
      while (scan.hasNextLine()) 
      {
          input = scan.nextLine();
          inputArray = input.split(" ");
          inputType = inputArray[0];
          int idx1 = 0;
          int idx2 = 0;
          char first = inputArray[1].charAt(0);
          
          if (inputType.equals("add")) {
              char second = inputArray[2].charAt(0);
              if (added.contains(first)) 
              {
                  idx1 = added.indexOf(first);
              } 
              else 
              {
                  g.insertNode(new Node(first));
                  added.add(first);
                  idx1 = Graph.nodes.size()-1;
              }
              if (added.contains(second)) 
              {
                  idx2 = added.indexOf(second);
              } 
              else 
              {
                  g.insertNode(new Node(second));
                  added.add(second);
                  idx2 = Graph.nodes.size()-1;
              }
              g.addNodes(new Node(first), new Node(second));
          }
          if (inputType.equals("breadth")) {
              g.breadth(inputArray[1].charAt(0));
              System.out.println();
          }
          if (inputType.equals("depth")) {
              g.depth(inputArray[1].charAt(0));
          }
          if (inputType.equals("remove")) {
              char second = inputArray[2].charAt(0);
              g.removeNodes(new Node(first), new Node(second));
              System.out.println();
          }
          
      }
      System.out.println();
            }
}
