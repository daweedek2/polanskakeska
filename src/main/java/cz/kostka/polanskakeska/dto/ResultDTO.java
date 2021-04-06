package cz.kostka.polanskakeska.dto;

public class ResultDTO {
    private boolean isCorrect;
    private String message;
    private String code;

    public ResultDTO(final boolean isCorrect, final String message, final String code) {
        this.isCorrect = isCorrect;
        this.message = message;
        this.code = code;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(final boolean correct) {
        isCorrect = correct;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }
}
