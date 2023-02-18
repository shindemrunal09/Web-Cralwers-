package mtWebCrawler;

import WebCrawler.WebCrawler;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        ArrayList<WebCrawler>bots= new ArrayList<>();
        bots.add(new WebCrawler("https://www.wikipedia.org/",1));
        bots.add(new WebCrawler("https://www.flowerglossary.com/white-flowers/",2));
        bots.add(new WebCrawler("https://traveltriangle.com/blog/bullet-train-in-india/",3));
        for(WebCrawler w: bots){
            try{
                w.getThread().join();
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}

