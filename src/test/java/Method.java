import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class Method extends BaseTest {

    protected static FluentWait<AppiumDriver> appiumFluentWait;

    private MobileElement findElement(By by) {

        WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, 20);
        MobileElement mobileElement = (MobileElement) webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));

        return mobileElement;
    }
    public List<MobileElement> findElements(By by) throws Exception {
        List<MobileElement> mobileElementList = null;
        try {
            mobileElementList = appiumFluentWait.until(new ExpectedCondition<List<MobileElement>>() {
                @Nullable
                @Override
                public List<MobileElement> apply(@Nullable WebDriver driver) {
                    List<MobileElement> elements = driver.findElements(by);
                    return elements.size() > 0 ? elements : null;
                }
            });
            if (mobileElementList == null) {
                throw new NullPointerException(String.format("by = %s Web element list not found", by.toString()));
            }
        } catch (Exception e) {
            throw e;
        }
        return mobileElementList;

    }


    public MobileElement findElementByKey(String key, String type) {
        MobileElement mobileElement = null;
        try {
            if(type.contains("id")){
                mobileElement = findElement(By.id(key));
            }
            else if (type.contains("className")){
                mobileElement = findElement(By.className(key));
            }
            else {
                mobileElement =findElement(By.xpath(key));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElement;
    }

    public List<MobileElement> findElementsByKey(String key, String type) {
        List<MobileElement> mobileElements = null;
        try {
            if(type == "id"){
                mobileElements = findElements(By.id(key));
            }
            else if (type == "className"){
                mobileElements = findElements(By.className(key));
            }
            else {
                mobileElements =findElements(By.xpath(key));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mobileElements;
    }

    public static int randomNumber(int start, int end) {
        Random rn = new Random();
        int randomNumber = rn.nextInt(end - 1) + start;
        return randomNumber;
    }


}
