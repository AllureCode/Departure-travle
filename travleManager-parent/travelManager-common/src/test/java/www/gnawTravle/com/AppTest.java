package www.gnawTravle.com;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import www.gnawTravle.com.travel.utils.Tools;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     * test tools about string is not null
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        String s = " ";
       if (Tools.isEmpty(s)){
           System.out.println("空");
       }else {
           System.out.println("非空");
       }
        assertTrue( true );
    }
}
