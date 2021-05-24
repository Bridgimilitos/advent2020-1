package five;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardingPassParserTest
{
    @Test
    void parseColumn()
    {
        //arrange
        String firstPass = "LLL";
        String lastPass = "RRR";
        String seat5Pass = "RLR";

        //act
        int first = BoardingPassParser.parseColumn(firstPass);
        int last = BoardingPassParser.parseColumn(lastPass);
        int seat5 = BoardingPassParser.parseColumn(seat5Pass);

        //assert
        assertAll(
            () -> assertEquals(0, first),
            () -> assertEquals(7, last),
            () -> assertEquals(5, seat5)
        );
    }

    @Test
    void parseRow()
    {
        String f = "F";
        String firstPass = f.repeat(7);
        String b = "B";
        String lastPass = b.repeat(7);
        String row44Pass = "FBFBBFF";
        
        int first = BoardingPassParser.parseRow(firstPass);
        int last = BoardingPassParser.parseRow(lastPass);
        int row44 = BoardingPassParser.parseRow(row44Pass);

        assertAll(
            () -> assertEquals(0, first),
            () -> assertEquals(127, last),
            () -> assertEquals(44, row44)
        );
    }

    @Test
    void parseRowAndColumnAndSeatId()
    {
        //BFFFBBFRRR: row 70, column 7, seat ID 567.
        //FFFBBBFRRR: row 14, column 7, seat ID 119.
        //BBFFBBFRLL: row 102, column 4, seat ID 820.

        String pass1 = "BFFFBBFRRR";
        String pass2 = "FFFBBBFRRR";
        String pass3 = "BBFFBBFRLL";

        int seatId1 = BoardingPassParser.parseBoardingPass(pass1);
        int seatId2 = BoardingPassParser.parseBoardingPass(pass2);
        int seatId3 = BoardingPassParser.parseBoardingPass(pass3);

        assertAll(
            () -> assertEquals(567, seatId1),
            () -> assertEquals(119, seatId2),
            () -> assertEquals(820, seatId3)
        );
    }
}
