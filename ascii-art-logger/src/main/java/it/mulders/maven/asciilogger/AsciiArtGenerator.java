package it.mulders.maven.asciilogger;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import org.codehaus.plexus.component.annotations.Component;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * Simple logger that
 * @see <a href="https://www.baeldung.com/ascii-art-in-java">ASCII Art in Java by Chris Oberle at Baeldung</a>
 * @author Maarten Mulders
 */
@Component(role = AsciiArtGenerator.class, hint = "ascii-art-generator")
public class AsciiArtGenerator {
    private static final int HEIGHT = 20;
    private static final int WIDTH = 150;
    private static final char ART_CHAR = '$';

    /**
     * Generate ASCII art based on a String of input.
     * @param input The text to convert into ASCII art.
     * @return The lines of output for the ASCII art.
     */
    public List<String> generate(final String input) {
        final BufferedImage image = createImageBuffer();
        final Graphics2D graphics = createGraphicsObject(image.getGraphics());
        drawText(graphics, input);

        // Iterate over all lines of pixels
        return IntStream.range(0, HEIGHT)
                // Within the line, iterate over all pixels
                .mapToObj(lineIdx -> generateLine(image, lineIdx))
                .collect(toList());
    }

    private String generateLine(final BufferedImage image, final int lineIdx) {
        return IntStream.range(0, WIDTH)
                .map(columnIdx -> extractPixel(image, lineIdx, columnIdx))
                .mapToObj(this::generatePixelChar)
                .collect(joining());
    }

    private int extractPixel(final BufferedImage image, final int lineIdx, final int columnIdx) {
        return image.getRGB(columnIdx, lineIdx);
    }

    private String generatePixelChar(final int character) {
        return character == -16777216 ? " " : String.valueOf(ART_CHAR);
    }

    private void drawText(final Graphics2D graphics, String input) {
        graphics.drawString(input, 0, HEIGHT);
    }

    private BufferedImage createImageBuffer() {
        return new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D createGraphicsObject(final Graphics graphics) {
        graphics.setFont(new Font("Courier New", Font.PLAIN, 24));
        return (Graphics2D) graphics;
    }
}
