import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.*;
import java.awt.Point;

import java.util.Arrays;

/**
 * COSC326, January 2017, Etude 1
 * Java 7
 * Simulating the behaviour of Langton's ant.
 * @author Frida Israelsson & Johnny Flame Lee
 */

public class Ants {

    public enum Direction{
        N, E, S, W;
    }

    public static String run(HashMap<Character,Character[][]> dna,
                           char defaultState, int steps){
        HashMap<Point,Character> visitedTiles = new HashMap<Point,Character>();
        HashMap<Character,Integer> direction = new HashMap<Character,Integer>();
        Point currentTile = new Point(0,0);
        Point nextTime;
        Character state = null;
        char stepDirection = 'N';
        Character newState = null;
        int x = 0;

        //linking direction and position in dna line array
        for(Direction d: Direction.values()){
            direction.put(d.toString().charAt(0), x);
            x++;
        }

        while(steps>0){

            state = visitedTiles.get(currentTile);

            if(state==null){ //not visited before -> default state
                state = defaultState;
            }

            // getting new direction and state based on current state
            newState = dna.get(state)[direction.get(stepDirection)][1];
            stepDirection = dna.get(state)[direction.get(stepDirection)][0];

            //adds tile or overwrites tile state
            visitedTiles.put(currentTile, newState); 

            // currentTile becomes a copy of itself to not overwrite the tile
            // stored in visitedTimes when stepping 
            currentTile = new Point(currentTile);

            switch (stepDirection){
                case 'N': currentTile.move((int)currentTile.getX(),
                                           (int)currentTile.getY()+1);
                    break;
                case 'E': currentTile.move((int)currentTile.getX()+1,
                                           (int)currentTile.getY());
                    break;
                case 'S': currentTile.move((int)currentTile.getX(),
                                           (int)currentTile.getY()-1);
                    break;
                case 'W': currentTile.move((int)currentTile.getX()-1,
                                           (int)currentTile.getY());
                    break;
            }            
            steps--;
        }
        
        return "# "+(int)currentTile.getX()+" "+(int)currentTile.getY();
    }

    public static void main(String[] args){
        HashMap<Character, Character[][]> dna =
            new HashMap<Character, Character[][]>();
        Scanner commandLine = new Scanner(System.in);
        String line;
        Pattern dnaPattern = Pattern.compile("\\p{ASCII}\\s[NESW][NESW][NESW]"+
                                             "[NESW]\\s\\p{ASCII}\\p{ASCII}"
                                             +"\\p{ASCII}\\p{ASCII}");
        Character defaultState = null;
        String output = "";
  
        while (commandLine.hasNext()){
            line = commandLine.nextLine();
            Matcher m = dnaPattern.matcher(line);

            if(line.length()!=0){ //if not an empty line
                if (line.charAt(0)=='#'){
                    //this is a comment
                } else if(m.matches()){
                    Character[][] dnaLine = new Character[4][2];
                    for(int i=0; i<4; i++){
                        dnaLine[i][0] = line.charAt(i+2);
                        dnaLine[i][1] = line.charAt(i+7);
                    }
                    dna.put(line.charAt(0), dnaLine);
                    output += line+"\n";
                    defaultState = line.charAt(0);
                    // overwrites default state if scenario is NOT preceeded by
                    // an empty line
                } else if(line.matches("\\d+")){ //if line is a number
                    output += line;
                    System.out.println(output);
                    System.out.println(run(dna, defaultState, Integer.parseInt(line)));  
                    dna.clear();
                }   
            } else {
                line = commandLine.nextLine();
                m = dnaPattern.matcher(line);
                if(m.matches()){
                    Character[][] dnaLine = new Character[4][2];
                    for(int i=0; i<4; i++){
                        dnaLine[i][0] = line.charAt(i+2);
                        dnaLine[i][1] = line.charAt(i+7);
                    }
                    dna.put(line.charAt(0), dnaLine);
                    output += "\n"+line+"\n";
                } else if(line.charAt(0)=='#'){
                    //comment
                } else if(line.matches("\\d+")){
                    output += line;
                    System.out.println(output);
                    System.out.println(run(dna, defaultState, Integer.parseInt(line)));
                    dna.clear();
                }   
            }
        }
    }
}
