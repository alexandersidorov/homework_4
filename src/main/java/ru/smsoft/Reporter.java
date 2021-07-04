package ru.smsoft;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reporter {

    private final static String TITLE = "Анализ текста ";
    private final static String COUNT_WORDS = "Кол-во слов в тексте: ";
    private final static String COUNT_PUNCT = "Кол-во знаков препинания и др. символов в тексте: ";
    private final static String COUNT_SPACE = "Кол-во пробелов и пр. отступов в тексте: ";
    private final static String COUNT_SEQ = "Встречаемость сочетаний букв: ";

    public static String getReport(String text){

        StringBuilder report = new StringBuilder();
        report.append(TITLE).append("\n");
        addCountOfWordsInReport(text,report);
        addCountOfPunctInReport(text,report);
        addCountOfSpaceInReport(text,report);
        addStrSequenceInReport(text,report);

        return report.toString();
    }



    private static void addCountOfWordsInReport(String text,StringBuilder report){
        List<String> words = getWordsInText(text);
        report.append(COUNT_WORDS)
                .append(words.size())
                .append("\n");
    }

    private static void addCountOfPunctInReport(String text,StringBuilder report){
        int puncts = 0;
        Matcher m;

        Pattern punctPattern = Pattern.compile("\\pP");
        var arrayText = text.strip().split(" +");
        for (var s : arrayText) {
            int count = 0;
            m =  punctPattern.matcher(s);
            while (m.find()) {
                ++count;
            }
            puncts += count;
        }
        report.append(COUNT_PUNCT)
                .append(puncts)
                .append("\n");
    }

    private static void addCountOfSpaceInReport(String text,StringBuilder report){
        int spaces = 0;
        Matcher m;

        Pattern punctPattern = Pattern.compile("\\s");
        int count = 0;
        m =  punctPattern.matcher(text);
        while (m.find()) {
            ++count;
        }
        spaces += count;

        report.append(COUNT_SPACE)
                .append(spaces)
                .append("\n");
    }

    private static void addStrSequenceInReport(String text,StringBuilder report){
        Map<String,Integer> strSeq = new HashMap<>();
        List<String> words = getWordsInText(text);
        for(String word:words){
            Set<String> partsOfString = getPartsOfString(word);
            for(String part:partsOfString){
                if(!strSeq.containsKey(part)){
                    int count = StringUtils.countMatches(text,part);
                    strSeq.put(part,count);
                }
            }
        }

        report.append(COUNT_SEQ).append("\n");
        for(Map.Entry<String,Integer> entry:strSeq.entrySet() ) {
            report.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue().toString())
                    .append("\n");
        }

    }

    private static List<String> getWordsInText(String text){
        List<String> words = new ArrayList<>();

        var arr = text.split("\\pP");
        for (var str:arr){
            if(!StringUtils.isBlank(str)) {
                var arr2 = str.strip().split(" +");
                words.addAll(Arrays.asList(arr2));
            }
        }
        return words;
    }

    private static Set<String> getPartsOfString(String word) {
        Set<String> parts = new HashSet<>();
        for (int i = 0;i<word.length();++i){
            for (int j = i;j<=word.length();++j) {
                String part = word.substring(i, j);
                if(!StringUtils.isBlank(part))
                    parts.add(part);
            }
        }
        return parts;
    }
}
