import java.util.Scanner;
import java.util.HashMap;
import java.util.regex.*;
import java.awt.Point;

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

    public static void run(HashMap<Character, Character[][]> dna, char defaultState, int steps){
        HashMap<Point, Character> visitedTiles = new HashMap<Point, Character>();
        HashMap<Character, Integer> direction = new HashMap<Character, Integer>();
        Point currentTile = new Point();
        Character state = null;
        char stepDirection = 'N';
        Character newState = null;
        int x = 0;

        for(Direction d: Direction.values()){ //linking direction and position in dna line array
            direction.put(d.toString().charAt(0), x);
            x++;
        }

        while(steps>0){
                   
            state = visitedTiles.get(currentTile);

            System.out.println("\nwhere am I?   "+currentTile.toString());
            System.out.println("have I been here?   "+visitedTiles.containsKey(currentTile));


            if(state==null){ //not visited before -> default state
                state = defaultState;
            }
            System.out.println("I came "+stepDirection+" and here is "+state);
            
            newState = dna.get(state)[direction.get(stepDirection)][1];
            stepDirection = dna.get(state)[direction.get(stepDirection)][0];
            
            System.out.println("and now I go.. "+stepDirection);
            System.out.println("this place, "+currentTile.toString()+" turns "+newState);
            
            visitedTiles.put(currentTile, newState); // adds or overwrites tile state

            switch (stepDirection){
                case 'N': currentTile.move((int)currentTile.getX(), (int)currentTile.getY()+1);
                    break;
                case 'E': currentTile.move((int)currentTile.getX()+1, (int)currentTile.getY());
                    break;
                case 'S': currentTile.move((int)currentTile.getX(), (int)currentTile.getY()-1);
                    break;
                case 'W': currentTile.move((int)currentTile.getX()-1, (int)currentTile.getY());
                    break;
            }            
            steps--;
        }
        
        System.out.println("# "+(int)currentTile.getX()+" "+(int)currentTile.getY());
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
                    //overwrites default state if scenario is NOT preceeded by empty line
                } else if(line.matches("\\d+")){ // is a number
                    output += line;
                    System.out.println(output);
                    run(dna, defaultState, Integer.parseInt(line));
                    return;
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
                    // comment
                } else if(line.matches("\\d+")){
                    output += line;
                    System.out.println(output);
                    run(dna, defaultState, Integer.parseInt(line));
                    return;
                }   
            }
        }
    }
}