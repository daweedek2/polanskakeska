package cz.kostka.polanskakeska.utils;

import cz.kostka.polanskakeska.dto.ResultDTO;
import cz.kostka.polanskakeska.entity.Cache;

public class CacheResultUtil {
    private static final String EMPTY_STRING = "";

    private CacheResultUtil() {
        throw new IllegalStateException("Utility class cannot be instantiated");
    }

    public static ResultDTO createFailedResult(final String message) {
        return new ResultDTO(false, message, EMPTY_STRING);
    }

    public static ResultDTO createSuccessfulResult(final String message, final Cache cache) {
        return new ResultDTO(true, message, cache.getCode());
    }
}
