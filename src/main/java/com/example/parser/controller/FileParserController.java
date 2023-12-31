package com.example.parser.controller;

import com.example.parser.domain.Chapter;
import com.example.parser.service.FileParserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.StringWriter;

/**
 * Класс-контроллер для обработки запросов
 */
@RestController
public class FileParserController {

    @Autowired
    /** Поле объект класса-сервиса */
    private FileParserService parserService;

    /**
     * Функция для обработки запроса на парсинг файла
     * @param file - входящий файл
     * @return JSON с результатом
     */
    @PostMapping(value = "/parseFile", produces = MediaType.APPLICATION_JSON_VALUE)
    public String parseFile(@RequestParam("file") MultipartFile file) throws Exception {
        Chapter result = parserService.parseFile(file);
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        mapper.writeValue(writer, result);
        return writer.toString();
    }


}
