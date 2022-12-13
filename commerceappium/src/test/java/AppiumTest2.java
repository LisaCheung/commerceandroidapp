//import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AppiumTest2 {

    private AndroidDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("appium:app", "C:\\Users\\cheun\\Desktop\\ecommerceandroidapp\\commerceappium\\src\\test\\resources\\commerce-app.apk");
        desiredCapabilities.setCapability("appium:deviceName", "Pixel XL API 33");
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("appium:automationName", "UIAutomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 1000000);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void test1() {
//        driver.findElement(By.id("com.example.ecommerceapp:id/signuplink")).click();
//        driver.startActivity(new Activity("com.example.ecommerceapp", ".SignupActivity"));
//        driver.findElement(By.id("com.example.ecommerceapp:id/signupEmail")).click();
        driver.findElement(By.id("com.example.ecommerceapp:id/loginEmail")).click();
        driver.findElement(By.id("com.example.ecommerceapp:id/loginEmail")).sendKeys("janesmith24@email.com");
        driver.findElement(By.id("com.example.ecommerceapp:id/loginPassword")).click();
        driver.findElement(By.id("com.example.ecommerceapp:id/loginPassword")).sendKeys("janesmith24@email.com");
        driver.findElement(By.id("com.example.ecommerceapp:id/loginButton")).click();
        driver.startActivity(new Activity("com.example.ecommerceapp", ".MainActivity"));
        driver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Items\"]"));
        driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]")).click();
        driver.findElement(By.id("com.example.ecommerceapp:id/my_items")).click();
//        driver.startActivity(new Activity("com.example.ecommerceapp", ".UserProfileActivity"));

    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
