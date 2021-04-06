package ru.strings;

public class TextFixer {

    public String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public String fixText(String text) {
        return
                capitalize(
                        text
                                .trim()
                                .replace("-", "—")
                                .replace("“", "«")
                                .replace("”", "»")
                                .replace("ситх", "стих")
                );
    }

    public static void main(String[] args) {

        String text = "    свободный ситх, верли́бр (фр. vers libre) - в разной степени свободный от жёсткой рифмометрической " +
                "композиции ситх, занявший довольно широкую нишу в западной, в частности - англоязычной, поэзии XX века. " +
                "Это тип стихосложения, для которого характерен последовательный отказ от всех “вторичных признаков” " +
                "стиховой речи: рифмы, слогового метра, изотонии и изосиллабизма (равенства строк по числу ударений или " +
                "слогов) и регулярной строфики.    ";

        var textFixer = new TextFixer();
        var fixedText = textFixer.fixText(text);
        System.out.println(fixedText);
    }
}
