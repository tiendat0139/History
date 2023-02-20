package crawler;

import models.Event;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;

public class EventCrawler extends Crawler{

    private static final String END_POINT = "https://thuvienlichsu.com";

    //split string contain event name and event time
    public String[] handleString(String s) {
        String[] arr = s.split("\\(");
        arr[0] = arr[0].trim();
        if (arr.length == 2) {
            arr[1] = arr[1].substring(0, arr[1].length() - 1).trim();
        }
        return arr;
    }
    @Override
    public void crawler(String url) {
        Document doc = request(url);
        List<Event> list = new ArrayList<>();
        if(doc != null) {
            Elements cardElements = doc.select(".card");
            for (Element cardElement : cardElements) {
                Element aElement = cardElement.select(".click").first();
                if (aElement != null) {
                    String tenSuKien = handleString(aElement.text())[0];
                    String thoiGian = handleString(aElement.text())[1];

                    // make request to event's detail page
                    String linkToDetail = aElement.attr("href");
                    Document detailDoc = request(END_POINT + linkToDetail);
                    String dienBien = "", tenDiaDiem = "";
                    List<String> nhanVat = new ArrayList<>();

                    if (detailDoc != null) {
                        Elements headerElements = detailDoc.select(".divide-line");
                        for (Element headerElement : headerElements) {
                            String title = headerElement.text();

                            if (title.contains("Diễn biễn")) {
                                dienBien = Objects.requireNonNull(headerElement.nextElementSibling()).text();
                            }
                            if (title.contains("Địa điểm")) {
                                tenDiaDiem = Objects.requireNonNull(Objects.requireNonNull(headerElement.nextElementSibling())
                                        .select(".card-title").first()).text();
                            }
                            if (title.contains("Nhân vật liên quan")) {
                                Elements personCards = headerElement.nextElementSiblings().select(".card");
                                for (Element personCard : personCards) {
                                    String personName = personCard.select(".click").text();
                                    nhanVat.add(handleString(personName)[0]);
                                }
                            }
                        }
                    }
                    Event<String, String> item = new Event<String, String>(tenSuKien, thoiGian,
                            tenDiaDiem, dienBien, nhanVat);
                    list.add(item);
                }
            }
            String fileName = "src/data/sukien.json";
            writeToJson(list, fileName);
        }
    }

    public static void main(String[] args) {
        String url = "https://thuvienlichsu.com/su-kien";
        EventCrawler eventCrawler = new EventCrawler();
        eventCrawler.crawler(url);
    }
}
