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
    public static ArrayList <String> anagrams = new ArrayList<String>();
    public static int max = 1;

    public static void printAnagrams(){
        for(int j=0; j<anagrams.size()-1; j++){
            System.out.print(anagrams.get(j)+"\n");
        }
    }


    public static void findAnagram(ArrayList<Character> l, StringBuilder a,
                                   int n){
        if(l.isEmpty() && n<=max){ 
            anagrams.add(a.toString());
        } else if (!l.isEmpty() && n==max){ 
            return;
        } else {
            for(String word:dictonary){ //for each word in the dictonary
                boolean useWord = true;
                if(a.length()==0 ||
                   word.length()<a.substring(a.lastIndexOf(" ")+1).length() || 
                   (word.length()==a.substring(a.lastIndexOf(" ")+1).length()
                    && word.compareTo(a.substring(a.lastIndexOf(" ")+1))>=0)){
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
                            StringBuilder a2 = new StringBuilder(a);
                            if(a2.length()>0){
                                a2.append(" ");
                            }
                            a2.append(word);
                            findAnagram(l2, a2, n+1);
                        }
                        
                    }
                    
                }
            }
        }
    }

    public static void main(String[] args){
        String input = args[0].toLowerCase().replaceAll("[^a-z]","");
        ArrayList <Character> letters = new ArrayList <Character>();
        StringBuilder anagram = new StringBuilder();
        StringBuilder pattern = new StringBuilder();

        max = Integer.parseInt(args[1]);

        if (input.length()>0){
            for(int i=0; i<input.length(); i++){
                letters.add(input.charAt(i));
            }

            pattern.append("[a-z&&[^");
            for(char x='a'; x<='z'; x++){
                boolean include=false;
                for(char l:letters){
                    if(l==x){
                        include=true;
                    }
                }
                if(!include){
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
                findAnagram(letters, anagram, 0);
            }

            //sort anagrams in order before printing
            if (anagrams.size()>0){
                Collections.sort(anagrams, Collections.reverseOrder(sorter));
                printAnagrams();
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
