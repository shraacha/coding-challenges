/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day07;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Handy Haversacks 2
    Programmed by:         Anish Racharla
    Date:                  Monday, 2020/12/7
 */
public class HandyHaversacks2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nListLength, nCount = 0, nIndex;
        boolean bContains;
        String strBagContain, word;

        ArrayList<String> arListEntries = new ArrayList<String>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day07_Input.txt"));
            while ((word = br.readLine()) != null) {
                arListEntries.add(word);
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

        nListLength = arListEntries.size();

        String[] arstrParents = new String[nListLength];

        for (int i = 0; i < nListLength; i++) {
            arstrParents[i] = arListEntries.get(i);
        }

        ArrayList<ArrayList> arlistarlistChildrenInfo = new ArrayList<ArrayList>();
        
        //Adding the string arraylists to and arraylist arraylist, and modifying the original list entries array to only hold the parent bag names.
        for (int i = 0; i < nListLength; i++) {
            arlistarlistChildrenInfo.add(i, processLine(arstrParents, i));
//            arListEntries.set(i, word);
//            System.out.println(arstrParents[i] + " ");                          //TEST
//            for(int j = 0; j < arlistarlistChildren.get(i).size(); j ++) {      //TEST
//                System.out.println(arlistarlistChildren.get(i).get(j));         //TEST
//            }
//            System.out.println("");                                             //TEST
        }
        
        nIndex = linearSearch(arstrParents, "shiny gold");
        
//        System.out.println(nIndex);
        
        nCount = numContained(arstrParents, arlistarlistChildrenInfo, nIndex, 0, 1);
        
        
        //Output with extra info
//        System.out.println("The number of bags inside a shiny gold bag is: " + nCount);
        
        //Puzzle output
        System.out.println(nCount);
    }

    public static ArrayList<ArrayList> processLine(String[] arstrParents, int nIndex) {
        /*
            Purpose: Processes the input information.
            Pre-condition: A string array, and an index must be passed.
            Post-condition: Modifies the string array item to be just the parent bag name, and returns an arraylist of the children bag count and another of their names.
         */
        ArrayList<ArrayList> arlistarlistChildrenInfo = new ArrayList<ArrayList>();
        ArrayList<String> arListChildren = new ArrayList<String>();
        ArrayList<Integer> arListChildrenNum = new  ArrayList<Integer>();
        int nTemp1 = 0, nTemp2;
        String strTemp, strLine;

        strLine = arstrParents[nIndex];

        nTemp2 = strLine.indexOf(" ");
        nTemp2 = strLine.indexOf(" ", nTemp2 + 1);

        arstrParents[nIndex] = strLine.substring(0, nTemp2);

        if (!strLine.contains("no other bags")) {
            strTemp = strLine.substring(strLine.indexOf("contain") + 7);
            for (int i = 0; i < strTemp.length(); i++) {
                if (strTemp.charAt(i) == ',') {
                    nTemp1++;
                }
            }

            String[] arStrBagsContained = strTemp.split(",", nTemp1 + 1);

            for (int i = 0; i < arStrBagsContained.length; i++) {
                nTemp2 = Integer.parseInt(arStrBagsContained[i].substring(1, 2));
                
                arListChildrenNum.add(nTemp2);
                
                strTemp = arStrBagsContained[i].substring(3, arStrBagsContained[i].indexOf("bag") - 1);

                arListChildren.add(strTemp);
            }
        }
        
        arlistarlistChildrenInfo.add(arListChildrenNum);
        arlistarlistChildrenInfo.add(arListChildren);
        
        return arlistarlistChildrenInfo;
    }

    public static int numContained(String[] arstrParents, ArrayList<ArrayList> arlistarlistChildrenInfo, int nIndex, int nCount, int nOuterBagCount) {
        /*
            Purpose: Checks the number of bags within a given bag.
            Pre-condition: A string array, an array list containing an arraylist of an arraylist containing an arraylist of ints and one of strings
            (phew, that's alot), an index, a bag count, and an "outer bag count" must be passed.
            Post-condition: Returns the total number of bags within the initial one.
         */
        ArrayList<Integer> arListChildrenNum = new ArrayList<Integer>();
        ArrayList<String> arListChildrenName = new ArrayList<String>();
        
        arListChildrenNum = (ArrayList<Integer>) arlistarlistChildrenInfo.get(nIndex).get(0);
        arListChildrenName = (ArrayList<String>) arlistarlistChildrenInfo.get(nIndex).get(1);

        if (arListChildrenName.isEmpty()) {
            return 0;
        }
        
        //Adding the bag count to the total and finding the number of nested bags
        for (int i = 0; i < arListChildrenName.size(); i++) {
            nCount += arListChildrenNum.get(i) * nOuterBagCount;
            
            //Must multiply the count of the current child by the count of it's parent (thus the product of the num of child bags and the num of outer bags is passed to the function for nOuterBagCount)
            nCount += numContained(arstrParents, arlistarlistChildrenInfo, linearSearch(arstrParents, arListChildrenName.get(i)), 0, arListChildrenNum.get(i) * nOuterBagCount);
        }
        
        return nCount;
    }

    static public int linearSearch(String[] A, String B) {
        /*
            Purpose: Does a simple linear search of a given string array for a given string.
            Pre-condition: A string array and a string to find must be passed.
            Post-condition: Returns the string's index **the string should be in the array, thus the program does not account for a missing string**
         */
        String strTemp;

        for (int k = 0; k < A.length; k++) {
            strTemp = A[k];

            if (strTemp.equals(B)) {
                return k;
            }
        }

        return -1;
    }
}
