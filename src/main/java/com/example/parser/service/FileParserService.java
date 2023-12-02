package com.example.parser.service;

import com.example.parser.domain.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

@Service
public class FileParserService {

    public Chapter parseFile(MultipartFile file) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String name = reader.readLine();
        String firstChapter = reader.readLine();
        Chapter result = new Chapter(0, name, new LinkedList<>(), new LinkedList<>());
        result.setSubchapters(parseChapter(reader, 1,
                firstChapter.substring(countSymbol(firstChapter), firstChapter.length() - 1), countSymbol(firstChapter)));
        return result;
    }

    String line;
    int flag = 1;
    private List<Chapter> parseChapter(BufferedReader reader, int index, String name, int level) throws IOException {
        Chapter result = new Chapter(index, name, new LinkedList<>(), new LinkedList<>());
        List<Chapter> subchapters = new LinkedList<>();
        while (true) {
            if(flag == 1) {
                if ((line = reader.readLine()) == null) {
                    subchapters.add(result);
                    return subchapters;
                }
            }
            int currentLevel = countSymbol(line);
            if (currentLevel > 0) {
                if(level < currentLevel) {
                    result.setSubchapters(parseChapter(reader, 1, line.substring(currentLevel, line.length() - 1), currentLevel));
                }
                if(level == currentLevel){
                    subchapters.add(result);
                    index ++;
                    name = line.substring(currentLevel, line.length() - 1);
                    flag = 1;
                    result = new Chapter(index, name, new LinkedList<>(), new LinkedList<>());
                }
                if(level > currentLevel){
                    subchapters.add(result);
                    flag = 0;
                    return subchapters;
                }
            } else {
                List<String> strings = result.getStrings();
                strings.add(line);
                result.setStrings(strings);
            }
        }
    }

    private int countSymbol(String line) {
        int count = 0;
        for (char c : line.toCharArray()) {
            if (c == '#') {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
