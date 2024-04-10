package com.example.hiqtextprocessor.TextProcessorTests;

import com.example.hiqtextprocessor.TextProcessor.TextProcessor;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;


public class TextProcessorTests {

    @Test
    void processTextTest(){
        TextProcessor textProcessor = new TextProcessor();
        String expectedResult = "How Many fooGenericbar Chickens Can You Fit Into a fooGenericbar Pontiac?\n";
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                "text/plain",
                "How Many Generic Chickens Can You Fit Into a Generic Pontiac?".getBytes());

        assertEquals(expectedResult,textProcessor.processText(file));
    }
}
