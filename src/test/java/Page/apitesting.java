package Page;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.DecimalFormat;
import General.generalFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.cucumber.messages.internal.com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectWriter;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.node.ObjectNode;
import org.openqa.selenium.json.JsonException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static com.google.common.net.HttpHeaders.USER_AGENT;


@Component("apitesty")
public class apitesting extends generalFunction {
    String responseToken ;

    public void testApiFirst () throws IOException, JsonException {


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        ObjectMapper mapper = new ObjectMapper();
        double rndVar = Math.floor(Math.random()*100000 +1000);
        DecimalFormat format = new DecimalFormat("0.#");
        format.format(rndVar);

        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("step 1 : Set up the Header Content Type ");
        headers.set(USER_AGENT, "Mozilla/5.0 Firefox/26.0");
        String file = System.getProperty("user.dir")+"/src/test/resources/apiFile.json";
        File jsonFile = new File(file);
        String content = new String(Files.readAllBytes(jsonFile.toPath()));
        ObjectNode root = (ObjectNode)mapper.readTree(content);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(jsonFile ,root);
        HttpEntity<String> request = new HttpEntity<>(root.toString(), headers);
        System.out.println("step 2 : Set up the request Body ");
        propertySet();
        String theHosty = prop.getProperty("host");
        System.out.println("step 3 : Set up the request Url ");
        ResponseEntity<String> response = restTemplate.postForEntity(theHosty,request,String.class);
        System.out.println("step 4 : Sending the request ");
        System.out.println("step 5 : Getting al response information ");

        System.out.println("- response Status :"+response.getStatusCode());
        System.out.println("- response Body   :"+response.getBody());

  //      headers.setContentType()
    }

    public void testApiSecond () throws IOException, JsonException {
        URL url = new URL(prop.getProperty("host"));
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setRequestMethod("POST");
        request.setDoOutput(true);
        request.setRequestProperty("Content-Type", "application/json");
        request.setRequestProperty("Content-Length", "0");
        System.out.println(request.getResponseCode() + " " + request.getResponseMessage());
        request.disconnect();
    }
}
