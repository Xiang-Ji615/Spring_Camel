package com.spring.camel.service;

import com.spring.camel.Application;
import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class TestFilter implements GenericFileFilter {

    Set<String> fileNames = new HashSet<>();

    @Override
    public boolean accept(GenericFile file) {
        return fileNames.contains(file.getFileName());
    }

    public void setFileNames(Set<String> fileNames){
        this.fileNames = fileNames;
    }
}
