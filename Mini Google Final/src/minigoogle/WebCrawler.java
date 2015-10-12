package minigoogle;

import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {
  
  static ArrayList<String> removeDuplicates(List<String> links) {

    // Store unique items in result.
    ArrayList<String> result = new ArrayList<>();

    // Record encountered Strings in HashSet.
    HashSet<String> set = new HashSet<>();

    // Loop over argument list.
    for (String item : links) {

        // If String is not in set, add it to the list and the set.
        if (!set.contains(item)) {
      result.add(item);
      set.add(item);
        }
    }
    return result;
  }
  
public static void main(String[] args) throws Exception {
  SearchIndex.readIndex();
    List<String> links = new CopyOnWriteArrayList<String>();
    //System.out.println("here we go with a live website");
    links.add("https://learnjavathehardway.org/book/");
    //links.add("http://localhost:9090/Home.html");
    int iteration = 0;
    for (int index = iteration; index < links.size(); index++) {
      System.out.println("working with : " + links.get(index));
      //remove first conditioin in if statement; it is used as a dummy to avoid loop into real web.
      if (!links.get(index).equals("https://learnjavathehardway.org/book/ex00.html") && !links.equals(links.get(index)) || iteration == 0) {
        Elements fromEachPage = WebPage.getAllLinks(links.get(index));
        if (fromEachPage != null) {
          for (Element link : fromEachPage) {
            //String linkHref = link.attr("href");//gives only relative url
            String linkHref = link.attr("abs:href");//gives absolute url
            //System.out.println("working with : " + linkHref);
            //String linkText = link.text();
            //System.out.println(linkHref + " -> " + linkText);
            links.add(linkHref);
            links = removeDuplicates(links);
          }
        }
        
        iteration++;
      }
      
    }
    URL url;
    
    for (String link : links) {
      url = new URL(link);
      SearchIndex.writeIndex(link, WebCrawler.Crawl(url));
      //break;
    }
    
    System.out.println("\n Completed creating search index...");
  }
  
  public static List<String> Crawl(URL url) throws Exception {
   BufferedReader content = WebPage.getContent(url.toString()); 
   List<String> words = new ArrayList<String>();
   List<String> wordsListed = new ArrayList<String>();
   words = WebPage.getWords(content);
   /*for (String string : words) {
    System.out.println(string);
   }
   System.out.println();*/
   long frequency = 0;
   for (String keyword : words) {
     frequency = WebPage.getKeywordFrequency(words, keyword);
     if (frequency > 0) {
       wordsListed.add(keyword);
     }
   }
   return wordsListed;
  }
}
