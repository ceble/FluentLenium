package org.fluentlenium.integration.initialization;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.integration.util.adapter.FluentTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class AnnotationInitialization extends FluentTest {
    public WebDriver webDriver = new HtmlUnitDriver();

    @Page
    public TestAboutBlankPage page2;

    @Page
    public TestPrivatePage page;


    @Test
    public void test_no_exception() {
        page2.go();
    }

    @Test
    public void test_no_exception_when_inner_class() {
        page2.go();
    }

    @Override
    public WebDriver newWebDriver() {
        return webDriver;
    }

}


class TestPrivatePage extends FluentPage {


    @Override
    public String getUrl() {
        return "about:blank";
    }
}
