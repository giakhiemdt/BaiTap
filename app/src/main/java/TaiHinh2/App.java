/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package TaiHinh2;

// public class App {
//     public String getGreeting() {
//         return "Hello World!";
//     }

//     public static void main(String[] args) {
//         System.out.println(new App().getGreeting());
//     }
// }

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * NHT
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {

        List<String> chap = new ArrayList<>();

        Document doc = Jsoup.connect("https://kissmanga.org/manga/manga-ee981361").timeout(20000).get();

        Elements chaps = doc.getElementsByTag("h3");

        for (int a = 0; a < chaps.size(); a++) {
            Elements lc = chaps.get(a).getElementsByTag("a");
            for (int b = 0; b < lc.size(); b++) {
                String linkChap = lc.first().absUrl("href");
                if (linkChap.contains("chapter")) {
                    chap.add(linkChap);
                }
            }
        }
        linkAnh(chap);
        // PrintWriter pw = new PrintWriter(new File("output.txt"));
        // pw.println(chap);
        // pw.println(chaps.size());
        // pw.close();

    }

    public static void linkAnh(List<String> chap) throws IOException, InterruptedException {
        ThreadPoolExecutor e1 = (ThreadPoolExecutor) (Executors.newFixedThreadPool(5));

        for (int a = 0; a < chap.size(); a++) {
            String chapName = "Chap_" + (a + 1);
            String chapLink = chap.get(chap.size() - a - 1);
            ThreadChap thrchap = new ThreadChap(chapName, chapLink);
            e1.submit(thrchap);
        }

        e1.shutdown();
        e1.awaitTermination(1, TimeUnit.MINUTES);
    }
}
