package com.epam.framework.core.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.epam.framework.core.utils.FileUtils.getResourcePathAsImputStream;


@CucumberOptions(
        features = "src/main/java/com/epam/framework/gist/ebay/tests",
        glue = "com.epam.framework.gist.ebay.tests"
)
public class RunnerTestNG extends AbstractTestNGCucumberTests{

public static final InputStream RESOURCE_NAME = getResourcePathAsImputStream("Suite.xml");

    public static void main(String[] args) {

        TestNG testNG = new TestNG();
        try {
            List<XmlSuite> suite = (List<XmlSuite>) (new Parser(RESOURCE_NAME)).parse();
            testNG.setXmlSuites(suite);
            testNG.run();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                RESOURCE_NAME.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.exit(0);
    }
}