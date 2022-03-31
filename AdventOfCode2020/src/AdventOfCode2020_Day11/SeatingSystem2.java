/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdventOfCode2020_Day11;

import java.io.*;
import java.util.*;

/*
               Advent of Code 2020
    Program title:         Seating System 1
    Programmed by:         Anish Racharla
    Date:                  Saturday, 2021/7/3
 */
public class SeatingSystem2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean bEquals;
        bEquals = false;

        int nAdjOccupiedCount, nTotOccupiedCount, nRows, nCols;
        nTotOccupiedCount = 0;

        char cTemp, cNew;

        String strLine, strNewLine;

        ArrayList<String> arListMap1 = new ArrayList<String>();
        ArrayList<String> arListMap2 = new ArrayList<String>();

        BufferedReader br = null;

        //Getting the input & adding it into arListMap1.
        try {
            br = new BufferedReader(new FileReader("Input\\Day11_Input.txt"));
            while ((strLine = br.readLine()) != null) {
                arListMap1.add(strLine);
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

        //Gets the dimensions of the map (these are unchanging)
        nRows = arListMap1.size();
        nCols = arListMap1.get(0).length();

        //Using a while loop to check if bEquals is true or not. If bEquals is true, meaning the new seat map and the previous seat map are identical,
        //the loop will stop (meaning that they have reached stability and no seats have changed state)
        while (!bEquals) {
            //This variable counts how many occupied seats there are in total (the puzzle output)
            nTotOccupiedCount = 0;

            //This first for loop runs through each "row" (each arraylist index)
            for (int i = 0; i < nRows; i++) {
                strLine = arListMap1.get(i);

                //This string will hold all the new seat values for the current row
                strNewLine = "";

                //This second for loop runs through each "column" (each char in the string)
                for (int j = 0; j < nCols; j++) {
                    //This is a temp char that gets the current seat value
                    cTemp = strLine.charAt(j);

                    //bCycle works the same here as it does in it's previous use
                    //A method is called that 
                    nAdjOccupiedCount = adjSeatCheck(arListMap1, i, j);

                    //Using a switch to carry out different instructions depending on the current seat value (cTemp)
                    switch (cTemp) {
                        //If the position is an empty seat:
                        //If there are no occupied seats around it, it becomes occupied
                        //If there are > 0 occupied seats, it remains empty
                        case 'L':
                            if (nAdjOccupiedCount == 0) {
                                cNew = '#';

                                //Incrementing the total occupied seat count
                                nTotOccupiedCount++;
                            } else {
                                cNew = 'L';
                            }
                            break;
                        //If the position is an occupied seat:
                        //If there are >= 4 occuied seats around it, it becomes empty
                        //If there are < 4 occupied seats, it remains occupied 
                        case '#':
                            if (nAdjOccupiedCount >= 5) {
                                cNew = 'L';
                            } else {
                                cNew = '#';

                                //Incrementing the total occupied seat count
                                nTotOccupiedCount++;
                            }
                            break;
                        //If the position is floor, it remains floor 
                        default:        //Floor ('.')
                            cNew = '.';
                            break;
                    }

                    //The new position value is then added to this string
                    strNewLine += cNew;
                }

                //After going through all of the positions in a line and determining the new position values, the new line is added to map 2
                arListMap2.add(i, strNewLine);
            }

            //Checking if map 2 is the same as map 1. If so, the while loop ends, 
            //if not, map 1 is set to = map 2, map 2 is cleared and the process repeats
            if (arListMap1.equals(arListMap2)) {
                bEquals = true;
            } else {
                for (int i = 0; i < arListMap1.size(); i++) {
                    strLine = arListMap2.get(i);
                    arListMap1.set(i, strLine);
                }

                arListMap2.clear();
            }
        }

//        //TEST (prints the map)
//        for (int i = 0; i < nRows; i++) {
//            System.out.println(arListMap1.get(i));
//        }
        
        //Outputting the number of occupied seats
        System.out.println(nTotOccupiedCount);
    }
    
    //This method takes in the arraylist of strings that maps the seats, and the coordinates of the seat being checked for.
    //The method outputs the number of occupied seats of the first visible seats the current seat's 8 directions (think of the possible directions a queen can move in chess).
    public static int adjSeatCheck(ArrayList<String> arListSeats, int nRow, int nCol) {
        int nSeatsOccupied, nRowDiff, nColDiff;
        nSeatsOccupied = 0;

        String strBoundsLine, strTempLine;
        strBoundsLine = arListSeats.get(0);

        char cTemp;

        //Going through each row in the 1 seat radius
        for (int i = nRow - 1; i <= nRow + 1; i++) {
            //Checking if the row is in bounds
            if (i >= 0 && i < arListSeats.size()) {
                //Going through each column in the 1 seat radius
                for (int j = nCol - 1; j <= nCol + 1; j++) {

                    //Checking if the column is in bounds
                    if (j >= 0 && j < strBoundsLine.length()) {

                        //Checking if the coords of the seat being checked currently are the same as the 
                        //one being checked for. If they are, it is skipped.
                        //If not, checking if the position is an occupied seat and incrementing the counter respectively
                        if (i == nRow && j == nCol) {
                        } else {
                            nRowDiff = i - nRow;
                            nColDiff = j - nCol;
                            
                            //Extending out to the seats in each direction:
                            //'k' is the row index and 'l' is the col index, they start in 1 of the possible 8 adjacent positions around the seat being checked for.
                            //n<Row/Col>Diff sees where the adjacent positions's coordinates are relative to the seat being checked for, and are used
                            //to increment the row/col index in the direction of the position outwards (for example if the current adjacent seat is directly above,
                            //it is -1 rows and 0 cols from the current seat being checked for, and so the program will continuouslyu add -1 and 0 to the
                            //row and col indices).
                            //The program extends out into each direction until it finds a seat (empty/occupied) or reaches one of the "walls",
                            //updates the counter respectively and terminates the search in that direction.
                            for (int k = i, l = j; (k >= 0 && k < arListSeats.size()) && (l >= 0 && l < strBoundsLine.length()); k += nRowDiff, l += nColDiff) {
                                strTempLine = arListSeats.get(k);
                                
                                cTemp = strTempLine.charAt(l);
                                
                                //Incrementing the counter if the found seat is occupied.
                                //To end the loop, I set k to -2 (since the for loop ends if k < 0, also if I set k = -1 it will infinetely loop if nRowDiff = 1)
                                if (cTemp == '#') {
                                    nSeatsOccupied++;
                                    k = -2;
                                } else if (cTemp == 'L') {
                                    k = -2;
                                }
                            }
                        }
                    }
                }
            }
        }

        return nSeatsOccupied;
    }
}
