package two;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PasswordValidator
{
    static boolean valid(int min, int max, char c, String pwd)
    {
        long count = pwd.chars().filter(s -> s == c).count();
        return count >= min && count <= max;
    }

    static boolean parseAndValid(String line)
    {
        List<String> split = List.of(line.split(" "));
        int min = Integer.parseInt(split.get(0).split("-")[0]);
        int max = Integer.parseInt(split.get(0).split("-")[1]);
        char c = split.get(1).charAt(0);
        String pwd = split.get(2);

        return valid(min, max, c, pwd);
    }

    static long parseInputFileAndGetCountValid(String file) throws IOException
    {
        return Files.lines(Paths.get(file))
                   .map(PasswordValidator::parseAndValid)
                   .filter(valid -> valid)
                   .count();
    }

    public static void main(String[] args)
    {
        try
        {
            System.out.println(PasswordValidator.parseInputFileAndGetCountValid("resource/input2"));
            System.out.println(PasswordValidator.parseInputFileAndGetCountValidPart2("resource/input2"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static boolean validPart2(int pos1, int pos2, char c, String pwd)
    {
        return pwd.charAt(pos1 - 1) == c ^ pwd.charAt(pos2 - 1) == c;
    }

    public static long parseInputFileAndGetCountValidPart2(String file) throws IOException
    {
        return Files.lines(Paths.get(file))
                   .map(PasswordValidator::parseAndValidPart2)
                   .filter(valid -> valid)
                   .count();
    }

    static boolean parseAndValidPart2(String line)
    {
        List<String> split = List.of(line.split(" "));
        int min = Integer.parseInt(split.get(0).split("-")[0]);
        int max = Integer.parseInt(split.get(0).split("-")[1]);
        char c = split.get(1).charAt(0);
        String pwd = split.get(2);

        return validPart2(min, max, c, pwd);
    }
}
