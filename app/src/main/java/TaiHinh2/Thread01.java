package TaiHinh2;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Thread01 extends Thread {
    String link = "";
    String name = "";

    public Thread01(String name, String link) {
        this.name = name;
        this.link = link;
    }

    @Override
    public void run() {

        try {
            URL url = new URL(link);
            InputStream in = url.openStream();
            OutputStream out;
            out = new BufferedOutputStream(new FileOutputStream("C:\\Users\\khiem\\Desktop\\Img" + "\\" + name));
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
