package crawler;

import models.Period;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class PeriodCrawler extends Crawler {

    // split name of period and time
    public List<String> handleString(String s) {
        String name = "", time = "";
        String[] arrOfStr = s.split("\\(");
        if (arrOfStr.length == 2) {
            name = arrOfStr[0].trim();
            time = arrOfStr[1].trim();
            time = time.substring(0, time.length() - 1);
            List<String> strAfter = new ArrayList<>();
            strAfter.add(name);
            strAfter.add(time);
            return strAfter;
        }
        return  null;
    }

    @Override
    public void crawler(String url) {
        Document doc = request(url);
        List<Period> list = new ArrayList<>();
        if (doc != null) {
            Elements titleElements = doc.select("b a");
            for (Element titleElement : titleElements) {
                String title = titleElement.text();
                List<String> titleAfterHandle = handleString(title);

                if(titleAfterHandle != null) {
                    Period item = new Period(titleAfterHandle.get(0), titleAfterHandle.get(1));
                    list.add(item);
                }
            }
            String fileName = "src/data/thoiki.json";
            writeToJson(list, fileName);
        }
    }

    public static void main(String[] args) {
        String url = "https://vansu.vn/viet-nam/nien-bieu-lich-su?fbclid=" +
                "IwAR2YHpT77thFaeAsT-EYjn2YS5XqyWaAXmypGnfXPauQOsY0nl2UwiN6-J0";
        PeriodCrawler periodCrawler = new PeriodCrawler();
        periodCrawler.crawler(url);
    }
}
