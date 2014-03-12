package utils;
public class Separ {
 public Separ(String opt)
 {
  
 }
 
 public static String[] getval(String opt){
  int[] in=new int[20];
  int intm=opt.indexOf(":");
  for(int i=0;i<opt.length();i++)
   if(opt.startsWith(",", i)){
    in[i]=i+1;
    in[i-1]=intm;
    intm=i+1;
   }
  
  String[] s=new String[in.length];
  
  int si=0;
  for(int i=0;i<in.length-1;i++){   
   if(in[i]!=0 && in[i+1]!=0){ 
    s[si]=opt.substring(in[i], in[i+1]); 
    si+=1;
   }
   System.out.println(s[i]);
  
  }
  return s;
 }
}
