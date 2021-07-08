package AdventOfCode2020_Day13;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Shuttle Search 2
    Programmed by:         Anish Racharla
    Date:                  Wednesday, 2021/7/8
 */
public class ShuttleSearch2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long lFirstBusTime, lTempNextBusTime;
        lFirstBusTime = 0;

        String strTemp;

        ArrayList<String> arListInput = new ArrayList<String>();

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader("Input\\Day13_Test2.txt"));
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

        //Splitting the second input line with "," as the regex to get all the bus IDs
        String[] arstrBusID = arListInput.get(1).split(",");

        /*
         *Finding the timestamp for the first bus ID such that each subsequent listed bus ID departs at that subsequent minute
         *'i' is used to iterate through the bus IDs, and 'j' is the multiplier for the first bus ID in the list.
         *How this works:
         *The program starts at the beginning of the bus ID array and with a multiplier of 0.
         *It stores the product of the first bus ID and the multiplier (lFirstBusTime).
         *It then moves forward an ID. 
         *
         *If the ID is an 'x', it is skipped.
         *If not, then the program will store the value of the first multiple of the current ID that is greater than lFirstBusTime.
         *
         *If this value is == lFirstBusTime + i, meaning that the bus departs 'i' minutes after lFirstBusTime, and it meets the requirements for the puzzle,
         *it will move on to check the next bus ID.
         *If not, 'i' will be reset back to the first bus ID in the list, and the multiplier will be incrmented by 1
         *
         *This loop continues until the program reaches the end of the list of bus IDs without having to reset 'i'
         */
        
        //Well... this works for small input lists; Day13_Test1 and Day13_Test2 worked perfectly fine.
        //I had to stop the program after 5 minutes for the real Input...
        for (int i = 0, j = 0; i < arstrBusID.length; i++) {
            strTemp = arstrBusID[i];

            //Iterating through the list of bus IDs
            //Storing the timestamp of first bus ID multiplied by j
            if (i == 0) {
                lFirstBusTime = Integer.parseInt(strTemp) * j;
            } else {
                //Checking if the current bus ID has a multiple 'i' minutes greater than lFirstBusTime
                if (!arstrBusID[i].equals("x")) {
                    lTempNextBusTime = (lFirstBusTime / Integer.parseInt(strTemp)) * Integer.parseInt(strTemp) + Integer.parseInt(strTemp);

                    if (lTempNextBusTime != (lFirstBusTime + i)) {
                        i = -1;
                        j++;
                    }
                }
            }
        }

        System.out.println(lFirstBusTime);
    }
}
