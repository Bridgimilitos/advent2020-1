package four;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PassportValidatorTest
{
    private final Map<String, String> passport = Map.of("ecl", "gry",
                                                        "pid", "860033327",
                                                        "eyr", "2020",
                                                        "hcl", "#fffffd",
                                                        "byr", "1937",
                                                        "iyr", "2017",
                                                        "cid", "147",
                                                        "hgt", "183cm");

    private final Map<String, String> passport2 = Map.of("ecl", "grn",
                                                        "pid", "087499704",
                                                        "eyr", "2030",
                                                        "hcl", "#623a2f",
                                                        "byr", "1980",
                                                        "iyr", "2012",
                                                        "hgt", "74in");

    @Test
    void passportHasValidFields()
    {
//        byr (Birth Year) - four digits; at least 1920 and at most 2002.
//        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
//        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
//        hgt (Height) - a number followed by either cm or in:
//          If cm, the number must be at least 150 and at most 193.
//          If in, the number must be at least 59 and at most 76.
//        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
//        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
//        pid (Passport ID) - a nine-digit number, including leading zeroes.
//        cid (Country ID) - ignored, missing or not.

        //arrange
        //ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm
        //act/assert
        assertTrue(PassportValidator.validate(passport));
    }

    @Test
    void parsePassport()
    {
        assertEquals(passport, PassportValidator.parsePassport("ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\n" +
                                                                   "byr:1937 iyr:2017 cid:147 hgt:183cm"));
    }

    @Test
    void parsePassportBatch() throws IOException
    {
        assertTrue(PassportValidator.parseBatch("resource/input4test").contains(passport));
    }

    @Test
    void countValidPassports() throws IOException
    {
        assertEquals(2, PassportValidator.parseBatch("resource/input4test").stream()
            .filter(PassportValidator::validate)
            .count());
    }

    @Test
    void countValidPassportsPart2() throws IOException
    {
        assertEquals(4, PassportValidator.parseBatch("resource/input4test2").stream()
                            .filter(PassportValidator::validatePart2)
                            .count());
    }

    @Test
    void passportHasValidFieldsPart2()
    {
//        byr (Birth Year) - four digits; at least 1920 and at most 2002.
//        iyr (Issue Year) - four digits; at least 2010 and at most 2020.
//        eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
//        hgt (Height) - a number followed by either cm or in:
//          If cm, the number must be at least 150 and at most 193.
//          If in, the number must be at least 59 and at most 76.
//        hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
//        ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
//        pid (Passport ID) - a nine-digit number, including leading zeroes.
//        cid (Country ID) - ignored, missing or not.

        //arrange
        //ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm
        //act/assert
        assertTrue(PassportValidator.validatePart2(passport));
    }
}
