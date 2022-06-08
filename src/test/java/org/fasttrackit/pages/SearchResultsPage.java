package org.fasttrackit.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchResultsPage extends PageObject {

    @FindBy(css = ".products-grid>li")
    private List<WebElementFacade> productsList;

    @FindBy(css="div.category-products>div>div.sorter select")
    private WebElementFacade sortInDropDown;


    public boolean checkListForProduct(String productName){
        for(WebElementFacade element : productsList){
            if(element.findElement(By.cssSelector("h2.product-name a")).getText().equalsIgnoreCase(productName)){
                return true;
            }
        }
        return false;
    }

    public void selectProductFromList(String product){
        for(WebElementFacade element : productsList){
            if(element.findElement(By.cssSelector("product-collection-image-425")).getText().equalsIgnoreCase(product)){
                element.findElement(By.cssSelector("#product-collection-image-425")).click();
                break;
            }
        }
    }

    public void selectFromDropDown(String value){
        Select dropDown = new Select(sortInDropDown);
        dropDown.selectByVisibleText(value);
    }

    public boolean checkListOrdered(){
        int firstItemPrice = Integer.valueOf(productsList.get(1).findElement(By.cssSelector("div.price-box span.price")).getText().split(",")[0]);
        int lastItemPrice = Integer.valueOf(productsList.get(productsList.size() - 1).findElement(By.cssSelector("div.price-box span.price")).getText().split(",")[0]);
        return firstItemPrice <= lastItemPrice;
    }
}
