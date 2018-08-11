package com.nir.selenium;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public class ScreenshotTaker extends AbstractTestExecutionListener {
    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        TakesScreenshot takesScreenshot = (TakesScreenshot)testContext.getApplicationContext()
                .getBean(WebDriverConfiguration.class);
        File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File file = new File("target",
                testContext.getTestClass().getName() + "_" + testContext.getTestMethod().getName() + ".png");
        FileUtils.deleteQuietly(file);
        FileUtils.moveFile(screenshot, file);
        System.err.println("saved screenshot as " + file);
    }
}