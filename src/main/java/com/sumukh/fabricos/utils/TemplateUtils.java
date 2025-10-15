package com.sumukh.fabricos.utils;

public class TemplateUtils {
    
    public static String replaceNamePlaceholder(String template, String name) {
        return template.replaceAll("\\{\\{name}}", name);
    }
}