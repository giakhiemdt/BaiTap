package TaiHinh2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;

public class ThreadChap extends Thread {

    String nameChap = "";
    String linkChap = "";
    String mangaName = "";

    public ThreadChap(String name, String link, String manga) {
        nameChap = name;
        linkChap = link;
        mangaName = manga;
    }

    @Override
    public void run() {

        try {
            org.jsoup.nodes.Document docChap = Jsoup.connect(linkChap).timeout(20000).get();
            List<String> img = new ArrayList<>();
            org.jsoup.select.Elements im = docChap.getElementsByTag("img");
            for (int i = 0; i < im.size(); i++) {
                String url = im.get(i).absUrl("src");
                if (url.equals("")) {
                    continue;
                }
                if (url.contains("blazefast")) {
                    img.add(url);
                }
            }

            taiAnh(img);
        } catch (IOException | InterruptedException e) {

        }

    }

    public void taiAnh(List<String> img) throws IOException, InterruptedException {

        ThreadPoolExecutor e = (ThreadPoolExecutor) (Executors.newFixedThreadPool(5));
        for (int a = 0; a < img.size(); a++) {
            String name = nameChap + "_" + (a + 1) + ".jpg";
            String link = img.get(a);
            Thread01 t1 = new Thread01(name, link, mangaName);
            e.submit(t1);
        }

        e.shutdown();
        e.awaitTermination(1, TimeUnit.MINUTES);
    }
}
