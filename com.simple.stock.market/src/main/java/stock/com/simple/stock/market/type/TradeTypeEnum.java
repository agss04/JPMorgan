package stock.com.simple.stock.market.type;

public enum TradeTypeEnum {
    BUY(1), SELL(2);

    private int code;

    TradeTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
