package minigoogle;

import java.util.*;
import java.util.Map.Entry;
import java.io.*;
public class SearchIndex{

    static HashMap<String,List<String>> hm=new HashMap<String,List<String>>();
    public static void readIndex(){
    //read from file 
      try{
          File toRead=new File("res/searchIndex.txt");
          FileInputStream fis=new FileInputStream(toRead);
          
          Scanner sc=new Scanner(fis);

          //read data from file line by line:
          String currentLine;
          while(sc.hasNextLine()){
              currentLine=sc.nextLine();
              List<String> urlsList = null;
              if (currentLine.substring(currentLine.indexOf(":") + 1).isEmpty()) {
                urlsList = Arrays.asList(currentLine.substring(currentLine.indexOf(":") + 1));
                System.out.println("\n empty"+urlsList);
              } else {
                urlsList = Arrays.asList(currentLine.substring(currentLine.indexOf("[") + 1, currentLine.indexOf("]")));
                //System.out.println("\n non empty"+urlsList);
              }
              
              hm.put(currentLine.substring(0, currentLine.indexOf(":")), urlsList);
              
          }
          fis.close();
          sc.close();
          //print All data in MAP
          for(Entry<String, List<String>> m :hm.entrySet()){
              System.out.println(m.getKey()+" : "+m.getValue());
          }
      }catch(Exception e){}
    }

    public static void writeIndex(String url, List<String> wordsList){
      //write to file : "fileone"
      try{
      File fileTwo=new File("res/searchIndex.txt");
      FileOutputStream fos=new FileOutputStream(fileTwo);
          PrintWriter pw=new PrintWriter(fos);

          
          for (String word : wordsList) {
            System.out.println("\nword is => " + word);
            List<String> addUrl = new ArrayList<String>();
            if (word.equals("") || word.equals(null)) {
              //System.out.println("word is null");
            } else if (hm.containsKey(word)) {
              List<String> hashMapUrls = hm.get(word);
              System.out.println("\nlist of urls related to " + word + " is =>\n" + hashMapUrls);
              for (String urlFromHashMapList : hashMapUrls ) {
                System.out.println("\n each url for " + word + " is => " + urlFromHashMapList);
                if (!addUrl.contains(urlFromHashMapList)) {
                  addUrl.add(urlFromHashMapList);
                }
                
              }
              System.out.println("\n is current url is present in the list of urls previously loded ");
              System.out.println(addUrl + "\n" + url);
              if (!addUrl.contains(url)) {
                addUrl.add(url);
              }
              
              hm.put(word, addUrl);
            } else {
              //List <String> urlList = new ArrayList <String>();
              addUrl.add(url);
              hm.put(word, addUrl);
            }
            
          }
          
          for(Entry<String, List<String>> m :hm.entrySet()){
            //System.out.println("key is " + m.getKey() + " " + m.getValue());
            
            pw.println(m.getKey()+":"+m.getValue());
          }
          

          pw.flush();
          pw.close();
          fos.close();
      }catch(Exception e){
        e.printStackTrace();
      }

    }
}
