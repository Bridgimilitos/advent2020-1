package one;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Sum2020
{
    private final List<Integer> input;

    public Sum2020(List<Integer> input)
    {
        this.input = input;
    }

    public Sum2020(String inputFile) throws IOException
    {
        //URL url = new URL(urlPath);
        input = new ArrayList<>();
        Files.lines(Paths.get(inputFile)).forEach(line -> input.add(Integer.parseInt(line)));
    }

    public Long multiply2020Entries()
    {
        for (int i: input)
        {
            for (int j: input)
            {
                if (i + j == 2020)
                {
                    return (long)i * (long)j;
                }
            }
        }

        return null;
    }

    public static void main(String[] args)
    {
        try
        {
            Sum2020 sum = new Sum2020("./input1");
            System.out.println("2 entries: " + sum.multiply2020Entries());
            System.out.println("3 entries: " + sum.multiply2020ThreeEntries());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Integer> getInput()
    {
        return input;
    }

    public Long multiply2020ThreeEntries()
    {
        for (int i: input)
        {
            for (int j: input)
            {
                for (int k: input)
                {
                    if (i + j + k == 2020)
                    {
                        return (long)i * (long)j * (long)k;
                    }
                }
            }
        }
        return null;
    }
}
