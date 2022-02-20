package StepsDefinition;

import General.generalFunction;
import Page.apitesting;
import Runner.TestRunner;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;



import java.io.IOException;
@ContextConfiguration(classes = TestRunner.class, loader = SpringBootContextLoader.class)
public class MyStepdefs extends generalFunction {
    
    @Autowired
    private apitesting apiTesty;

    @Given("check the API response")
    public void checkTheAPIRespence() throws IOException {
        apiTesty = new apitesting();
        apiTesty.testApiFirst();
    }


}
