import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class Sum2020Test
{
    @Test
    public void entriesThatSumTo2020MultiplyToCorrectAnswer()
    {
        //arrange
        List<Integer> input = List.of(1721, 979, 366, 299, 675, 1456);

        //act
        Sum2020 sum2020 = new Sum2020(input);

        //assert
        assertEquals((long)sum2020.multiply2020Entries(), 514579);
    }

    @Test
    public void readInEntriesSuccessfully() throws IOException
    {
        //arrange
        //check first 10 values of https://adventofcode.com/2020/day/1/input
        List<Integer> firstTen = List.of(1779, 1737, 1537, 1167, 1804, 1873, 1894, 1446, 1262, 1608);

        //act
        Sum2020 sum2020 = new Sum2020("./input");

        //assert
        assertArrayEquals(sum2020.getInput().stream()
            .mapToInt(i -> i).limit(10).toArray(), firstTen.stream().mapToInt(i->i).toArray());
    }

    @Test
    public void threeEntriesThatSumTo2020MultiplyToCorrectAnswer()
    {
        //arrange
        List<Integer> input = List.of(1721, 979, 366, 299, 675, 1456);

        //act
        Sum2020 sum2020 = new Sum2020(input);

        //assert
        assertEquals((long)sum2020.multiply2020ThreeEntries(), 241861950);
    }
}
