package crawler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public abstract class Crawler {

    public Document request(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                System.out.println("Connect to website successfully!");
                doc.select("*[style*=display:none]").remove();
                return doc;
            } else {
                return null;
            }
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public void writeToJson(List<?> list, String fileName) {
        try {
            Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(gson.toJson(list));
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract void crawler(String url);
}
