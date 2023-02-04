package crawler;

import models.Festival;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FesCrawler extends Crawler {

    @Override
    public void crawler(String url) {
        Document doc = request(url);
        List<Festival> list = new ArrayList<>();

        Element tableElement = doc.select(".prettytable.wikitable").first();
        if (tableElement != null) {
            Elements rows = tableElement.getElementsByTag("tr");
            for (Element row : rows) {
                String date = row.child(0).text();
                String location = row.child(1).text();
                String name = row.child(2).text();
                String firstDate = row.child(3).text();
                String[] persons = null;
                if (!row.child(4).text().equals("")) {
                    persons = row.child(4).text().split(",");
                    for (int i = 0; i < persons.length; i++) {
                        persons[i] = persons[i].trim();
                    }
                }

                Festival item = new Festival(name, date, firstDate, location, Arrays.asList(persons));
                list.add(item);
            }
            String fileName = "src/data/lehoi.json";
            writeToJson(list, fileName);
        }
    }

    public static void main(String[] args) {
        String url = "https://vi.wikipedia.org/wiki/L%E1%BB%85_h%E1%BB%99i_Vi%E1%BB%87t_Nam?" +
                "fbclid=IwAR3S7RKGSFxqpc5oMTautJGeg-KHS3vTA-ZgzrOyWBd03joj484JiWpUxow";
        FesCrawler fesCrawler = new FesCrawler();
        fesCrawler.crawler(url);
    }
}
