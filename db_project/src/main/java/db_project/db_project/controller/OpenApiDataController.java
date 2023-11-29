package db_project.db_project.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import db_project.db_project.domain.Feed;
import db_project.db_project.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

@RestController
@RequiredArgsConstructor
public class OpenApiDataController {
    private final FeedService feedService;
    @PostMapping("/getData")
    public String feedList() {
        StringBuffer result = new StringBuffer();
        try {
            String urlstr = "http://api.nongsaro.go.kr/service/feedRawMaterial/feedRawMaterialAllList?" +
                    "apiKey=20231004MDDAYVB8GN7GYJDSQN2VTQ" +
                    "&type=json";
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

            br.readLine();
            String returnLine;

            while ((returnLine = br.readLine()) != null) {
                result.append(returnLine);
            }

            String StringifyResult = result.toString();
            XmlMapper xmlMapper = new XmlMapper();
            JsonNode node = xmlMapper.readTree(StringifyResult.getBytes(StandardCharsets.UTF_8));  //인코딩 방식 utf-8로 변경

            ObjectMapper jsonMapper = new ObjectMapper();
            String json = jsonMapper.writeValueAsString(node);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode itemArrayNode = rootNode.path("body").path("items").path("item");

            JSONArray jsonArray = new JSONArray(itemArrayNode.toString());
            for (int i = 0; i < jsonArray.length(); i++) { // jsonArray.length()
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Integer mtralPc = getIntegerValue(jsonObject, "mtralPc");
                Double clciQy = getDoubleValue(jsonObject, "clciQy");
                Double naQy = getDoubleValue(jsonObject, "naQy");
                Double dryMatter = getDoubleValue(jsonObject, "dryMatter");
                Double ashsQy = getDoubleValue(jsonObject, "ashsQy");
                Integer feedSeqNo = getIntegerValue(jsonObject, "feedSeqNo");
                Double crfbQy = getDoubleValue(jsonObject, "crfbQy");
                Double totEdblfibrQy = getDoubleValue(jsonObject, "totEdblfibrQy");
                Integer feedClCode = getIntegerValue(jsonObject, "feedClCode");
                Double ptssQy = getDoubleValue(jsonObject, "ptssQy");
                Double mitrQy = getDoubleValue(jsonObject, "mitrQy");
                Double slwtEdblfibrQy = getDoubleValue(jsonObject, "slwtEdblfibrQy");
                Double liacQy = getDoubleValue(jsonObject, "liacQy");
                Integer upperFeedClCode = getIntegerValue(jsonObject, "upperFeedClCode");
                Double fatQy = getDoubleValue(jsonObject, "fatQy");
                Double lnacQy = getDoubleValue(jsonObject, "lnacQy");
                Double vtmaQy = getDoubleValue(jsonObject, "vtmaQy");
                Double trypQy = getDoubleValue(jsonObject, "trypQy");
                String feedNm = getStringValue(jsonObject, "feedNm");
                Double crbQy = getDoubleValue(jsonObject, "crbQy");
                Double phphQy = getDoubleValue(jsonObject, "phphQy");
                Double protQy = getDoubleValue(jsonObject, "protQy");
                String feedClCodeNm = getStringValue(jsonObject, "feedClCodeNm");
                Double inslbltyEdblfibrQy = getDoubleValue(jsonObject, "inslbltyEdblfibrQy");
                String originNm = getStringValue(jsonObject, "originNm");
                Feed feed = new Feed(mtralPc, clciQy, naQy, dryMatter, ashsQy, feedSeqNo, crfbQy, totEdblfibrQy, feedClCode,
                        ptssQy, mitrQy, slwtEdblfibrQy, liacQy, upperFeedClCode, fatQy, lnacQy, vtmaQy, trypQy, feedNm, crbQy,
                        feedClCodeNm, phphQy, protQy, originNm, inslbltyEdblfibrQy
                );
                feedService.saveFeed(feed);
            }
            urlconnection.disconnect();
            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


    private static Integer getIntegerValue(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        if (!value.equals("")) {
            return Integer.parseInt(String.valueOf(value));
        }
        return null;
    }

    private static Double getDoubleValue(JSONObject jsonObject, String key) {
        String value = String.valueOf(jsonObject.get(key));
        if (value.equals("")) {
            return null;
        }
        return Double.parseDouble(value);
    }

    private static String getStringValue(JSONObject jsonObject, String key) {
        Object value = jsonObject.get(key);
        if (value == null || value.toString().isEmpty()) {
            return null;
        }
        return (String) value;
    }

}