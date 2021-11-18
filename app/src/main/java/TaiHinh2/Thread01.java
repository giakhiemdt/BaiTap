package TaiHinh2;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Thread01 extends Thread {
    String link = "";
    String name = "";
    String mangaName = "";

    public Thread01(String name, String link, String manga) {
        this.name = name;
        this.link = link;
        mangaName = manga;
    }

    @Override
    public void run() {

        try {
            URL url = new URL(link);
            InputStream in = url.openStream();
            OutputStream out;
            out = new BufferedOutputStream(new FileOutputStream("Manga\\" + mangaName + "\\" + name));
            for (int b; (b = in.read()) != -1;) {
                out.write(b);
            }
            out.close();
            in.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
