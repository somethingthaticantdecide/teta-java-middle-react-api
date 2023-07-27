package mts.teta.java_middle_back;

import mts.teta.java_middle_back.model.UserInfo;
import mts.teta.java_middle_back.services.EnrichmentService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
class JavaMiddleBackApplicationTests {
    @Autowired
    EnrichmentService enrichmentService;
    @Autowired
    private Map<String, UserInfo> userMap;

    @Test
    void msdnFoundTest() throws JSONException {
        String body = "{ \"action\": \"button_click\", \"page\": \"book_card\", \"msisdn\": \"88005553535\" }";
        String enriched = "{ \"action\": \"button_click\", \"page\": \"book_card\", \"msisdn\": \"88005553535\", \"enrichment\": { \"firstName\": \"Vasya\", \"lastName\": \"Ivanov\" } }";

        userMap.put("88005553535", new UserInfo("Vasya", "Ivanov"));

        String actual = enrichmentService.enrich(body);
        JSONAssert.assertEquals(enriched, actual, JSONCompareMode.STRICT);
    }

    @Test
    void msdnNotFoundTest() throws JSONException {
        String body = "{ \"action\": \"button_click\", \"page\": \"book_card\", \"msisdn\": \"88005553534\" }";

        String actual = enrichmentService.enrich(body);
        JSONAssert.assertEquals(body, actual, JSONCompareMode.STRICT);
    }


    @Test
    void enrichedFoundAndOverwrittenTest() throws JSONException {
        String body = "{ \"action\": \"button_click\", \"page\": \"book_card\", \"msisdn\": \"88005553535\", \"enrichment\": { \"firstName\": \"Inokentiy\", \"lastName\": \"Pupkin\" } }";
        String enriched = "{ \"action\": \"button_click\", \"page\": \"book_card\", \"msisdn\": \"88005553535\", \"enrichment\": { \"firstName\": \"Vasya\", \"lastName\": \"Ivanov\" } }";

        userMap.put("88005553535", new UserInfo("Vasya", "Ivanov"));

        String actual = enrichmentService.enrich(body);
        JSONAssert.assertEquals(enriched, actual, JSONCompareMode.STRICT);
    }

}
