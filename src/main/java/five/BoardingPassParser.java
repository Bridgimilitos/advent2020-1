package five;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardingPassParser
{
    public static int parseColumn(String column)
    {
        //R = upper, L = lower
        //000 = 0, 111 = 7
        //convert R to 1, L to 0, then back to int.

        String binary = column.chars()
                             .map(c -> c == 'R' ? '1' : '0')
                             .mapToObj(Character::toString)
                             .collect(Collectors.joining());

        //column = column.replaceAll("R", "1");
        //column = column.replaceAll("L", "0");

        return Integer.parseInt(binary, 2);
    }

    public static int parseRow(String row)
    {
        String binary = row.chars()
                            .map(c -> c == 'B' ? '1' : '0')
                            .mapToObj(Character::toString)
                            .collect(Collectors.joining());
        return Integer.parseInt(binary, 2);
    }

    public static int parseBoardingPass(String pass)
    {
//        String row = pass.substring(0, 7);
//        String column = pass.substring(7, 10);
//        return parseRow(row) * 8 + parseColumn(column);

        return Integer.parseInt(pass.chars()
                               .map(c -> (c == 'R' || c == 'B') ? '1' : '0')
                               .mapToObj(Character::toString)
                               .collect(Collectors.joining()), 2);
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println(Files.lines(Path.of("resource/input5"))
                                   .mapToInt(BoardingPassParser::parseBoardingPass)
                                   .max().getAsInt());

            List<Integer> seatIds = Files.lines(Path.of("resource/input5"))
                                        .map(BoardingPassParser::parseBoardingPass).collect(Collectors.toList());

            List<Integer> allSeatIds = IntStream.range(0, 1 << 10).boxed().collect(Collectors.toList());
            allSeatIds.removeAll(seatIds);
            System.out.println(allSeatIds);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
