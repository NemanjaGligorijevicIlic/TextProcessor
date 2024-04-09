package com.example.hiqtextprocessor.ContentTypeEnum;

public enum ContentType {
    RTF("application/rtf"),
    MD("text/markdown"),
    TXT("text/plain"),
    OCTET_STREAM("application/octet-stream");

    private final String type;
    ContentType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
