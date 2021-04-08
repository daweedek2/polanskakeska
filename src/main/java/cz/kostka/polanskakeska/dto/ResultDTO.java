package cz.kostka.polanskakeska.dto;

public class ResultDTO {
    private boolean isCorrect;
    private String message;
    private String code;
    private int cacheNumber;

    public ResultDTO(final boolean isCorrect, final String message, final String code, final int cacheNumber) {
        this.isCorrect = isCorrect;
        this.message = message;
        this.code = code;
        this.cacheNumber = cacheNumber;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public int getCacheNumber() {
        return cacheNumber;
    }
}
