package two;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest
{
    @Test
    void checkPasswordsMatchPolicy()
    {
//        1-3 a: abcde
//        1-3 b: cdefg
//        2-9 c: ccccccccc
        assertAll(
            () -> assertTrue(PasswordValidator.valid(1, 3, 'a', "abcde")),
            () -> assertFalse(PasswordValidator.valid(1, 3, 'b', "cdefg")),
            () -> assertTrue(PasswordValidator.valid(2, 9, 'c', "ccccccccc")));
    }

    @Test
    void parseInputLineAndCheckMatch()
    {
        //"1-3 a: abcde"
        //"1-3 b: cdefg"
        //"2-9 c: ccccccccc"
        //act
        assertAll(
            () -> assertTrue(PasswordValidator.parseAndValid("1-3 a: abcde")),
            () -> assertFalse(PasswordValidator.parseAndValid("1-3 b: cdefg")),
            () -> assertTrue(PasswordValidator.parseAndValid("2-9 c: ccccccccc")));
    }

    @Test
    void parseInputFileAndCheckNumberMatch() throws IOException
    {
        assertEquals(2L, PasswordValidator.parseInputFileAndGetCountValid("resource/input2test"));
    }

    @Test
    void checkPasswordsMatchPolicyPart2()
    {
//        1-3 a: abcde
//        1-3 b: cdefg
//        2-9 c: ccccccccc
        assertAll(
            () -> assertTrue(PasswordValidator.validPart2(1, 3, 'a', "abcde")),
            () -> assertFalse(PasswordValidator.validPart2(1, 3, 'b', "cdefg")),
            () -> assertFalse(PasswordValidator.validPart2(2, 9, 'c', "ccccccccc")));
    }

    @Test
    void parseInputFileAndCheckNumberMatchPart2() throws IOException
    {
        assertEquals(1L, PasswordValidator.parseInputFileAndGetCountValidPart2("resource/input2test"));
    }
}
