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

    public static void main(String[] args)
    {
        try
        {
            System.out.println(PassportValidator.parseBatch("resource/input4").stream()
                .filter(PassportValidator::validate)
                .count()); 
            
            System.out.println(PassportValidator.parseBatch("resource/input4").stream()
                .filter(PassportValidator::validatePart2)
                .count());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    static boolean validatePart2(Map<String, String> passport)
    {
        if (!validate(passport))
        {
            return false;
        }

        return validateBirthYear(passport.get("byr"))
            && validateIssueYear(passport.get("iyr"))
            && validateExpirationYear(passport.get("eyr")) 
            && validateHeight(passport.get("hgt"))
            && validateHairColor(passport.get("hcl"))
            && validateEyeColor(passport.get("ecl"))
            && validatePassportId(passport.get("pid"));
    }

    private static boolean validatePassportId(String pid)
    {
        //        pid (Passport ID) - a nine-digit number, including leading zeroes.
        return pid.chars().filter(Character::isDigit).count() == 9;
    }

    private static boolean validateEyeColor(String ecl)
    {
        //        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
        return List.of("amb","blu","brn","gry","grn","hzl","oth").contains(ecl);
    }

    private static boolean validateHairColor(String hcl)
    {
        //        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
        return hcl.matches("#([0-9a-f]{6})");
    }

    private static boolean validateHeight(String hgt)
    {
        //        hgt (Height) - a number followed by either cm or in:
        //          If cm, the number must be at least 150 and at most 193.
        //          If in, the number must be at least 59 and at most 76.
        if (hgt.endsWith("cm"))
        {
            int cm = Integer.parseInt(hgt.replaceAll("cm", ""));
            return cm >= 150 && cm <= 193;
        }
        else if (hgt.endsWith("in"))
        {
            int in = Integer.parseInt(hgt.replaceAll("in", ""));
            return in >= 59 && in <= 76;
        }
        else
        {
            return false;
        }
    }

    private static boolean validateExpirationYear(String eyr)
    {
        //        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
        int year = Integer.parseInt(eyr);
        return year >= 2020 && year <= 2030;
    }

    private static boolean validateIssueYear(String iyr)
    {
        //        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
        int year = Integer.parseInt(iyr);
        return year >= 2010 && year <= 2020;
    }

    private static boolean validateBirthYear(String byr)
    {
        //        byr (Birth Year) - four digits; at least 1920 and at most 2002.
        int year = Integer.parseInt(byr);
        return year >= 1920 && year <= 2002;
    }
}
