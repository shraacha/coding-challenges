package AdventOfCode2020_Day13;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Shuttle Search 2
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/7/7
 */
public class ShuttleSearch1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long lTargetTimestamp, lEarliestTime, lTempEarliestTime, lProd;
        lEarliestTime = 0;

        int nTempEarlyBusID, nEarlyBusID;
        nTempEarlyBusID = 0;
        nEarlyBusID = 0;
        
        String strTemp;

        ArrayList<String> arListInput = new ArrayList<String>();
        ArrayList<Integer> arListBusId = new ArrayList<Integer>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day13_Input.txt"));
            String strIn;
            while ((strIn = br.readLine()) != null) {
                arListInput.add(strIn);
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

        lTargetTimestamp = Integer.parseInt(arListInput.get(0));
        
        //Splitting the second input line with "," as the regex to get all the bus IDs
        String[] arstrBusID = arListInput.get(1).split(",");

        //Getting the relevant bus ids, discarding the Xs
        for (int i = 0; i < arstrBusID.length; i++) {
            strTemp = arstrBusID[i];

            if (!strTemp.equals("x")) {
                arListBusId.add(Integer.parseInt(strTemp));
            }
        }

        //Finding the ID of the first bus that can be taken
        for (int i = 0; i < arListBusId.size(); i++) {
            nTempEarlyBusID = arListBusId.get(i);
            
            //Getting the closest multiple of bus 'i' under the target timestamp, and then adding one loop time to that
            lTempEarliestTime = ((lTargetTimestamp / nTempEarlyBusID) * nTempEarlyBusID) + nTempEarlyBusID;
            
            //Comparing the current bus ID timestamp to the bus ID timestamp closest to & after the target timestamp
            //If it is earlier, it replaces the closest timestamp's place
            if (i == 0) {
                lEarliestTime = lTempEarliestTime;
                
                nEarlyBusID = nTempEarlyBusID;      //Noting down the id of the bus (for the puzzle answer)
            } else {
                if (lTempEarliestTime < lEarliestTime) {
                    lEarliestTime = lTempEarliestTime;
                    
                    nEarlyBusID = nTempEarlyBusID;      //Noting down the id of the bus (for the puzzle answer)
                }
            }
        }
        
        //Puzzle answer/output
        lProd = nEarlyBusID * (lEarliestTime - lTargetTimestamp);
        
        System.out.println(lProd);
    }
}
