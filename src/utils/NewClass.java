package utils;

import java.util.Scanner;

public class NewClass {
 
 public static void main(String[] args){
  Scanner sc= new Scanner(System.in);
  String s = sc.nextLine();
  int sum = 0;
  for(int i = 0 ; i < s.length() - 1 ; i++ )
   if(s.substring(i , i+1).equals("a"))
    sum += 1;
  System.out.println(sum);
 }
}

