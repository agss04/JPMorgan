package stock.com.simple.stock.market;

import java.math.BigDecimal;
import java.util.Date;

import stock.com.simple.stock.market.type.TradeTypeEnum;


public class TradingMarket {
    private TradeTypeEnum tradeType;
    private Integer quantity;
    private BigDecimal price;
    private Date date;
    public TradeTypeEnum getTradeType() {
        return tradeType;
    }
    public void setTradeType(TradeTypeEnum tradeType) {
        this.tradeType = tradeType;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public TradingMarket(TradeTypeEnum type, Integer quantity, BigDecimal price,Date date) {
        this.setTradeType(type);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setDate(date);
    }
    
}
