package StepsDefinition;

import General.generalFunction;

import io.cucumber.java.Before;
import io.cucumber.java.After;


public class Hooks extends generalFunction{
    @Before
    public void Initialisation(){
        propertySet();
        System.out.println("step 0 :set the internal property ");
    }
//
//
//    @After
//    public void TearDown(){
//        if(driver != null){
//            removeDriver();
//            System.out.println("Closing the browser ");
//        }
//
//    }

}
