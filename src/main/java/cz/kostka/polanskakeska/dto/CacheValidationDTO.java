package cz.kostka.polanskakeska.dto;

public class CacheValidationDTO {
    private int cacheNumber;
    private String teamName;
    private String email;
    private String answer;

    public CacheValidationDTO(final int cacheNumber, final String teamName, final String email, final String answer) {
        this.cacheNumber = cacheNumber;
        this.teamName = teamName;
        this.email = email;
        this.answer = answer;
    }

    public CacheValidationDTO() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
