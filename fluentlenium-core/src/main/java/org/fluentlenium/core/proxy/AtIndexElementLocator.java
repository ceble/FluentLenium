package org.fluentlenium.core.proxy;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * {@link ElementLocator} retrieving a particular index element from another locator.
 */
public class AtIndexElementLocator implements ElementLocator {
    private final int index;
    private ElementLocator listLocator;

    public AtIndexElementLocator(ElementLocator listLocator, int index) {
        this.listLocator = listLocator;
        this.index = index;
    }

    @Override
    public WebElement findElement() {
        List<WebElement> elements = this.listLocator.findElements();
        if (index >= elements.size()) return null;
        return elements.get(index);
    }

    @Override
    public List<WebElement> findElements() {
        WebElement element = findElement();
        if (element == null) return Collections.emptyList();
        return Arrays.asList(element);
    }
}
