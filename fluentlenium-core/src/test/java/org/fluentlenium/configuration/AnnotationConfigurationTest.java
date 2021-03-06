package org.fluentlenium.configuration;


import org.assertj.core.api.Assertions;
import org.fluentlenium.configuration.AbstractPropertiesConfigurationTest.DummyConfigurationDefaults;
import org.fluentlenium.configuration.AbstractPropertiesConfigurationTest.DummyConfigurationFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AnnotationConfigurationTest {
    private static AnnotationConfiguration configuration;
    private static AnnotationConfiguration defaultConfiguration;
    private static AnnotationConfiguration noConfiguration;

    @FluentConfiguration(baseUrl = "http://localhost:3000", configurationFactory = DummyConfigurationFactory.class,
            configurationDefaults = DummyConfigurationDefaults.class, eventsEnabled = FluentConfiguration.BooleanValue.FALSE,
            capabilities = "{javascriptEnabled: true}",
            htmlDumpMode = ConfigurationProperties.TriggerMode.AUTOMATIC_ON_FAIL, htmlDumpPath = "/html-path", implicitlyWait = 1000, pageLoadTimeout = 2000,
            screenshotMode = ConfigurationProperties.TriggerMode.MANUAL, screenshotPath = "/screenshot-path", scriptTimeout = 3000, webDriver = "firefox")
    public static class ConfiguredClass {
    }

    @FluentConfiguration
    public static class DefaultClass {
    }

    @BeforeClass
    public static void beforeClass() {
        configuration = new AnnotationConfiguration(ConfiguredClass.class);
        defaultConfiguration = new AnnotationConfiguration(DefaultClass.class);
        noConfiguration = new AnnotationConfiguration(Object.class);
    }

    @Test
    public void configurationFactory() {
        Assertions.assertThat(configuration.getConfigurationFactory()).isEqualTo(DummyConfigurationFactory.class);
    }

    @Test
    public void configurationDefaults() {
        Assertions.assertThat(configuration.getConfigurationDefaults()).isEqualTo(DummyConfigurationDefaults.class);
    }

    @Test
    public void webDriver() {
        Assertions.assertThat(noConfiguration.getWebDriver()).isNull();
        Assertions.assertThat(defaultConfiguration.getWebDriver()).isNull();

        Assertions.assertThat(configuration.getWebDriver()).isEqualTo("firefox");
    }

    @Test
    public void capabilities() {
        Assertions.assertThat(noConfiguration.getCapabilities()).isNull();
        Assertions.assertThat(defaultConfiguration.getCapabilities()).isNull();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setJavascriptEnabled(true);

        Assertions.assertThat(configuration.getCapabilities()).isEqualTo(capabilities);
    }

    @Test
    public void baseUrl() {
        Assertions.assertThat(noConfiguration.getBaseUrl()).isNull();
        Assertions.assertThat(defaultConfiguration.getBaseUrl()).isNull();

        Assertions.assertThat(configuration.getBaseUrl()).isEqualTo("http://localhost:3000");
    }

    @Test
    public void pageLoadTimeout() {
        Assertions.assertThat(noConfiguration.getPageLoadTimeout()).isNull();
        Assertions.assertThat(defaultConfiguration.getPageLoadTimeout()).isNull();

        Assertions.assertThat(configuration.getPageLoadTimeout()).isEqualTo(2000L);
    }

    @Test
    public void implicitlyWait() {
        Assertions.assertThat(noConfiguration.getImplicitlyWait()).isNull();
        Assertions.assertThat(defaultConfiguration.getImplicitlyWait()).isNull();

        Assertions.assertThat(configuration.getImplicitlyWait()).isEqualTo(1000L);
    }

    @Test
    public void scriptTimeout() {
        Assertions.assertThat(noConfiguration.getScriptTimeout()).isNull();
        Assertions.assertThat(defaultConfiguration.getScriptTimeout()).isNull();

        Assertions.assertThat(configuration.getScriptTimeout()).isEqualTo(3000L);
    }

    @Test
    public void eventsEnabled() {
        Assertions.assertThat(noConfiguration.getEventsEnabled()).isNull();
        Assertions.assertThat(defaultConfiguration.getEventsEnabled()).isNull();

        Assertions.assertThat(configuration.getEventsEnabled()).isEqualTo(false);
    }

    @Test
    public void screenshotPath() {
        Assertions.assertThat(noConfiguration.getScreenshotPath()).isNull();
        Assertions.assertThat(defaultConfiguration.getScreenshotPath()).isNull();

        Assertions.assertThat(configuration.getScreenshotPath()).isEqualTo("/screenshot-path");
    }

    @Test
    public void htmlDumpPath() {
        Assertions.assertThat(noConfiguration.getHtmlDumpPath()).isNull();
        Assertions.assertThat(defaultConfiguration.getHtmlDumpPath()).isNull();

        Assertions.assertThat(configuration.getHtmlDumpPath()).isEqualTo("/html-path");
    }

    @Test
    public void screenshotMode() {
        Assertions.assertThat(noConfiguration.getScreenshotMode()).isNull();
        Assertions.assertThat(defaultConfiguration.getScreenshotMode()).isNull();

        Assertions.assertThat(configuration.getScreenshotMode()).isEqualTo(ConfigurationProperties.TriggerMode.MANUAL);
    }

    @Test
    public void htmlDumpMode() {
        Assertions.assertThat(noConfiguration.getHtmlDumpMode()).isNull();
        Assertions.assertThat(defaultConfiguration.getHtmlDumpMode()).isNull();

        Assertions.assertThat(configuration.getHtmlDumpMode()).isEqualTo(ConfigurationProperties.TriggerMode.AUTOMATIC_ON_FAIL);
    }
}
