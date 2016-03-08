package stock.com.simple.stock.market.bean;
import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.Date;

import stock.com.simple.stock.market.TradingMarket;


public class GlobalBeverageCorporationExchangeBean {
    private String stockSymbol ;
    private int type; 
    private BigDecimal lastDividend;
    private BigDecimal fixedDividend;
    private BigDecimal parValue;
    
    private BigDecimal dividend;
    private BigDecimal peRatio;
    
    private TreeMap<Date, TradingMarket> trades;
    
    public String getStockSymbol() {
        return stockSymbol;
    }
    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public BigDecimal getLastDividend() {
        return lastDividend;
    }
    public void setLastDividend(BigDecimal lastDividend) {
        this.lastDividend = lastDividend;
    }
    public BigDecimal getFixedDividend() {
        return fixedDividend;
    }
    public void setFixedDividend(BigDecimal fixedDividend) {
        this.fixedDividend = fixedDividend;
    }
    public BigDecimal getParValue() {
        return parValue;
    }
    public void setParValue(BigDecimal parValue) {
        this.parValue = parValue;
    }
    public BigDecimal getDividend() {
        return dividend;
    }
    public void setDividend(BigDecimal dividend) {
        this.dividend = dividend;
    }
    public BigDecimal getPeRatio() {
        return peRatio;
    }
    public void setPeRatio(BigDecimal peRatio) {
        this.peRatio = peRatio;
    }
    
    public TreeMap<Date, TradingMarket> getTrades() {
        return trades;
    }

    public void setTrades(TreeMap<Date, TradingMarket> trades) {
        this.trades = trades;
    }
    
    public void addTrades(TradingMarket trade){
        if(this.trades==null){
            this.trades = new TreeMap<Date, TradingMarket>();
        }
        this.trades.put(trade.getDate(), trade);
    }
}
