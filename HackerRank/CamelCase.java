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