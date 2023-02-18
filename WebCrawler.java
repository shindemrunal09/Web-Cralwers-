package WebCrawler;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
//can run different threads
public class WebCrawler implements Runnable {
    private Thread thread;
    private String first_link;
    private ArrayList<String> visitedLinks= new ArrayList<String>();
    private int ID;
    public WebCrawler (String link, int num) {
        System.out.print("Webcrawler created");
        first_link= link;
        ID= num;
        thread= new Thread(this);
        thread.start();
    }
    public void run(){
        crawl(1,first_link);
        //recursive function
     }
     private void crawl(int level, String url) {
         if (level <= 1) {
             Document doc = request(url);
             if (doc != null) {
                 for (Element link : doc.select("a[href]")) {
                     String next_link = link.absUrl("href");
                         if (visitedLinks.contains(next_link) == false) {
                             crawl(level++, next_link);

                     }
                 }
             }
         }
     }
     private Document request(String url) {
         try {
             Connection con = Jsoup.connect(url);
             Document doc = (Document) con.get();
             if (con.response().statusCode() == 200) {
                 System.out.println("\n**Bot ID:" + ID + " Received Webpage at " + url);
                 String title = ((org.jsoup.nodes.Document) doc).title();
                 System.out.println(title);
                 visitedLinks.add(url);
                 return doc;
             }
             return null;
         } catch (IOException e) {
             return null;
         }
     }
     public Thread getThread(){
        return thread;
     }
}
