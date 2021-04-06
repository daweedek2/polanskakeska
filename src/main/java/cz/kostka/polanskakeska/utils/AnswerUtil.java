package cz.kostka.polanskakeska.utils;

import java.text.Normalizer;

public class AnswerUtil {

    public static String normalize(final String answer) {
        return Normalizer
                .normalize(answer, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
