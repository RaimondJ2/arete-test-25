package ee.taltech.iti0202.stream.basics;

import ee.ttu.java.studenttester.annotations.TestContextConfiguration;
import ee.ttu.java.studenttester.enums.ReportMode;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.SequencedCollection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertListContainsObject;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertThrows;

@Test(timeOut = 10_000, groups = {"basics"})
@TestContextConfiguration(mode = ReportMode.VERBOSE)
public class StreamBasicsTest {

    private static final SequencedCollection<Integer> COLLECTION = new CopyOnWriteArrayList<>();

    static {
        COLLECTION.addAll(List.of(63, 2, 2, -128, 0, -64, 0, -128, 2, 127));
    }

    @Test(timeOut = 100)
    public void testGenerateNumbersExclusive_works() {
        final SequencedCollection<Integer> expected = List.of(1, 2, 3);
        final SequencedCollection<Integer> actual = StreamBasics.generateNumbersExclusive(1, 4);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGenerateNumbersInclusive_works() {
        final SequencedCollection<Double> expected = List.of(1.0, 2.0, 3.0, 4.0);
        final SequencedCollection<Double> actual = StreamBasics.generateNumbersInclusive(1, 4);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testConvertToList_works() {
        final SequencedCollection<Integer> actual = StreamBasics.convertToList(COLLECTION);
        assertEquals(actual, COLLECTION);
    }

    @Test(timeOut = 100)
    public void testGetDistinctValues_works() {
        final SequencedCollection<Integer> expected = List.of(63, 2, -128, 0, -64, 127);
        final SequencedCollection<Integer> actual = StreamBasics.getDistinctValues(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testConvertToUnmodifiableSet_works() {
        final Collection<Integer> expected = Set.of(63, 2, -128, 0, -64, 127);
        final Collection<Integer> actual = StreamBasics.convertToUnmodifiableSet(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFindMaxValue_returnsMaxValue() {
        final Optional<Integer> expected = Optional.of(127);
        final Optional<Integer> actual = StreamBasics.findMaxValue(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFindMinValue_returnsMinValue() {
        final Optional<Integer> expected = Optional.of(-128);
        final Optional<Integer> actual = StreamBasics.findMinValue(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFilterNonNegativeValues_returnsFilteredValues() {
        final SequencedCollection<Integer> expected = List.of(63, 2, 2, 0, 0, 2, 127);
        final SequencedCollection<Integer> actual = StreamBasics.filterNonNegativeValues(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFindAnyNegativeNumber_returnsFoundNumber() {
        final List<Optional<Integer>> expectedIn = List.of(Optional.of(-128), Optional.of(-64));
        final Optional<Integer> actual = StreamBasics.findAnyNegativeNumber(COLLECTION);
        assertListContainsObject(expectedIn, actual, "Expected to find any negative number");
    }

    @Test(timeOut = 100)
    public void testFindAnyNegativeNumber_returnsEmptyOptional() {
        final Optional<Integer> expected = Optional.empty();
        final Optional<Integer> actual = StreamBasics.findAnyNegativeNumber(List.of());
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFindAnyNegativeNumberOrElse_returnsFoundNumber() {
        final List<Integer> expectedIn = List.of(-128, -64);
        final int actual = StreamBasics.findAnyNegativeNumberOrElse(COLLECTION);
        assertListContainsObject(expectedIn, actual, "Expected to find any negative number");
    }

    @Test(timeOut = 100)
    public void testFindAnyNegativeNumberOrElseNotFound_returnsDefaultValue() {
        final int expected = Integer.MAX_VALUE;
        final int actual = StreamBasics.findAnyNegativeNumberOrElse(List.of(1, 2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFindFirstNegativeNumberOrElseGet_returnsFoundNumber() {
        final int expected = -128;
        final int actual = StreamBasics.findFirstNegativeNumberOrElseGet(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFindFirstNegativeNumberOrElseGet_returnsDefaultValue() {
        final int expected = Integer.MAX_VALUE;
        final int actual = StreamBasics.findFirstNegativeNumberOrElseGet(List.of(1, 2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testConvertToStringList_works() {
        final SequencedCollection<String> expected = List.of("63", "2", "2", "-128", "0", "-64", "0", "-128", "2",
                "127");
        final SequencedCollection<String> actual = StreamBasics.convertToStringList(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testConvertPositivesToSingleString_works() {
        final String expected = "String[63, 2, 2, 2, 127]";
        final String actual = StreamBasics.convertPositivesToSingleString(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGetLimitedList_works() {
        final SequencedCollection<Integer> expected = List.of(63, 2, 2, -128);
        final SequencedCollection<Integer> actual = StreamBasics.getLimitedList(COLLECTION, 4);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGetLimitedList_returnsWholeList() {
        final SequencedCollection<Integer> actual = StreamBasics.getLimitedList(COLLECTION, 20);
        assertEquals(actual, COLLECTION);
    }

    @Test(timeOut = 100)
    public void testGetListFromIndex_works() {
        final SequencedCollection<Integer> expected = List.of(0, -64, 0, -128, 2, 127);
        final SequencedCollection<Integer> actual = StreamBasics.getListFromIndex(COLLECTION, 4);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGetListFromIndex_returnsEmptyList() {
        final SequencedCollection<Integer> expected = List.of();
        final SequencedCollection<Integer> actual = StreamBasics.getListFromIndex(COLLECTION, 20);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGetSquaredValues_works() {
        final SequencedCollection<Double> expected = List.of(3969.0, 4.0, 4.0, 16384.0, 0.0, 4096.0, 0.0, 16384.0, 4.0,
                16129.0);
        final SequencedCollection<Double> actual = StreamBasics.getSquaredValues(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testDivideValues_works() {
        final double[] expected = {31.5, 1.0, 1.0, -64.0, 0.0, -32.0, 0.0, -64.0, 1.0, 63.5};
        final double[] actual = StreamBasics.divideValues(COLLECTION, 2.0);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testDivideValues_throws() {
        assertThrows(IllegalArgumentException.class, () -> StreamBasics.divideValues(COLLECTION, 0.0));
    }

    @Test(timeOut = 1000)
    public void testCountValues_works() {
        final long expected = 10L;
        final long actual = StreamBasics.countValues(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 1000)
    public void testCountValuesUsingForEach_calculatesCorrectly() {
        final int expected = 10_000_000;
        final SequencedCollection<Integer> collection = IntStream.range(0, expected).boxed().toList();
        final int actual = StreamBasics.countValuesUsingForEach(collection);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 1000)
    public void testCountValuesUsingForEachOrdered_calculatesCorrectly() {
        final int expected = 10_000_000;
        final SequencedCollection<Integer> collection = IntStream.range(0, expected).boxed().toList();
        final int actual = StreamBasics.countValuesUsingForEachOrdered(collection);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 1000)
    public void testCountValuesUsingParallelForEach_calculatesIncorrectly() {
        final int expected = 10_000_000;
        final SequencedCollection<Integer> collection = IntStream.range(0, expected).boxed().toList();
        final int actual = StreamBasics.countValuesUsingParallelForEach(collection);
        assertNotEquals(actual, expected);
    }

    @Test(timeOut = 1000)
    public void testCountValuesUsingParallelForEachOrdered_calculatesCorrectly() {
        final int expected = 10_000_000;
        final SequencedCollection<Integer> collection = IntStream.range(0, expected).boxed().toList();
        final int actual = StreamBasics.countValuesUsingParallelForEachOrdered(collection);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testCalculateSumUsingReduce_calculates() {
        final Optional<Integer> expected = Optional.of(-124);
        final Optional<Integer> actual = StreamBasics.calculateSumUsingReduce(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testCalculateSumUsingIntMap_calculates() {
        final Number expected = -124;
        final Number actual = StreamBasics.calculateSumUsingIntMap(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testCalculateAverageUsingIntMap_calculates() {
        final OptionalDouble expected = OptionalDouble.of(-12.4);
        final OptionalDouble actual = StreamBasics.calculateAverageUsingIntMap(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testCalculateAverageUsingCollect_calculates() {
        final double expected = -12.4;
        final double actual = StreamBasics.calculateAverageUsingCollect(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGenerateEvenIntegers_generatesEvenNumbersWithinBounds() {
        final SequencedCollection<Integer> expected = List.of(2, 4, 6, 8, 10);
        final SequencedCollection<Integer> actual = StreamBasics.generateEvenIntegers(2, 5);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testGenerateEvenIntegers_throws() {
        assertThrows(IllegalArgumentException.class, () -> StreamBasics.generateEvenIntegers(3, 5));
    }

    @Test(timeOut = 100)
    public void testGenerateRandomIntegers_generatesRandomNumbersWithinBounds() {
        final int expectedSize = 10;
        final Collection<Integer> actual = StreamBasics.generateRandomIntegers(-2, 2, expectedSize);
        assertEquals(actual.size(), expectedSize);

        final Predicate<Integer> predicate = i -> i < -2 || i > 2;
        assertFalse(actual.stream().anyMatch(predicate));
    }

    @Test(timeOut = 100)
    public void testGetRandomIntegers_generatesRandomNumbersWithinBounds() {
        final int expectedSize = 10;
        final Collection<Integer> actual = StreamBasics.getRandomIntegers(-2, 2, expectedSize);
        assertEquals(actual.size(), expectedSize);

        final Predicate<Integer> predicate = i -> i < -2 || i > 2;
        assertFalse(actual.stream().anyMatch(predicate));
    }

    @Test(timeOut = 100)
    public void testGetSelfDividableNumbersOrNull_works() {
        final SequencedCollection<Integer> expected = Arrays.asList(63, 2, 2, -128, null, -64, null, -128, 2, 127);
        final SequencedCollection<Integer> actual = StreamBasics.getSelfDividableNumbersOrNull(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testFflattenCollections_flattens() {
        final SequencedCollection<Integer> expected = List.of(127, 2, -128, 0, -64, 0, -128, 2, 2, 63, 63, 2,2, -128,
                0, -64, 0, -128, 2, 127);
        final SequencedCollection<Integer> actual = StreamBasics.flattenCollections(List.of(COLLECTION.reversed(),
                COLLECTION));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testSortInNaturalOrder_sortsInNaturalOrder() {
        final SequencedCollection<Integer> expected = List.of(-128, -128, -64, 0, 0, 2, 2, 2, 63, 127);
        final SequencedCollection<Integer> actual = StreamBasics.sortInNaturalOrder(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testSortInReverseOrder_sortsInReverseOrder() {
        final SequencedCollection<Integer> expected = List.of(127, 63, 2, 2, 2, 0, 0, -64, -128, -128);
        final SequencedCollection<Integer> actual = StreamBasics.sortInReverseOrder(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testPartitionByEvenness_partitionsByEvenness() {
        final Map<Boolean, List<Integer>> expected = Map.of(true, List.of(2, 2, -128, 0, -64, 0, -128, 2), false,
                List.of(63, 127));
        final Map<Boolean, List<Integer>> actual = StreamBasics.partitionByEvenness(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testgGroupByNumberSign_groupsByNumberSign() {
        final Map<String, List<Integer>> expected = Map.of("positive", List.of(63, 2, 2, 2, 127), "negative",
                List.of(-128, -64, -128), "zero", List.of(0, 0));
        final Map<String, List<Integer>> actual = StreamBasics.groupByNumberSign(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testMapWithMergedValues_mergesAsExpected() {
        final Map<Integer, Integer> expected = Map.of(0, 1, 2, 27, 127, 128, -64, -63, -128, 16129, 63, 64);
        final Map<Integer, Integer> actual = StreamBasics.mapWithMergedValues(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testContainsAnyNegative_works() {
        final boolean expected = true;
        final boolean actual = StreamBasics.containsAnyNegative(List.of(1, -2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testContainsAnyNegative_returnsFalse_emptyList() {
        final boolean expected = false;
        final boolean actual = StreamBasics.containsAnyNegative(List.of());
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testContainsAnyNegative_returnsFalse_noNegatives() {
        final boolean expected = false;
        final boolean actual = StreamBasics.containsAnyNegative(List.of(0, 1, 2));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testAreAllPositive_works() {
        final boolean expected = true;
        final boolean actual = StreamBasics.areAllPositive(List.of(1, 2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testAreAllPositive_returnsTrue_emptyList() {
        final boolean expected = true;
        final boolean actual = StreamBasics.areAllPositive(List.of());
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testAreAllPositive_returnsFalse_containsNegative() {
        final boolean expected = false;
        final boolean actual = StreamBasics.areAllPositive(List.of(1, -2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testContainsNoZero_works() {
        final boolean expected = true;
        final boolean actual = StreamBasics.containsNoZero(List.of(1, 2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testContainsNoZero_returnsTrue_emptyList() {
        final boolean expected = true;
        final boolean actual = StreamBasics.containsNoZero(List.of());
        assertEquals(actual, expected);
    }


    @Test(timeOut = 100)
    public void testContainsNoZero_returnsFalse_containsZero() {
        final boolean expected = false;
        final boolean actual = StreamBasics.containsNoZero(List.of(1, 0, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testConcatenateCollections_concatenates() {
        final SequencedCollection<Integer> expected = List.of(6, 5, 4, 1, 2, 3);
        final SequencedCollection<Integer> actual = StreamBasics.concatenateCollections(List.of(6, 5, 4),
                List.of(1, 2, 3));
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testTakeWhileNumberIsPositive_works() {
        final SequencedCollection<Integer> expected = List.of(63, 2, 2);
        final SequencedCollection<Integer> actual = StreamBasics.takeWhileNumberIsPositive(COLLECTION);
        assertEquals(actual, expected);
    }

    @Test(timeOut = 100)
    public void testDropWhileNumberIsPositive_works() {
        final SequencedCollection<Integer> expected = List.of(-128, 0, -64, 0, -128, 2, 127);
        final SequencedCollection<Integer> actual = StreamBasics.dropWhileNumberIsPositive(COLLECTION);
        assertEquals(actual, expected);
    }
}
