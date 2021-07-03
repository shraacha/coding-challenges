/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day10;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Adapter Array 2
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/6/9

    Used the help of "Alvin the Programmer" on youtube and "geeksforgeeks" site on Dynamic Programming.
    Youtube link: https://www.youtube.com/watch?v=_f8N7qo_5hA
 */
public class AdapterArray2 {

    /**
     * @param args the command line arguments
     */
    
    private static long[] arlMemo;
    
    public static void main(String[] args) {
        long lPaths;
        
        String strWord;
        
        //Getting the input.
        ArrayList<Integer> arListEntries = new ArrayList<>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day10_Input.txt"));
            while ((strWord = br.readLine()) != null) {
                arListEntries.add(Integer.parseInt(strWord));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        //Sorting the list of inputs.
        insertionSort(arListEntries);
        
        //Adding the start and end values.
        arListEntries.add(0, 0);
        arListEntries.add(arListEntries.get(arListEntries.size() - 1) + 3);
        
        //This array is for memoization. It later stores the total number of paths found for a specific node in the
        //countPaths method.
        arlMemo = new long[arListEntries.size()];
        
        lPaths = countPaths(arListEntries, 0);
        
        //Output with extra info
//        System.out.println("The total number of distinct paths is: " + lPaths);
        
        //Puzzle output
        System.out.println(lPaths);
    }
    
    //Simple insertion sort method.
    public static void insertionSort(ArrayList<Integer> arListInt){
        int nTempI, nTempJ, i, j;
        
        for (i = 1; i < arListInt.size(); i++) {
            nTempI = arListInt.get(i);
            j = i - 1;
            while ((j >= 0) && nTempI < arListInt.get(j)) {
                nTempJ = arListInt.get(j);
                
                arListInt.set(j, arListInt.get(j + 1));
                arListInt.set((j + 1), nTempJ);
                
                j--;
            }
        }
    }
    
    //This method uses Dynamic Programming:
    //To expolore all the paths of the tree through depth first search, you can recursively search by going between the closest nodes first, then going backwards
    //and exploring progressively farther nodes until the entire tree is explored.
    
    //Dynamic programming greatly cuts down the complexity of the recursive search by storing the total number of paths found for already fully searched nodes.
    //By exploring the longest path first and then essentially going backwords through the tree towards the other side, you fully explore the ends of the tree and 
    //can apply the stored node path totals once you encounter the same nodes again.
    public static long countPaths(ArrayList<Integer> arListInt, int nIndex) {
        //Checking if a node has already been fully explored.
        //Returns that value if it already has been.
        //A node can have 1 path to the end at minimum, thus != 0 serves to check if the node hasn't been searched (because it hasn't updated the memo).
        if (arlMemo[nIndex] != 0) {
            return arlMemo[nIndex];
        }
        
        //If the program is at the end of the list (max rated joltage) then it is at the end of the path, and 1 path has been found.
        if (nIndex == arListInt.size() - 1) {
            return 1;
        }
        
        long nTotal = 0;
        
        //The program basically explores all the way down to the leaves from the specified node, 
        //finds how many paths to the end/connected leaves there are and then crawls back up to the original node, adding up the number of found paths.
        
        //First, the program searches for the closest node (gap of 1). 
        //If it exists, then it jumps to that node.
        //If not, it moves on.
        if (nIndex + 1 < arListInt.size() && (arListInt.get(nIndex + 1) - arListInt.get(nIndex)) <= 3) {
            nTotal += countPaths(arListInt, nIndex + 1);
        }
        
        //Then, the program searches for the second possible closest node (one node can connect to maximum 3 nodes; gap of 1, 2 and 3 respectively)
        //It then does the same as the last but for the new node.
        if (nIndex + 2 < arListInt.size() && (arListInt.get(nIndex + 2) - arListInt.get(nIndex)) <= 3) {
            nTotal += countPaths(arListInt, nIndex + 2);
        }
        
        //Now the program searches for the third possible node, and does the same as the last 2 if statements.
        if (nIndex + 3 < arListInt.size() && (arListInt.get(nIndex + 3) - arListInt.get(nIndex)) <= 3) {
            nTotal += countPaths(arListInt, nIndex + 3);
        }
        
        //Now the program stores the node's total at its index for later use, and then returns the total.
        arlMemo[nIndex] = nTotal;
        return nTotal;
    }
}
