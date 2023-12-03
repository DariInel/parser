package com.example.parser.domain;

import lombok.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс для формирования объекта главы
 */
public class Chapter {

    /** Поле id (номер главы относительно своего уровня вложенности, название текста имеет id=0) */
    private int id;
    /** Поле название главы */
    private String name;
    /** Поле список строк главы */
    private List<String> strings = new LinkedList<>();
    /** Поле список подглав главы */
    private List<Chapter> subchapters = new LinkedList<>();

    /**
     * Функция получения значения поля 'name'
     * @return название главы
     */
    public String getName() {
        return name;
    }

    /**
     * Процедура определения названия главы
     * @param name - название главы
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Функция получения значения поля 'strings'
     * @return список строк главы
     */
    public List<String> getStrings() {
        return strings;
    }

    /**
     * Процедура определения списка строк главы
     * @param strings - список строк главы
     */
    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    /**
     * Функция получения значения поля 'id'
     * @return номер главы относительно её вложенности в файле
     */
    public int getId() {
        return id;
    }

    /**
     * Процедура определения номера главы относительно её вложенности в файле
     * @param id - номер главы относительно её вложенности
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Функция получения значения поля 'subchapters'
     * @return список подглав главы
     */
    public List<Chapter> getSubchapters() {
        return subchapters;
    }

    /**
     * Процедура определения списка подглав главы
     * @param subchapters - список подглав главы
     */
    public void setSubchapters(List<Chapter> subchapters) {
        this.subchapters = subchapters;
    }

    /**
     * Конструктор - создание нового объекта Chapter с определенными значениями
     * @param id - номер главы относительно её вложенности
     * @param name - название главы
     * @param strings - список строк главы
     * @param subchapters - список подглав главы
     */
    public Chapter (int id, String name, List<String> strings, List<Chapter> subchapters){
        this.id = id;
        this.name = name;
        this.strings = strings;
        this.subchapters = subchapters;
    }
}
