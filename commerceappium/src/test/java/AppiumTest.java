import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTest {
    @Test
    public void appiumTest1() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName("Copy_of_Pixel 6 Pro API 33-2");
        options.setApp("C:\\Users\\cheun\\Desktop\\commerceappium\\src\\test\\resources\\commerce-app.apk");
        AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }
}
