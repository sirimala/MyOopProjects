package minigoogle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchIndex_old {
  static Map<String, List<String>> map = new HashMap<String, List<String>>();
  public static void readIndex() throws IOException {
    
    BufferedReader in = new BufferedReader(new FileReader("res/searchIndex.txt"));
    
    String line;
    
    while ((line = in.readLine()) != null) {
        String parts[] = line.split("\t");
       //System.out.println(parts[0] + " -> " + parts[1]);
       map.put(parts[0], Arrays.asList(parts[1]));
    }
    in.close();
    

  }
  
  public static void writeIndex(String url, List<String> wordsList) throws IOException {
    FileWriter out = new FileWriter("res/searchIndex.txt", true);
    for (String string : wordsList) {
      /*//map.put(string, url);
      in.write(string);
      in.write("\t");
      in.write(url);
      in.write("\n");*/
      
      if (!map.containsKey(string)) {
        //System.out.println(url + "not there -> " + string);
        map.put(string, Arrays.asList(url));
        
      } else {
        List<String> listUrls = map.get(string);
        listUrls.add(url);
        map.put(string, listUrls);
      }
      out.write(string);
      out.write("\t");
      out.write(url);
      out.write("\n");
      readIndex();
      
    }
    out.close();
  }
}
