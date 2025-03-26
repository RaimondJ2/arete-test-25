package ee.taltech.iti0202.bigdecimal;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

import static org.testng.Assert.*;

public class BigDecimalTest {
    private final BigNumber num = new BigNumber();
    private final Random random = new Random();
    private final int BIG_NUMBER_ONE = 2147483647;
    private final int BIG_NUMBER_TWO = 2000000000;

    @Test(timeOut = 1000)
    public void testIsTwoDecimalsSameWithRounding() {
        BigDecimal dec1 = BigDecimal.valueOf(32.141543);
        BigDecimal dec2 = BigDecimal.valueOf(3.671541);
        BigDecimal dec3 = BigDecimal.valueOf(128.492);
        BigDecimal dec4 = BigDecimal.valueOf(128.511);

        assertTrue(num.isSame(dec1, dec2, 6));
        assertFalse(num.isSame(dec1, dec2, 7));
        assertFalse(num.isSame(dec3, dec4, 3));
        assertTrue(num.isSame(dec3, dec4, 4));
    }

    @Test(timeOut = 1000)
    public void testIsTwoDecimalsSameWithRoundingRandomNumber() {
        int randomNumber1 = random.nextInt(9999 - 1000) + 1000;
        double randomDouble1 = randomNumber1 + random.nextDouble();

        int randomNumber2 = random.nextInt(9999 - 1000) + 1000;
        double randomDouble2 = randomNumber2 + random.nextDouble();

        int rounding = random.nextInt(10 - 3) + 3;

        BigDecimal bigDecimal1 = BigDecimal.valueOf(randomDouble1);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(randomDouble2);

        boolean isSame = bigDecimal1.round(new MathContext(rounding))
                .equals(bigDecimal2.round(new MathContext(rounding)));

        assertEquals(num.isSame(bigDecimal1, bigDecimal2, rounding), isSame);
    }

    @Test(timeOut = 1000)
    public void testMultiplyBigInteger() {
        int randomNumber1 = random.nextInt(BIG_NUMBER_ONE - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        int randomNumber2 = random.nextInt(BIG_NUMBER_ONE - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        BigInteger value = BigInteger.valueOf(randomNumber1)
                .multiply(BigInteger.valueOf(randomNumber2));
        assertEquals(num.multiplyBigInteger(randomNumber1, randomNumber2), value);
    }

    @Test(timeOut = 1000)
    public void testAddBigInteger() {
        int randomNumber1 = random.nextInt(2147483647 - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        int randomNumber2 = random.nextInt(2147483647 - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        BigInteger value = BigInteger.valueOf(randomNumber1)
                .add(BigInteger.valueOf(randomNumber2));
        assertEquals(num.addBigInteger(randomNumber1, randomNumber2), value);
    }

    @Test(timeOut = 1000)
    public void testSubtractBigInteger() {
        int randomNumber1 = random.nextInt(BIG_NUMBER_ONE - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        int randomNumber2 = random.nextInt(30 - 5) + 5;

        BigInteger bigInteger1 = BigInteger.valueOf(randomNumber1)
                .multiply(BigInteger.valueOf(randomNumber2));

        int randomNumber3 = random.nextInt(BIG_NUMBER_ONE - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        BigInteger value = bigInteger1
                .subtract(BigInteger.valueOf(randomNumber3));
        assertEquals(num.subtractBigInteger(bigInteger1, randomNumber3), value);
    }

    @Test(timeOut = 1000)
    public void testDivideBigInteger() {
        int randomNumber1 = random.nextInt(BIG_NUMBER_ONE - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        int randomNumber2 = random.nextInt(30 - 5) + 5;

        BigInteger bigInteger1 = BigInteger.valueOf(randomNumber1)
                .multiply(BigInteger.valueOf(randomNumber2));

        int randomNumber3 = random.nextInt(BIG_NUMBER_ONE - BIG_NUMBER_TWO) + BIG_NUMBER_TWO;
        BigInteger value = bigInteger1
                .divide(BigInteger.valueOf(randomNumber3));
        assertEquals(num.divideBigInteger(bigInteger1, randomNumber3), value);
    }

    @Test(timeOut = 1000)
    public void testMultiplyAndRoundBigDecimal() {
        double randDouble1 = (random.nextInt(9999 - 1000) + 1000) + random.nextDouble();
        int multiplier = random.nextInt(9999 - 1000) + 1000;
        int rounding = random.nextInt(8 - 2) + 2;
        BigDecimal ans = BigDecimal.valueOf(randDouble1).multiply(BigDecimal.valueOf(multiplier),
                new MathContext(rounding));
        assertEquals(num.multiplyAndRoundBigDecimal(randDouble1, multiplier, rounding), ans);
    }

    @Test(timeOut = 1000)
    public void testIsFactorialCorrect() {
        assertEquals(num.factorial(30), new BigInteger("265252859812191058636308480000000"));
        assertEquals(num.factorial(10), BigInteger.valueOf(3628800));
        assertEquals(num.factorial(7), BigInteger.valueOf(5040));
        assertEquals(num.factorial(2), BigInteger.valueOf(2));
        assertEquals(num.factorial(0), BigInteger.valueOf(1));
        assertEquals(num.factorial(-1), BigInteger.valueOf(1));
    }

    @Test(timeOut = 1000)
    public void testisPowerCalculationCorrect() {
        int base1 = random.nextInt(100 - 20) + 20;
        int exponent1 = random.nextInt(50 - 20) + 20;
        BigInteger answer = BigInteger.valueOf(base1).pow(exponent1);
        assertEquals(num.power(base1, exponent1), answer);
    }

    @Test(timeOut = 1000)
    public void testFibonacciCalculationCorrect() {
        assertEquals(num.fibonacci(572),
                new BigInteger(
                        "155397926921764129586412153075574099601109247922558905818374286709640260695423396870480843150490968148047148588235083389"));
        assertEquals(num.fibonacci(235), new BigInteger("5789092068864820527338372482892113982249794889765"));
        assertEquals(num.fibonacci(20), BigInteger.valueOf(6765));
        assertEquals(num.fibonacci(10), BigInteger.valueOf(55));
        assertEquals(num.fibonacci(3), BigInteger.TWO);
        assertEquals(num.fibonacci(2), BigInteger.ONE);
        assertEquals(num.fibonacci(1), BigInteger.ONE);
        assertEquals(num.fibonacci(0), BigInteger.ZERO);
        assertEquals(num.fibonacci(-1), BigInteger.ZERO);
    }

    @Test(timeOut = 1000)
    public void testLucasCalculationCorrect() {
        assertEquals(num.lucas(762),
                new BigInteger(
                        "1772482150909261266935809714765791508254729174221668720920311171304840749088492994378238885428132998042510078073991707620874171944133780111288137927200206172178"));
        assertEquals(num.lucas(154), new BigInteger("152790586683089283455442974745243"));
        assertEquals(num.lucas(28), BigInteger.valueOf(710647));
        assertEquals(num.lucas(13), BigInteger.valueOf(521));
        assertEquals(num.lucas(2), BigInteger.valueOf(3));
        assertEquals(num.lucas(1), BigInteger.ONE);
        assertEquals(num.lucas(0), BigInteger.TWO);
        assertEquals(num.lucas(-1), BigInteger.TWO);
    }
}
