package minigoogle;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author SREENATH SIRIMALA
 * This webPage class holds the web page content
 *
 */
public class WebPage {

  /**
   * This method will return array list of links in the web page  
   * @return
   * @throws IOException 
   */
  public static Elements getAllLinks(String link) throws IOException {
    /*String url = "C:\\Users\\SREENATH SIRIMALA\\workspace\\Mini Google\\src\\minigoogle\\mysite\\" + link;
    File input = new File(url);
    
    //URL url = new URL(url1);
    Document doc = Jsoup.parse(input, "UTF-8");
    Element content = doc.tagName("html");
    Elements links = content.getElementsByTag("a");
    return links;*/
    
    
    /*Document doc = Jsoup.connect(link)
        .data("query", "Java")
        .userAgent("Mozilla")
        .cookie("auth", "token")
        .timeout(5000)
        .post();*/
/*    Document doc = Jsoup.connect(link)
        .header("Accept-Language", "en")
        .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
        .ignoreHttpErrors(true)
        .ignoreContentType(true)
        .timeout(100)*/
        //.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9"+ ",*/*;q=0.8")
        //.get();
    Elements links = null;
    Document doc = isException(link);
    if (doc != null) {
      Element content = doc.tagName("html");
      
      links = content.getElementsByTag("a");
    }
    
    return links;
  }
  

  /**
   * This method will return array list of words in the page , after removing the stopwords. 
   * @return
   */
  public static ArrayList<String> getWords(BufferedReader buffer) {
    List<String> stopwords = new ArrayList<String>();
    List<String> words = null;
    FileReader fileReader;
    BufferedReader bufferedReader;
    try {
      fileReader = new FileReader("res/stopwords.txt");
      bufferedReader = new BufferedReader(fileReader);
      String line1;
      while ((line1 = bufferedReader.readLine()) != null) {
        String[] stopWordList = line1.toString().split("\\s+");
        
        for (String string : stopWordList) {
          stopwords.add(string.trim().toLowerCase());
        }
      }
      
            
      words = new ArrayList<String>();
      String line;
     
      while ( buffer != null && (line = buffer.readLine()) != null) {
        String[] wordsList=line.toString().split("\\s+");
        
        for(String wordsTrim:wordsList) {
          String alphaString = wordsTrim.trim().toLowerCase();
          if (alphaString.chars().allMatch(x -> Character.isLetter(x))) {
            //System.out.println("\n x -> " + alphaString);
            words.add(alphaString);
          }
          
        //words.addAll(Arrays.asList());
        }
        
      }
      words.removeAll(stopwords);
      
      words = WebCrawler.removeDuplicates(words);
      
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    return (ArrayList<String>) words;
  }
  
  public static Document isException(String url) {
    Document doc = null;
    try {
      doc = Jsoup.connect(url)
          .header("Accept-Language", "en")
          .header("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
          .ignoreHttpErrors(true)
          .ignoreContentType(true)
          .timeout(5000)
          .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9"+ ",*/*;q=0.8")
          .get();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      //e.printStackTrace();
    }
    return doc;
  }
  
  /**
   * Take url parameter fetch the data from that link
   * 
   * @param url
   * @return 
   */
  public static BufferedReader getContent(String url) {
    BufferedReader in = null;
    Document doc = isException(url);
    if (doc != null) {
      Element content = doc.tagName("html");
      String text = content.text();
      InputStream is = new ByteArrayInputStream(text.getBytes());
      //System.out.println(is + "\n");
      in = new BufferedReader(new InputStreamReader(is));
      //System.out.println(in + "\n");
    }
    
    return in;
  }
  
  public static long getKeywordFrequency(List<String> words, String keyword) {
    
    return Collections.frequency(words, keyword);
   
    //return frequency;
  }
}
