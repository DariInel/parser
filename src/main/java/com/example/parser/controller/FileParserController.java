package com.example.parser.controller;

import com.example.parser.domain.Chapter;
import com.example.parser.service.FileParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileParserController {

    @Autowired
    private FileParserService parserService;
    @PostMapping(value = "/parseFile")
            //, produces = MediaType.APPLICATION_JSON_VALUE
    public Chapter parseFile(@RequestParam("file") MultipartFile file) throws Exception {
        Chapter result = parserService.parseFile(file);
        return result;
    }


}
