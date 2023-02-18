import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Crawler {
    public static final String CNN = "https://mhanational.org/issues/lgbtq-communities-and-mental-health";

//recursive function
    public static void main(String[] args) {
        String url = CNN;
        crawl(1, url, new ArrayList<String>());

    }

    private static void crawl (int level, String url, ArrayList<String> visited) {
        if(level <=1 ) {
            Document doc = request(url, visited);
            if (doc!= null) {
                for (Element link : doc.select("a[href]")) {
                    String next_link = link.absUrl("href");
                    if(visited.contains(next_link) == false) {
                        crawl(level++, next_link, visited);
                    }
                }
            }
        }
    }
    private static Document request(String url, ArrayList<String> v) {
        try {
            // fetch the HTML code of the given URL by using the connect() and get() method and store the result in Document
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if(con.response().statusCode() == 200) {
                System.out.println("Link: " + url);
                System.out.println(doc.title());
                v.add(url);// appends the visited urls
                return doc;
            }
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}