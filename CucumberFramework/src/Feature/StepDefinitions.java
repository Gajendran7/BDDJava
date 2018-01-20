package Feature;

import java.util.List;
import java.io.*;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinitions {
	
//	@Test	
//	public void setup() throws Exception{
//		System.setProperty("webdriver.chrome.driver","\\Users\\Gajendran\\Downloads\\chromedriver.exe");
//	}
	

	WebDriver driver = null;
	String URL = null;
	int CartCount = 0;
	
	@Given("^I had the takealot web address$")
	public void takealot_web_address() throws Throwable{
		System.setProperty("webdriver.chrome.driver","//Users//Gajendran//Downloads//chromedriver");
		driver = new ChromeDriver();
//		driver = new FirefoxDriver();
		URL = "http://www.takealot.com";
	}
	
	@Given("^I open the takealot website$")
	public void open_takealot_site() throws Throwable{
		driver.get(URL);
	}
	
	@Given("^the initial cart having \"([^\"]*)\" items$")
	public void initial_cart_value(String arg1) throws Throwable {
		String cartCount = driver.findElement(By.className("crt-cnt")).getText();
		System.out.println("The initial count in the cart is "+ cartCount);
		CartCount = Integer.parseInt(cartCount);
		Assert.assertTrue(driver.findElement(By.className("crt-cnt")).getText().equals(arg1));
	}
	
	@When("^I search for \"([^\"]*)\"$")
	public void searchItem(String item) {
		driver.findElement(By.id("search")).sendKeys(item);
		driver.findElement(By.className("submit")).click();
	}
	
//	@Then("^the list of \"([^\"]*)\"s are displayed$")
	@Then("^the list of \"([^\"]*)\"s are displayed$")
	
	public void items_list(String itemDetails){
//		Assert.assertTrue(driver.findElement(By.className("product-list group")).isDisplayed());
		List products = driver.findElements(By.className("result-item"));
		for(int i=0; i < products.size();i++){
			String prod = ((WebElement) products.get(i)).getText();
			Pattern.compile(Pattern.quote(itemDetails), Pattern.CASE_INSENSITIVE).matcher(prod).find();
//			Assert.assertEquals(true, prod.contains(itemDetails));
		}		
	}
	
	@When("^I select the model \"([^\"]*)\"$")
	public void selectItem(String model){
		List products = driver.findElements(By.className("result-item"));
		for(int i=0; i < products.size();i++){
			String prod = ((WebElement) products.get(i)).getText();
			if(prod.contains(model)){
				System.out.println(model+"............");
//				((WebElement) products.get(i)).click();
				((WebElement) products.get(i)).findElement(By.id("pos_link_0")).click();
				break;
			}
		}	
	}
	
	@Then("^the \"([^\"]*)\" of the model \"([^\"]*)\" is displayed$")
	public void itemDisplayed(String item, String itemModel){
		String SelectedProd = driver.findElement(By.cssSelector(".product-data .group h1.fn")).getText();
	    String SelectedProdModel = driver.findElement(By.cssSelector(".product-data .group h2.sub-heading")).getText();
	    Assert.assertEquals((SelectedProd.contains(item) && SelectedProdModel.contains(itemModel)), true);
	}
	
	@Then("^I click on \"([^\"]*)\" button$")
	public void clickAddcart(String addCartbtn){
	    driver.findElement(By.cssSelector(".btn-atc")).click();
	}
	
	@Then("^the item is added to the cart$")
	public void itemAdded_to_cart(){
	    String Msg = driver.findElement(By.cssSelector("ul#message-bar .inner-inner")).getText();
	    System.out.println(Msg);
	    Assert.assertEquals(Msg, "Item added to your cart");
	    String AddedCount = driver.findElement(By.cssSelector("div#header .last .crt-cnt")).getText();
	    System.out.println(AddedCount + "\n"+CartCount);
	    Assert.assertTrue(Integer.parseInt(AddedCount) == (CartCount + 1));
	}


	@Then("^I close the browser$")
	public void closeBrowser(){
		driver.close();
	}
	
	@Given("^I open the \"([^\"]*)\" website$")
	public void OpenBrowser(String webAdd){
		System.setProperty("webdriver.chrome.driver","//Users//Gajendran//Downloads//chromedriver");
		driver = new ChromeDriver();
		driver.get(webAdd);
	}

}
