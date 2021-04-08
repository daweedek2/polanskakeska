package cz.kostka.polanskakeska.dto;

public final class CrosswordCheckDTO {
    private String teamName;
    private int memberCount;

    public CrosswordCheckDTO(final String teamName, final int memberCount) {
        this.teamName = teamName;
        this.memberCount = memberCount;
    }

    public CrosswordCheckDTO() {
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(final String teamName) {
        this.teamName = teamName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(final int memberCount) {
        this.memberCount = memberCount;
    }
}
