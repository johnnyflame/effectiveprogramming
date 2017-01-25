import java.util.Scanner;
import java.util.regex.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * COSC326, January 2017, Etude 11
 * Java 7
 * Finds anagrams of input using words in provided dictonary.
 * @author Frida Israelsson & Johnny Flame Lee
 */

public class Anagrams{
    public static ArrayList <String> dictonary = new ArrayList<String>();

    public static void findAnagram(ArrayList<Character> l, ArrayList<String> a,
                                   int n, int t){
        if(l.isEmpty() && n<=t){ 
            for(String s:a){
                System.out.print(s+" ");
            }
            System.out.print("\n");
        } else if (!l.isEmpty() && n==t){ 
            return;
        } else {
            for(String word:dictonary){ //for each word in the dictonary
                boolean useWord = true;
                if(a.size()==0 || word.length()<a.get(a.size()-1).length() ||
                   (word.length()==a.get(a.size()-1).length()
                    && word.compareTo(a.get(a.size()-1))>=0)){
                    //if words have been added: the new word need to be shorter
                    //than the last or if the same length, come after in
                    //alphabetic order.
                    for(int i=0; i<word.length(); i++){ //for each letter
                        if(!l.contains(word.charAt(i))){
                            //if this letter is not of available letters
                            useWord=false;
                        }
                    }
                    if(useWord){
                        ArrayList<Character> l2 = new ArrayList<Character>(l);
                        for(int j=0; j<word.length(); j++){
                            if(l2.contains(word.charAt(j))){
                                l2.remove(l2.indexOf(word.charAt(j)));
                            } else {
                                useWord=false;
                                //the word uses several instances of a letter
                                //that only occurce once in letters.
                            }
                        }
                        if(useWord){
                            ArrayList<String> a2 = new ArrayList<String>(a);
                            a2.add(word);
                            findAnagram(l2, a2, n+1, t);
                        }
                        
                    }
                    
                }
            }
        }
    }

    public static void main(String[] args){
        String input = args[0].toLowerCase().replaceAll("[^a-z]","");
        ArrayList <Character> letters = new ArrayList <Character>();
        int nrWords = Integer.parseInt(args[1]);
        ArrayList <String> anagram = new ArrayList <String>();
        StringBuilder pattern = new StringBuilder();

        if (input.length()>0){
            for(int i=0; i<input.length(); i++){
                letters.add(input.charAt(i));
            }

            pattern.append("[a-z&&[^");
            for(char x='a'; x<='z'; x++){
                boolean b=false;
                for(char l:letters){
                    if(l==x){
                        b=true;
                    }
                }
                if(!b){
                    pattern.append(x);
                }
            }
            pattern.append("]]{1,"+input.length()+"}");        
            Pattern p = Pattern.compile(pattern.toString());

            Scanner s = new Scanner(System.in);
            while(s.hasNextLine()){
                String line = s.nextLine();
                Matcher m = p.matcher(line);
                //add words that only contain right letters, have not already
                // been added and are not the same as the input.
                if(m.matches()&&!dictonary.contains(line)&&!line.equals(input)){
                    dictonary.add(line);
                }
            }

            StringLengthComparator sorter = new StringLengthComparator();
            
            //sorting dictonary based on word length
            Collections.sort(dictonary, sorter);

            if (dictonary.size()>0){
                findAnagram(letters, anagram, 0, nrWords);
            }
        } 
    }

    static class StringLengthComparator implements Comparator<String>{
    
        public int compare(String s1, String s2){
            if(s1.length()>s2.length()){
                return -1;
            } else if(s1.length()<s2.length()){
                return 1;
            } else {
                return 0;
            }

        }
    }   
}
