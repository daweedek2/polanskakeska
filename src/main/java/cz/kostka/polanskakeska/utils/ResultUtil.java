package cz.kostka.polanskakeska.utils;

import cz.kostka.polanskakeska.dto.ResultDTO;
import cz.kostka.polanskakeska.entity.Cache;

public class ResultUtil {
    private static final String EMPTY_STRING = "";

    private ResultUtil() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static ResultDTO createFailedResult(final String message) {
        return new ResultDTO(false, message, EMPTY_STRING, 0);
    }

    public static ResultDTO createSuccessfulResult(final String message, final Cache cache) {
        return new ResultDTO(true, message, cache.getCode(), cache.getNumber());
    }
}
