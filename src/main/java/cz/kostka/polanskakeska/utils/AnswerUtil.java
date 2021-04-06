package cz.kostka.polanskakeska.utils;

import java.text.Normalizer;

public class AnswerUtil {

    private AnswerUtil() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static String normalize(final String answer) {
        return Normalizer
                .normalize(answer, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }
}
