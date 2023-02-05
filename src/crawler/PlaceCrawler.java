package crawler;

import models.Place;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlaceCrawler extends Crawler{

    private static final String END_POINT = "https://thuvienlichsu.com";

    @Override
    public void crawler(String url) {
        Document doc = request(url);
        if (doc != null) {
            Elements cardElements = doc.select(".card");
            List<Place> list = new ArrayList<>();
            for (Element cardElement : cardElements) {
                Element aElement = cardElement.select(".click").first();
                if (aElement != null) {
                    String tenDiaDiem = aElement.text();

                    // make request to event's detail page
                    String linkToDetail = aElement.attr("href");
                    Document detailDoc = request(END_POINT + linkToDetail);
                    if (detailDoc != null) {
                        String dienBien = "";
                        List<String> suKien = new ArrayList<>();
                        Elements headerElements = detailDoc.select(".divide-line");
                        for (Element headerElement : headerElements) {
                            String title = headerElement.text();
                            if (title.contains("Diễn biễn")) {
                                dienBien = Objects.requireNonNull(headerElement.nextElementSibling()).ownText();
                            }
                            if (title.contains("Sự kiện")) {
                                Elements eventCards = headerElement.nextElementSiblings().select(".card");
                                for (Element eventCard : eventCards) {
                                    String eventName = eventCard.select(".card-title").text();
                                    suKien.add(eventName);
                                }
                            }
                        }
                        Place item = new Place(tenDiaDiem, dienBien, suKien);
                        list.add(item);
                    }
                }
            }
            String fileName = "src/data/diadanh.json";
            writeToJson(list, fileName);
        }
    }

    public static void main(String[] args) {
        String url = "https://thuvienlichsu.com/dia-diem";
        PlaceCrawler placeCrawler = new PlaceCrawler();
        placeCrawler.crawler(url);
    }
}
