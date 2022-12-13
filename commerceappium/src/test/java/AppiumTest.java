import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    @Test
    public void appiumTest1() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Pixel 6 Pro API 33-copy3");
        options.setApp("C:\\Users\\cheun\\Desktop\\ecommerceandroidapp\\commerceappium\\src\\test\\resources\\commerce-app.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        androidDriver.findElement(By.id("com.example.ecommerceapp:id/loginEmail")).click();
        androidDriver.findElement(By.id("com.example.ecommerceapp:id/loginEmail")).sendKeys("janesmith24@email.com");
        androidDriver.findElement(By.id("com.example.ecommerceapp:id/loginPassword")).click();
        androidDriver.findElement(By.id("com.example.ecommerceapp:id/loginPassword")).sendKeys("janesmith24@email.com");
        androidDriver.findElement(By.id("com.example.ecommerceapp:id/loginButton")).click();
//        androidDriver.findElement(By.xpath("//android.widget.LinearLayout[@content-desc=\"Items\"]")).click();
//        androidDriver.quit();
    }
}
/*
{
  "app": "C:\\Users\\cheun\\Desktop\\ecommerceandroidapp\\commerceappium\\src\\test\\resources\\commerce-app.apk",
  "deviceName": "Pixel 6 Pro API 33-copy3",
  "platformName": "android",
  "automationName": "UIAutomator2"
}
 */
