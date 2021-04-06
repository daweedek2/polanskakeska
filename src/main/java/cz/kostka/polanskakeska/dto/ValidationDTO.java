package cz.kostka.polanskakeska.dto;

public class ValidationDTO {
    private int cacheNumber;
    private String teamName;
    private String answer;

    public ValidationDTO(final int cacheNumber, final String teamName, final String answer) {
        this.cacheNumber = cacheNumber;
        this.teamName = teamName;
        this.answer = answer;
    }

    public int getCacheNumber() {
        return cacheNumber;
    }

    public void setCacheNumber(final int cacheNumber) {
        this.cacheNumber = cacheNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answer) {
        this.answer = answer;
    }
}
