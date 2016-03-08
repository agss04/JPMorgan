package stock.com.simple.stock.market.type;

public enum StockTypeEnum {   
    COMMON(1, "Common"), PREFERRED(2, "Preferred");

    private int code;
    private String name;
    
    StockTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public static StockTypeEnum fromEventName(String v) {
        for (StockTypeEnum stockTypeEnum : StockTypeEnum.values()) {
            if (stockTypeEnum.name.equals(v)) {
                return stockTypeEnum;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static StockTypeEnum fromEventCode(long v) {
        for (StockTypeEnum stockTypeEnum : StockTypeEnum.values()) {
            if (stockTypeEnum.code == v) {
                return stockTypeEnum;
            }
        }
        throw new IllegalArgumentException("" + v);
    }
}