package org.fluentlenium.integration;

import org.fluentlenium.core.FluentControl;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.fluentlenium.integration.localtest.IntegrationFluentTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
public class PageInPageTest extends IntegrationFluentTest {

    @Page
    private TestPage testPage;

    @Page
    private SubSubTestPage subTestPage;

    @Test
    public void pages_should_be_injected() {
        assertThat(testPage).isNotNull();
        assertThat(testPage).isInstanceOf(TestPage.class);
        assertThat(testPage.includedPage).isNotNull();
        assertThat(testPage.includedPage).isInstanceOf(IncludedPage.class);
        assertThat(testPage.includedPage.element).isNotNull();
        assertThat(subTestPage).isNotNull();
        assertThat(subTestPage).isInstanceOf(SubTestPage.class);
        assertThat(subTestPage.includedPage).isNotNull();
        assertThat(subTestPage.anotherIncludedPage).isNotNull();
    }
}

class TestPage extends FluentPage {

    @Page
    IncludedPage includedPage;
}

class SubSubTestPage extends SubTestPage {
}

class SubTestPage extends TestPage {

    @Page
    IncludedPage anotherIncludedPage;
}

class SubTestPageWithCreate extends FluentPage {

    public IncludedPage pageWithCreatePage;

    public void initFluent(FluentControl control) {
        super.initFluent(control);
        pageWithCreatePage = newInstance(IncludedPage.class);
    }

}

class IncludedPage extends FluentPage {

    FluentWebElement element;
}
