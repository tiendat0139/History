package crawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonCrawler extends Crawler {

    private static final String END_POINT = "https://nguoikesu.com";

    private static final int NUMBER_OF_PAGES = 291;

    private ArrayList<String> listAttr = new ArrayList<>();

    public void setListAttr(ArrayList<String> listAttr) {
        this.listAttr = listAttr;
    }

    public String convertAttrName(String name) {
        return switch (name) {
            case "Tên" -> "ten";
            case "Tên thật" -> "tenThat";
            case "Tên húy" -> "tenHuy";
            case "Tên đầy đủ" -> "tenDayDu";
            case "Niên hiệu" -> "nienHieu";
            case "Miếu hiệu" -> "mieuHieu";
            case "Triều đại" -> "trieuDai";
            case "Sinh" -> "sinh";
            case "Mất" -> "mat";
            case "An táng" -> "anTang";
            case "Tiền nhiệm" -> "tienNhiem";
            case "Kế nhiệm" -> "keNhiem";
            case "Tại vị" -> "taiVi";
            case "Thân phụ" -> "thanPhu";
            case "Thân mẫu" -> "thanMau";
            default -> "";
        };
    }

    @Override
    public void crawler(String url) {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i <= NUMBER_OF_PAGES; i++){
            String pageUrl = url + "?start=" + i*5; //pagination
            Document doc = request(pageUrl);
            if(doc != null) {
                Elements headerElements = doc.select(".page-header h2 a");
                for (Element headerElement : headerElements) {
                    String href = END_POINT + headerElement.attr("href");
                    Document doc2 = request(href); //detail info website
                    if (doc2 != null){
                        Element infoTable = doc2.getElementsByClass("infobox").first();
                        if (infoTable != null) {
                            HashMap<String, String> info = new HashMap<>();
                            Elements rowElements = infoTable.getElementsByTag("tr");
                            String name = rowElements.get(0).text(); // get name of person
                            info.put("ten", name);
                            for (Element rowElement : rowElements) {
                                Element tdElement = rowElement.getElementsByTag("td").first();
                                Element thElement = rowElement.getElementsByTag("th").first();
                                if(tdElement != null && thElement != null) {
                                    // If the attribute exists in the array of defined attributes
                                    if(listAttr.contains(thElement.text())){
                                        String key = convertAttrName(thElement.text());
                                        String value = tdElement.text();
                                        info.put(key, value);
                                    }
                                }
                            }
                            list.add(info);
                        }
                    }
                }
            }
        }
        String fileName = "src/data/nhanvat.json";
        writeToJson(list, fileName);
    }

    public static void main(String[] args) {
        String url = "https://nguoikesu.com/nhan-vat";
        ArrayList<String> listAttr = new ArrayList<>();

        listAttr.add("Tên");
        listAttr.add("Tên thật");
        listAttr.add("Tên húy");
        listAttr.add("Tên đầy đủ");
        listAttr.add("Niên hiệu");
        listAttr.add("Miếu hiệu");
        listAttr.add("Triều đại");
        listAttr.add("Sinh");
        listAttr.add("Mất");
        listAttr.add("An táng");
        listAttr.add("Tiền nhiệm");
        listAttr.add("Kế nhiệm");
        listAttr.add("Tại vị");
        listAttr.add("Thân phụ");
        listAttr.add("Thân mẫu");

        PersonCrawler personCrawler = new PersonCrawler();
        personCrawler.setListAttr(listAttr);
        personCrawler.crawler(url);
    }

}
