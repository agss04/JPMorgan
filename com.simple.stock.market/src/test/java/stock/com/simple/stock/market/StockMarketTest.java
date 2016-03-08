package stock.com.simple.stock.market;

import java.math.BigDecimal;

import junit.framework.TestCase;

public class StockMarketTest extends TestCase {
    public void testBuyStockPass() {
        StockMarket stock = new StockMarket();       
        TradingMarket trade = stock.buyStocks(5, new BigDecimal(5.0));
        if(trade.getPrice().compareTo(new BigDecimal(5))==0){
            assertTrue(true);
        }           
    }

    public void testBuyStockFailure() {
        StockMarket stock = new StockMarket();       
        TradingMarket trade = stock.buyStocks(5, new BigDecimal(5.0));
        if(trade.getPrice().compareTo(new BigDecimal(5))!=0){
            assertTrue(false);
        }           
    }
    
    public void testSellStockPass() {
        StockMarket stock = new StockMarket();       
        TradingMarket trade = stock.sellStocks(5, new BigDecimal(5.0));
        if(trade.getPrice().compareTo(new BigDecimal(5))==0){
            assertTrue(true);
        }           
    }

    public void testSellStockFailure() {
        StockMarket stock = new StockMarket();       
        TradingMarket trade = stock.sellStocks(5, new BigDecimal(5.0));
        if(trade.getPrice().compareTo(new BigDecimal(5))!=0){
            assertTrue(false);
        }           
    }
    public void testcalculatePERatioFailure()    
    {
        StockMarket stock = new StockMarket();       
        BigDecimal peRatio = stock.calculatePERatio(new BigDecimal(5.0), new BigDecimal(5.0));
        if(peRatio.compareTo(new BigDecimal(1))!=0){
            assertTrue(false);
        }       
    }
    
    public void testcalculatePERatioPass()    
    {
        StockMarket stock = new StockMarket();       
        BigDecimal peRatio = stock.calculatePERatio(new BigDecimal(5.0), new BigDecimal(5.0));
        if(peRatio.compareTo(new BigDecimal(1))==0){
            assertTrue(true);
        }       
    }
}
