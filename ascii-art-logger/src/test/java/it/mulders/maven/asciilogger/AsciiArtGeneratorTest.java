package it.mulders.maven.asciilogger;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AsciiArtGeneratorTest {
    private AsciiArtGenerator generator = new AsciiArtGenerator();

    private List<String> readExpectedOutput(final String filename) throws IOException {
        final List<String> expectedLines = new ArrayList<>();
        try (final InputStream input = getClass().getResourceAsStream(filename);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                expectedLines.add(line);
            }
        }
        return expectedLines;
    }

    @Test
    void print_hello() throws IOException {
        // Arrange
        final List<String> expectedLines = readExpectedOutput("/hello.txt");
        final String expectedOutput = String.join(System.getProperty("line.separator"), expectedLines);

        // Act
        final List<String> lines = generator.generate("Hello");
        final String actualOutput = String.join(System.getProperty("line.separator"), lines).replace(' ', '.');

        // if you need to re-generate the txt file, use these lines:
//        System.out.println("-");
//        lines.forEach(line -> System.out.println(line.replace(' ', '.')));
//        System.out.println("-");

        // Assert
        assertThat(actualOutput).isEqualTo(expectedOutput);
    }
}
