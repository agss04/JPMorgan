package stock.com.simple.stock.market;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import stock.com.simple.stock.market.bean.GlobalBeverageCorporationExchangeBean;
import stock.com.simple.stock.market.exception.DataException;
import stock.com.simple.stock.market.type.StockTypeEnum;


public class SimpleStockMarketApplication {

    List<GlobalBeverageCorporationExchangeBean> beanList = new ArrayList<GlobalBeverageCorporationExchangeBean>();
    final static Logger log = Logger.getLogger(SimpleStockMarketApplication.class); 
    SimpleStockMarketApplication() {
        initialValidDataSetup();       
    }
    

    public static void main(String[] args) throws InterruptedException, DataException {
        BigDecimal marketPrice = new BigDecimal(100);        
        SimpleStockMarketApplication simpleStock = new SimpleStockMarketApplication();
        simpleStock.calculateStockDetails(marketPrice);
    }

    public void calculateStockDetails(BigDecimal marketPrice) throws DataException, InterruptedException {
        if(marketPrice.compareTo(BigDecimal.ZERO) == 0) {
            log.error("Please check the entered Values...MarketPrice is Zero.");
            throw new DataException("Please check the entered Values...MarketPrice is Zero.");
        }
       
        StockMarket stock = new StockMarket();      
       
        for (GlobalBeverageCorporationExchangeBean bean:beanList){  
            log.info("Stock Symbol :"+bean.getStockSymbol()+"  Type "+StockTypeEnum.fromEventCode(bean.getType()));
            BigDecimal dividend = stock.calculateDividend(bean, marketPrice);          
            log.info("i.Calculated dividend yield ="+dividend);
            BigDecimal peRatio = stock.calculatePERatio(dividend, marketPrice);        
            log.info("ii. Calculated P/E Ratio"+peRatio.setScale(4, BigDecimal.ROUND_UP));
            if(peRatio.compareTo(BigDecimal.ZERO) == 0) {
                log.error("P/E Ratio is Zero.");
                throw new DataException("P/E Ratio is Zero.");
            }
            populateStockData(bean);
            if(bean.getTrades()!=null && bean.getTrades().size()>0){             
                BigDecimal volumeWeighted = stock.calculateVolumeWeightedStockPrice(bean.getTrades()).setScale(4, RoundingMode.HALF_UP);                
                log.info("iv. Volume Weighted Stock Price based on trades of "+bean.getStockSymbol()+" in past 15 minutes ="+volumeWeighted);
            }            
            log.info("------------------------------------------------------------------------------------------------------------------------------------");
        }
        BigDecimal priceMultiply = BigDecimal.ONE;
        int totalStocks = 0;
        for (GlobalBeverageCorporationExchangeBean bean:beanList){          
            for ( TradingMarket trade : bean.getTrades().values()){  
                totalStocks++;
                priceMultiply = priceMultiply.multiply(trade.getPrice());
            }
        }
        Double geometricMean = stock.findGeoMetricMean(priceMultiply.doubleValue(),totalStocks);
      
        log.info("b. Calculate the GBCE All Share Index using the geometric mean of prices for all stocks = "+new BigDecimal(geometricMean).setScale(4, RoundingMode.HALF_UP));
    }

    
    private void populateStockData(GlobalBeverageCorporationExchangeBean bean) throws InterruptedException{
        StockMarket stock = new StockMarket();
     
        log.info("iii. Record a trade, with timestamp, quantity of shares, buy or sell indicator and trade price for "+bean.getStockSymbol());
        for (int i=1; i <= 2; i++) {
            Integer randomStocks = getRandomQuantity();
            BigDecimal randomPrice = getRandomPrice().setScale(4, BigDecimal.ROUND_UP);;
            TradingMarket trade =stock.buyStocks(randomStocks, randomPrice);
           
            log.info( bean.getStockSymbol() + " bought " + randomStocks + " shares at $" + randomPrice);
            bean.addTrades(trade);
            Thread.sleep(1000);
            
            randomStocks = getRandomQuantity();
            randomPrice = getRandomPrice().setScale(4, BigDecimal.ROUND_UP);
            trade = stock.sellStocks(randomStocks, getRandomPrice());
          
            log.info( bean.getStockSymbol() + " sold " + randomStocks + " shares at $" + randomPrice);
            bean.addTrades(trade);
            Thread.sleep(1000);
        }
    }
    
    private BigDecimal getRandomPrice(){
        Random r = new Random(System.currentTimeMillis());
        return new BigDecimal(r.nextInt(100000) * 0.1);
    }
    
    private Integer getRandomQuantity(){
        Random rnd = new Random(System.currentTimeMillis());
        return rnd.nextInt(900) + 100;
    }
    //To verify with invalid input
    @SuppressWarnings("unused")
    private void initialInValidDataSetup() {

        GlobalBeverageCorporationExchangeBean bean1 = new GlobalBeverageCorporationExchangeBean();
        bean1.setStockSymbol("TEA");
        bean1.setType(StockTypeEnum.COMMON.getCode());
        bean1.setLastDividend(new BigDecimal(0));
        bean1.setParValue(new BigDecimal(100));
        beanList.add(bean1);

        GlobalBeverageCorporationExchangeBean bean2 = new GlobalBeverageCorporationExchangeBean();
        bean2.setStockSymbol("POP");
        bean2.setType(StockTypeEnum.COMMON.getCode());
        bean2.setLastDividend(new BigDecimal(8));
        bean2.setParValue(new BigDecimal(100));
        beanList.add(bean2);

        GlobalBeverageCorporationExchangeBean bean3 = new GlobalBeverageCorporationExchangeBean();
        bean3.setStockSymbol("ALE");
        bean3.setType(StockTypeEnum.COMMON.getCode());
        bean3.setLastDividend(new BigDecimal(23));
        bean3.setParValue(new BigDecimal(100));
        beanList.add(bean3);

        GlobalBeverageCorporationExchangeBean bean4 = new GlobalBeverageCorporationExchangeBean();
        bean4.setStockSymbol("GIN");
        bean4.setType(StockTypeEnum.PREFERRED.getCode());
        bean4.setLastDividend(new BigDecimal(8));
        bean4.setFixedDividend(new BigDecimal(2));
        bean4.setParValue(new BigDecimal(100));
        beanList.add(bean4);

        GlobalBeverageCorporationExchangeBean bean5 = new GlobalBeverageCorporationExchangeBean();
        bean5.setStockSymbol("JOE");
        bean5.setType(StockTypeEnum.COMMON.getCode());
        bean5.setLastDividend(new BigDecimal(13));
        bean5.setParValue(new BigDecimal(250));
        beanList.add(bean5);
    }
    
    //To verify with valid input
    private void initialValidDataSetup() {

        GlobalBeverageCorporationExchangeBean bean1 = new GlobalBeverageCorporationExchangeBean();
        bean1.setStockSymbol("TEA");
        bean1.setType(StockTypeEnum.COMMON.getCode());
        bean1.setLastDividend(new BigDecimal(3));
        bean1.setParValue(new BigDecimal(100));
        beanList.add(bean1);

        GlobalBeverageCorporationExchangeBean bean2 = new GlobalBeverageCorporationExchangeBean();
        bean2.setStockSymbol("POP");
        bean2.setType(StockTypeEnum.COMMON.getCode());
        bean2.setLastDividend(new BigDecimal(8));
        bean2.setParValue(new BigDecimal(100));
        beanList.add(bean2);

        GlobalBeverageCorporationExchangeBean bean3 = new GlobalBeverageCorporationExchangeBean();
        bean3.setStockSymbol("ALE");
        bean3.setType(StockTypeEnum.COMMON.getCode());
        bean3.setLastDividend(new BigDecimal(23));
        bean3.setParValue(new BigDecimal(100));
        beanList.add(bean3);

        GlobalBeverageCorporationExchangeBean bean4 = new GlobalBeverageCorporationExchangeBean();
        bean4.setStockSymbol("GIN");
        bean4.setType(StockTypeEnum.PREFERRED.getCode());
        bean4.setLastDividend(new BigDecimal(8));
        bean4.setFixedDividend(new BigDecimal(2));
        bean4.setParValue(new BigDecimal(100));
        beanList.add(bean4);

        GlobalBeverageCorporationExchangeBean bean5 = new GlobalBeverageCorporationExchangeBean();
        bean5.setStockSymbol("JOE");
        bean5.setType(StockTypeEnum.COMMON.getCode());
        bean5.setLastDividend(new BigDecimal(13));
        bean5.setParValue(new BigDecimal(250));
        beanList.add(bean5);
    }  
}
