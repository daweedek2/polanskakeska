package cz.kostka.polanskakeska.dto;

public final class CrosswordCheckDTO {
    private String teamName;
    private String email;

    public CrosswordCheckDTO(final String teamName, final String email) {
        this.teamName = teamName;
        this.email = email;
    }

    public CrosswordCheckDTO() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
