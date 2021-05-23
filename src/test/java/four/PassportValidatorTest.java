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

    @Test
    void passportHasValidFields()
    {
//        byr (Birth Year)
//        iyr (Issue Year)
//        eyr (Expiration Year)
//        hgt (Height)
//        hcl (Hair Color)
//        ecl (Eye Color)
//        pid (Passport ID)
//        cid (Country ID) Optional - no point in checking.

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
}
