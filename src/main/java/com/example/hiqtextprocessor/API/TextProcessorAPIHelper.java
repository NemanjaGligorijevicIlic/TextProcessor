package com.example.hiqtextprocessor.API;

import com.example.hiqtextprocessor.ContentTypeEnum.ContentType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public class TextProcessorAPIHelper {
    public boolean isFileEmpty(MultipartFile file){
        return file.isEmpty();
    }

    public boolean isAllowedContentType(MultipartFile file) { // More content type can be added, if necessary.
        return Objects.equals(file.getContentType(), ContentType.MD.getType())
                || Objects.equals(file.getContentType(), ContentType.RTF.getType())
                || Objects.equals(file.getContentType(), ContentType.TXT.getType())
                || Objects.equals(file.getContentType(), ContentType.OCTET_STREAM.getType());
    }
}
