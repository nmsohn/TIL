Alice wrote a sequence of words in CamelCase as a string of letters, s, having the following properties:

It is a concatenation of one or more words consisting of English letters.
All letters in the first word are lowercase.
For each of the subsequent words, the first letter is uppercase and rest of the letters are lowercase.
Given , s print the number of words in s on a new line.


## Function Description

Complete the camelcase function in the editor below. It must return the integer number of words in the input string.

camelcase has the following parameter(s):
- s: the string to analyze

## Input Format

A single line containing string s.

## Output Format

Print the number of words in string s.

## Sample Input
```
saveChangesInTheEditor
```
## Sample Output
```
5
```

## Solution
```
import java.io.*;
import java.util.*;
import java.util.regex.*;

class Main {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    String s = sc.nextLine();
    sc.close();

    //count upper case +1
    System.out.print(s.chars().filter(c -> Character.isUpperCase(c)).count()+1);

    //pattern, matcher
     Pattern pattern = Pattern.compile("[A-Z]"); //\p{Lu}
     Matcher matcher = pattern.matcher(s);
     int count = 0;
     while (matcher.find()) {
      count+= matcher.group(0).length();
    }
    System.out.print(count+1);

    //count split
    System.out.print(s.split("[A-Z]").length);

    //ascii
    int count2 = 0;
    for(int i=0; i < s.length(); i++){
      for(char c='A'; c<='Z';c++){
        if(s.charAt(i) == c){
          count2++;
        }
      }
    }
    System.out.print(count2+1);

    //count replaceAll
    System.out.print(s.length() - s.replaceAll("[A-Z]","").length()+1);
  }
} 
```