package cz.kostka.polanskakeska.dto;

public class CacheValidationDTO {
    private int cacheNumber;
    private String teamName;
    private String email;
    private String solution;

    public CacheValidationDTO(final int cacheNumber, final String teamName, final String email, final String solution) {
        this.cacheNumber = cacheNumber;
        this.teamName = teamName;
        this.email = email;
        this.solution = solution;
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

    public String getSolution() {
        return solution;
    }

    public void setSolution(final String solution) {
        this.solution = solution;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
