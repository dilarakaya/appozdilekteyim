import com.thoughtworks.gauge.Step;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.util.concurrent.TimeUnit;

public class StepImplementation  extends Method{

    private static final Logger logger = LogManager.getLogger(StepImplementation.class);

    @Step("Uygulama açılışı ve kontrolü")
    public void uygulamaAc() throws InterruptedException {
        Thread.sleep(3000);
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/iv_storeTopLogo"));

    }
    @Step("<key> <type>'li element var mı")
    public boolean doesElementExistByKey(String key,String type) {

        try {
            findElementsByKey(key, type);
            return true;
        } catch (Exception e) {
            logger.info(key + " aranan elementi bulamadı");
            return false;
        }

    }
    @Step("Alışverişe başlanır")
    public void startShopping() throws InterruptedException {
        appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        appiumDriver.findElement(By.id("com.ozdilek.ozdilekteyim:id/tv_startShoppingStore")).click();
        Thread.sleep(3000);

    }

    @Step({"<seconds> saniye bekle"})
    public void waitBySecond(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }

    @Step("<key> li <type> elementi bul ve tıkla")
    public void clickByKey(String key, String type) {

        findElementByKey(key, type).click();
        logger.info(key + "elemente tıkladı");

    }

    @Step("<key> li <type> elementi bul ve <text> değerini yaz")
    public void sendKeysByKeyNotClear(String key,String type, String text) {
        findElementByKey(key,type).setValue(text);

    }

    @Step("swipe et")
    public void swipeMethod() {
        if (appiumDriver instanceof IOSDriver) {
            Dimension size = appiumDriver.manage().window().getSize();
            int x = size.getWidth() - 1;
            int starty = (int) (size.getHeight() * 0.20);
            int endy = (int) (size.getHeight() * 0.80);

            new TouchAction(appiumDriver).longPress(PointOption.point(x, starty))
                    .moveTo(PointOption.point(x, endy))
                    .release().perform();
        } else {
            new TouchAction(appiumDriver).longPress(PointOption.point(2, 800))
                    .moveTo(PointOption.point(2, 568))
                    .release().perform();
        }

        System.out.println("swipe yapıldı");

        }


    @Step("Rastgele <key> <type> seç")
    public void randomUrun(String key, String type) {

        findElementByKey(key, type).click();
    }
    }

