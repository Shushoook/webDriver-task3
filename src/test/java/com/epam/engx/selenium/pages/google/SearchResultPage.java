package com.epam.engx.selenium.pages.google;

import com.epam.engx.selenium.pages.browser.Page;
import com.epam.engx.selenium.pages.model.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public final class SearchResultPage extends Page {
    @FindBy(xpath = "//div[contains(@class, 'gsc-result')]//div[contains(@class, 'gsc-thumbnail-inside')]")
    private List<WebElement> results;

    @FindBy(css = "a:first-child > b")
    private WebElement firstResultLink;

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    public List<Link> links() {
        FluentWait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20), Duration.ofMillis(500)).ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfAllElements(results));
        return results.stream().map(Link::new).toList();
    }

    public void clickFirstLink() {
        firstResultLink.click();
    }
}
