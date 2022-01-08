package stepdefs;

import io.cucumber.java.en.*;
import login.LoginWithFacebook;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import user_interface.n11BasePageElements;
import tasks.NavigateTo;

import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class n11LoginStepDef {

    Actor actor = Actor.named("emre");

    @Managed
    WebDriver webDriver;

    @Given("user launch browser and open the n11 main page")
    public void user_launch_browser_and_open_the_n11_main_page() {
        actor.can(BrowseTheWeb.with(webDriver));
        webDriver.manage().window().maximize();
        actor.wasAbleTo(NavigateTo.theN11HomePage());
    }

    @When("user login n11 with facebook valid credentials")
    public void user_login_n11_with_facebook_valid_credentials() {
        //cerkemamle@vusra.com not verified mail
        actor.attemptsTo(LoginWithFacebook.loginThePage("emre@test.com", "test123"));
        System.out.println("++test++");
    }

    @Then("user verify the logged in successfully")
    public void user_verify_the_logged_in_successfully() throws InterruptedException {
        actor.should(
                GivenWhenThen.seeThat(the(n11BasePageElements.USERNAME_LINK)
                        , containsText("TestAutomation"))
        );
        Thread.sleep(10000);
    }
}
