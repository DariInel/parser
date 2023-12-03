package com.example.parser.service;

import com.example.parser.domain.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс-сервис для парсинга файла
 */
@Service
public class FileParserService {

    /**
     * Функция для формирования результата
     * @param file - входной файл
     * @return итоговый объект-результат
     */
    public Chapter parseFile(MultipartFile file) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        String name = reader.readLine();
        String firstChapter = reader.readLine();
        Chapter result = new Chapter(0, name, new LinkedList<>(), new LinkedList<>());
        result.setSubchapters(parseChapter(reader, 1,
                firstChapter.substring(countSymbol(firstChapter), firstChapter.length() - 1), countSymbol(firstChapter)));
        return result;
    }
    /** Поле считанная строка*/
    String line;
    /** Поле флаг для разрешения/блокировки считывания новой строки*/
    int flag = 1;

    /**
     * Функция для обработки файла и формирования подглав заданной главы
     * @param reader - поток для чтения строк файла
     * @param index - id/номер главы относительно своего уровня/вложенности
     * @param name - название главы
     * @param level - уровень/вложенность главы
     * @return подглавы определенной главы
     */
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
            //Если встретили начало новой главы
            if (currentLevel > 0) {
                //Если встретили подглаву
                if(level < currentLevel) {
                    //Запускаем рекурсию для получения подглав
                    result.setSubchapters(parseChapter(reader, 1, line.substring(currentLevel, line.length() - 1), currentLevel));
                }
                //Если выстретили главу такого же уровня вложенности
                if(level == currentLevel){
                    //Добавляем текущую главу в список глав как обработанную (т.к у нее не может быть больше подглав)
                    subchapters.add(result);
                    index ++;
                    name = line.substring(currentLevel, line.length() - 1);
                    flag = 1;
                    //Переходим к обратботке новой главы такого же уровня
                    result = new Chapter(index, name, new LinkedList<>(), new LinkedList<>());
                }
                //Если встретили главу по вложенности меньше текущей (т.е. закончили обход подглав для главы на уровень выше)
                if(level > currentLevel){
                    //Добавляем текущую главу в список глав как обработанную (т.к у нее не может быть больше подглав)
                    subchapters.add(result);
                    flag = 0;
                    //Возвращаем подглавы
                    return subchapters;
                }
            }
            //Если встретили просто текст главы
            else {
                //Добавляем строку в лист строк текущей главы
                List<String> strings = result.getStrings();
                strings.add(line);
                result.setStrings(strings);
            }
        }
    }

    /**
     * Функция для подсчета вложенности главы
     * @param line - считанная строка
     * @return колличество символов '#' в начале строки, т.е. определяет вложенность главы
     */
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
