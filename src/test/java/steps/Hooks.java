package steps;

import api.ApiUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.ScenarioUtils;

import java.util.HashMap;

public class Hooks extends ApiUtils {

    @Before
    public void before(Scenario scenario){
        ScenarioUtils.add(scenario);
        headers = new HashMap<>();
    }

    @After
    public void after(){
        ScenarioUtils.remove();
    }
}
