package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SiteCrawler extends Crawler {

    private static final String END_POINT = "https://vi.wikipedia.org/";

    @Override
    public void crawler(String url){
        Document doc = request(url);
        if(doc != null){
            //get all row in all table
            Elements rows = doc.select(".wikitable tr");
            List<Map<String, String>> siteList = new ArrayList<>();
            for (Element row : rows) {
                String name = "", location = "", recognitionYear = "";
                HashMap<String, String> siteItem =  new HashMap<>();
                if(row.children().size() == 5 &&
                        row.child(2).text().contains("Lịch sử")) {
                    name = row.child(0).text();
                    location = row.child(1).text();
                    recognitionYear = row.child(3).text();
                    siteItem.put("tenDiTich", name);
                    siteItem.put("diaDiem", location);
                    siteItem.put("ngayCongNhan", recognitionYear);
                    siteList.add(siteItem);
                }
            }
            String siteFileName = "src/data/ditich.json";
            writeToJson(siteList, siteFileName);
        }
    }

    public static void main(String[] args) {
        String url = "https://vi.wikipedia.org/wiki/Danh_s%C3%A1ch_Di_t%C3%ADch_qu%E1%BB%91c_gia_Vi%E1%BB%87t_Nam";
        SiteCrawler siteCrawler = new SiteCrawler();
        siteCrawler.crawler(url);
    }
}
