package stock.com.simple.stock.market;

import java.math.BigDecimal;

import stock.com.simple.stock.market.exception.DataException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class SimpleStockMarketApplicationTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SimpleStockMarketApplicationTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SimpleStockMarketApplicationTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testSimpleStockMarketApplicationFailure()    
    {
        SimpleStockMarketApplication simpleStock = new SimpleStockMarketApplication();
        try {
            simpleStock.calculateStockDetails(BigDecimal.ZERO);
        } catch (DataException e) {
            assertTrue( true );
        } catch (InterruptedException e) {
            assertTrue( false );
        }       
    }
    
    
    public void testSimpleStockMarketApplicationPass()    
    {
        SimpleStockMarketApplication simpleStock = new SimpleStockMarketApplication();
        try {
            simpleStock.calculateStockDetails(new BigDecimal(100));
        } catch (DataException e) {
            assertTrue( false );
        } catch (InterruptedException e) {
            assertTrue( false );
        }
        assertTrue( true );
    }
    
    
}
