package four;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class PassportValidator
{
    private static final List<String> MANDATORY = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

    static boolean validate(Map<String, String> passport)
    {
        return passport.keySet().containsAll(MANDATORY);
    }

    static Map<String, String> parsePassport(String singlePassport)
    {
        Map<String, String> passport = new HashMap<>();
        Scanner s = new Scanner(singlePassport);
        while (s.hasNext())
        {
            String line = s.nextLine();
            if (!line.isBlank())
            {
                for (var keyVal : line.split(" "))
                {
                    String[] split = keyVal.split(":");
                    var key = split[0];
                    var val = split[1];
                    passport.put(key, val);
                }
            }
        }

        return passport;
    }

    static List<Map<String, String>> parseBatch(String inputFile) throws IOException
    {
        return Arrays.stream(Files.readString(Path.of(inputFile)).split("\r\n\r\n"))
                   .map(PassportValidator::parsePassport)
                   .collect(Collectors.toList());
    }

    static void main(String[] args)
    {
        try
        {
            System.out.println(PassportValidator.parseBatch("resource/input4").stream()
                .filter(PassportValidator::validate)
                .count());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
