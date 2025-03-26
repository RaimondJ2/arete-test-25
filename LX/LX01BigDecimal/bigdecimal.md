# BigInteger ja BigDecimal

Kaust Gitis: ``LX/LX01BigDecimal``

Pakk: ``ee.taltech.iti0202.bigdecimal``

BigDecimal-i ja BigInteger-i kohta saab täpsemalt lugeda siit [javadoc](https://javadoc.pages.taltech.ee/data_types/numeric-types.html#biginteger-ja-bigdecimal-klassid)-ist.

Ülesandeks on kirjutada meetodid, kus tehakse ette antud arvutused kasutades BigDecimal-i ja BigInteger-i.

**Enamasti on funktisiooni parameetrid antud int või double väärtusega, ja kohati võivad need numbrid väga suured olla. Siin tasuks meenutada, mis juhtub siis kui integer väärtus üle enda maksimaalse piiri läheb.**

Realiseerige Klass ``BigNumber``:

`BigInteger multiplyBigInteger(int factor1, int factor2)` - Korruta 2 Integer väärtust ja tagasta korrutis BigIntegerina.

`BigInteger divideBigInteger(BigInteger dividend, int divisor)` - Jaga BigInteger, Integer väärtusega ja tagasta tulemus BigIntegerina.

`BigInteger addBigInteger(int add1, int add2)` - Liida 2 Integer väätust ja tagasta summa BigIntegerina.

`BigInteger subtractBigInteger(BigInteger minuend, int subtrahend)` - Lahuta Intger väärtus BigIntegeri väärtusest ja tagasta vahe BigIntegerina.

`BigDecimal multiplyAndRoundBigDecimal(double value, int multiplier, int rounding)` - Korruta double väärtus int väärtusega, ja ümarda nii mitu koma kohta kui on rounding väärtuses antud. Tagasta tulemus BigDecimalina.

`BigInteger factorial(int n)` - Tagasta n faktoriaal BigIntegerina.

`BigInteger power(int base, int exponent)` - Arvuta base astmes exponent ja tagasta tulemus BigIntegerina.

`boolean isSame(BigDecimal val1, BigDecimal val2, int rounding)` - Ümarda esimene ja teine BigDecimali väärtus nii mitu koma kohta kui on antud rounding väärtuses. Ja peale seda tagasta, kas need 2 ümardatud väärtust on võrdsed.

`BigInteger fibonacci(int n)` - Leia n-is fibonacci arv ja tagasta see BigIntegerina. Mis on fibonacci arvud saab lugeda [siit](https://www.mathsisfun.com/numbers/fibonacci-sequence.html).

`BBigInteger lucas(int n)` - Leia n-is lucas' arv ja tagasta see BigIntegerina. Mis on lucas' arvud saab lugeda [siit](https://brilliant.org/wiki/lucas-numbers/)

## Mall:
```java
package ee.taltech.iti0202.bigdecimal;

public class BigNumber {
    /**
     * Multiply 2 int values and return product in BigInteger
     * @param factor1 first factor
     * @param factor2 second factor
     * @return factor1 x factor2
     */
    public BigInteger multiplyBigInteger(int factor1, int factor2) {
        return BigInteger.ZERO;
    }

    /**
     * Divide 2 values and return quotient in BigInteger
     * If divisor is 0, return 0
     * @param dividend dividend
     * @param divisor divisor
     * @return dividend / divisor
     */
    public BigInteger divideBigInteger(BigInteger dividend, int divisor) {
        return BigInteger.ZERO;
    }

    /**
     * Add 2 values and return sum in BigInteger
     * @param add1 first value
     * @param add2 second value
     * @return add1 + add2
     */
    public BigInteger addBigInteger(int add1, int add2) {
        return BigInteger.ZERO;
    }

    /**
     * Subtract 2 values and return difference in BigInteger
     * @param minuend first value
     * @param subtrahend second value
     * @return value1 - value2
     */
    public BigInteger subtractBigInteger(BigInteger minuend, int subtrahend) {
        return BigInteger.ZERO;
    }

    /**
     * Multiply double value with int multiplier, round according to rounding
     * and return in BigDecimal
     * @param value value to calculate
     * @param multiplier multiplier to use
     * @param rounding rounding to use
     * @return value multiplied by multiplier and rounded by rounding
     */
    public BigDecimal multiplyAndRoundBigDecimal(double value, int multiplier, int rounding) {
        return BigDecimal.ZERO;
    }

    /**
     * Calculate n factorial and return in BigInteger
     * If n < 0, it should return 1
     * @param n n-th factorial to calculate
     * @return n-th factorial
     */
    public BigInteger factorial(int n) {
        return BigInteger.ZERO;
    }

    /**
     * Calculate base to the power of exponent and return in BigInteger
     * @param base base
     * @param exponent exponent
     * @return base to the power of exponent
     */
    public BigInteger power(int base, int exponent) {
        return BigInteger.ZERO;
    }

    /**
     * Round val1 and val2 with the rounding given, and check if they are equal after that
     * @param val1 first value to round
     * @param val2 second value to round
     * @param rounding rounding to use
     * @return true or false if val1 and val2 are equal after rounded with rounding
     */
    public boolean isSame(BigDecimal val1, BigDecimal val2, int rounding) {
        return false;
    }

    /**
     *
     * @param n n-th fib number to calculate
     * @return n-th fib number value
     */
    public BigInteger fibonacci(int n) {
        return BigInteger.ZERO;
    }

    /**
     *
     * @param n n-th luc number to calculate
     * @return n-th lucas number
     */
    public BigInteger lucas(int n) {
        return BigInteger.ZERO;
    }
}

```
