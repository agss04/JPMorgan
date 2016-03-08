package stock.com.simple.stock.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import stock.com.simple.stock.market.bean.GlobalBeverageCorporationExchangeBean;
import stock.com.simple.stock.market.exception.DataException;
import stock.com.simple.stock.market.type.TradeTypeEnum;

public class StockMarket {
    
    public BigDecimal calculateDividend(GlobalBeverageCorporationExchangeBean bean, BigDecimal marketPrice) throws DataException {
        switch(bean.getType()) {
            case 1:
                if(bean.getLastDividend().compareTo(BigDecimal.ZERO)==0){
                    throw new DataException("Last Dividend is Zero for "+bean.getStockSymbol());
                }
                return bean.getLastDividend().divide(marketPrice);
            case 2:
                if(bean.getFixedDividend().compareTo(BigDecimal.ZERO)==0){
                    throw new DataException("Fixed Dividend is Zero for "+bean.getStockSymbol());
                }else if(bean.getParValue().compareTo(BigDecimal.ZERO)==0){
                    throw new DataException("Par Value is Zero for "+bean.getStockSymbol());
                }
                return (bean.getFixedDividend().multiply(bean.getParValue())).divide(marketPrice);
            default:
                return BigDecimal.ZERO;
        }
    }
    
    public BigDecimal calculatePERatio(BigDecimal dividend, BigDecimal marketPrice) {
        return marketPrice.divide(dividend,RoundingMode.HALF_UP);
    }
    
    public TradingMarket buyStocks(Integer quantity, BigDecimal price) {
        return new TradingMarket(TradeTypeEnum.BUY, quantity, price,new Date());       
    }

    public TradingMarket sellStocks(Integer quantity, BigDecimal price) {
        return new TradingMarket(TradeTypeEnum.SELL, quantity, price,new Date());      
    }
    
    public double findGeoMetricMean(double num, int root)   {
        return Math.pow(Math.E, Math.log(num)/root);
    } 
    
    public BigDecimal calculateVolumeWeightedStockPrice(TreeMap<Date, TradingMarket> trades) {
        Date now = new Date();       
        Date startTime = new Date(now.getTime() - (15 * 60 * 1000));        
        SortedMap<Date, TradingMarket> tradesMap = trades.tailMap(startTime);      
        BigDecimal volumeWeigthedStockPrice = BigDecimal.ZERO;
        Integer totalQuantity = 0;
        for (TradingMarket tradesTemp: tradesMap.values()) {
            totalQuantity += tradesTemp.getQuantity();
            volumeWeigthedStockPrice = volumeWeigthedStockPrice.add(tradesTemp.getPrice().multiply(new BigDecimal(tradesTemp.getQuantity())));
        }
        return volumeWeigthedStockPrice.divide(new BigDecimal(totalQuantity),RoundingMode.HALF_UP);
    }
}
