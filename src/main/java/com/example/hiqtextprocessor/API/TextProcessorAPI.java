package com.example.hiqtextprocessor.API;

import com.example.hiqtextprocessor.TextProcessor.TextProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class TextProcessorAPI {
    TextProcessorAPIHelper helper = new TextProcessorAPIHelper();
    TextProcessor processor = new TextProcessor();

    @GetMapping("/fetchFile")
    public String processFile(@RequestParam("file") MultipartFile file) {
        if (helper.isFileEmpty(file)) {
            return "Received empty file!";
        }

        if (!helper.isAllowedContentType(file)) {
            return "Not supported file format! Try with MD, TXT or RTF!";
        }

        return processor.processText(file);
    }
}
