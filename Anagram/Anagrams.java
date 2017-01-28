import java.util.Scanner;


public class Anagrams{
    //dictonary (arraylist?)

    public static String[] findAnagram(char[] letters, String[] anagram,
                                       int nrWords){
        //base case = no more letters && nrWords==anagram.length()?
        //   return: result anagram

        //if letters remaining, but nrWords==anagram.length():
        //   return: empty String[];
        
        //for each word in dictonary:
        //   if first letter part of letters?
        //      check the rest of word... word can be used?
        //         return:
        //         findAnagram(remaining input letters,anagram[]+word,nrWords++)
        //   if end of dictonary and no match?
        //      return: empty String[]

    }

    public static void main(String[] args){
        String input = args[0].toLowerCase().replaceAll("[^a-z]","");
        char[] letters = new char[input.length()];
        int nrWords = Integer.parseInt(args[1]);
        String anagram = new String[nrWords];

        Scanner s = new Scanner(System.in);
        while(s.hasNextLine()){
            //read into dictonary
        }

        //if input > 0
        //  findAnagram()

        //for each String in result:
        //   print String
        //   if more strings: print " "
        //   else: print "\n"
        
    }
}

/*
 * can we use the same word several times?
 * is the dictonary in alphabetic order?
 * use recursion? -> need to store/sort results.. or do we?
 * asuming ok to use strings here..?
 * Should we build a tree? What kind? (B-tree? Trie? )
 */
