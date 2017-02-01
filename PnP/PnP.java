import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * COSC326, January 2017, Etude 7
 * Java 7
 * Finds a first move that will guarantee a win in the Peanuts and Pretzels game.
 * @author Frida Israelsson, Johnny Flame Lee, Max Jardine, Callum Grimmer
 */
public class PnP{
    public static ArrayList<possibleMove> rules = new ArrayList<>();
    public static HashMap<possibleMove, Boolean> winningMove= new HashMap<>();

    public static void takeSnack(possibleMove firstMove, int currPea,
                                 int currPre, boolean myTurn){
        if(firstMove!=null && !winningMove.get(firstMove)){
            //if this firstMove has already failed once
            return;
        }

        //swapping turns
        if(myTurn){
            myTurn=false;
        } else {
            myTurn=true;
        }

        //if the bowls are both empty..
        if(currPea==0 && currPre==0){
            if(myTurn){
                //I'm buying drinks, this firstMove has failed
                winningMove.put(firstMove, false);
            }
        } else {
            for(possibleMove m: rules){
                if(m.pea<=currPea && m.pre<=currPre){
                    if(!(currPea>=50 && m.pea==1 || currPre>=50 && m.pre==1)){
                        //do not start by taking one if there is lots in the bowls
                        if(firstMove==null){
                            takeSnack(m ,currPea-m.pea, currPre-m.pre, myTurn);
                        } else {
                            takeSnack(firstMove,currPea-m.pea,currPre-m.pre,myTurn);
                        }
                    } else {
                        winningMove.put(m, false);
                    }

                }
            }
        }
    }

    public static void main(String[] arg){
        int peanuts = 0;
        int pretzels = 0;
        Scanner input = new Scanner(System.in);        
        String line = "";

        while(true){
            if(input.hasNextLine()){
            line = input.nextLine();
            } else {
                line = "";
            }

            if(!line.equals("") && line.matches("\\d+\\p{Space}\\d+")){
                //set starting amounts
                peanuts = Integer.parseInt(line.substring(0,line.indexOf(" ")));
                pretzels = Integer.parseInt(line.substring(line.indexOf(" ")+1));

                //adding "basic rules" (take one from either bowl)
                if(peanuts>=1){
                    rules.add(new possibleMove(1,0));
                }
                if(pretzels>=1){
                    rules.add(new possibleMove(0,1));
                }

            } else if(!line.equals("") && line.matches("\\p{Punct}\\d+\\p{Space}\\p{Punct}\\d+")){
                //read rule line and store all possible moves based on rule
                String[] rule = line.split(" ");                
                int pea = Integer.parseInt(rule[0].substring(1));
                int pre = Integer.parseInt(rule[1].substring(1));
                int x, y;

                if(rule[0].contains("=") && peanuts>=pea){
                    if(rule[1].contains("=") && pretzels >=pre){
                        rules.add(new possibleMove(pea,pre));
                    } else if(rule[1].contains("<")){
                        for(y=pre-1; y>=0; y--){
                            if(pretzels >= y){
                                rules.add(new possibleMove(pea,y));
                            }
                        }
                    } else if(rule[1].contains(">")){
                        for(y=pre+1; y<=pretzels; y++){
                            if(pretzels >= y){
                                rules.add(new possibleMove(pea,y));
                            }
                        }
                    }
                } else if(rule[0].contains("<")){
                    if(rule[1].contains("=") && pretzels>=pre){
                        for(x=pea-1; x>=0; x--){
                            if(peanuts >= x){
                                rules.add(new possibleMove(x,pre));
                            }
                        }
                    } else if(rule[1].contains("<")){
                        for(x=pea-1; x>=0; x--){
                            for(y=pre-1; y>=0; y--){
                                if(peanuts>=x && pretzels>=y){
                                    rules.add(new possibleMove(x,y));
                                }
                            }
                        }
                    } else if(rule[1].contains(">")){
                        for(x=pea-1; x>=0; x--){
                            for(y=pre+1; y<=pretzels; y++){
                                if(peanuts>=x && pretzels>=y){
                                    rules.add(new possibleMove(x,y));
                                }
                            }
                        }
                    }
                } else if(rule[0].contains(">")){
                    if(rule[1].contains("=") && pretzels>=pre){
                        for(x=pea+1; x<=peanuts; x++){
                            if(peanuts>=x){
                                rules.add(new possibleMove(x,pre));
                            }
                        }
                    } else if(rule[1].contains("<")){
                        for(x=pea+1; x<=peanuts; x++){
                            for(y=pre-1; y>=0; y--){
                                if(peanuts>=x && pretzels>=y){
                                    rules.add(new possibleMove(x,y));
                                }
                            }
                        }
                    } else if(rule[1].contains(">")){
                        for(x=pea+1; x<=peanuts; x++){
                            for(y=pre+1; y<=pretzels; y++){
                                if(peanuts>=x && pretzels>=y){
                                    rules.add(new possibleMove(x,y));
                                }
                            }
                        }
                    }
                }
            }else {
                for(possibleMove m:rules){
                    winningMove.put(m, true);
                }
        
                takeSnack(null, peanuts, pretzels, false);

                for(possibleMove w:rules){
                    if(winningMove.get(w)){
                        System.out.println(w);
                        break; //remove break to print all winning moves
                    } else {
                        winningMove.remove(w);
                    }
                }
                if(winningMove.size()==0){
                    System.out.println("0 0");
                }

                //reset possible moves
                rules.removeAll(rules);
                winningMove.clear();

                if(!input.hasNext()){
                    return;
                }
            }
        }  
    }
}

class possibleMove{
    int pea;
    int pre;

    public possibleMove(int p1, int p2){
        pea = p1;
        pre = p2;
    }

    public String toString(){
        String p = pea+" "+pre;
        return p;
    }
}
