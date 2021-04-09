package cz.kostka.polanskakeska.dto;

public class TeamFormDTO {
    private String name;
    private String email;
    private String members;

    public TeamFormDTO(final String name, final String email, final String members) {
        this.name = name;
        this.email = email;
        this.members = members;
    }

    public TeamFormDTO() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMembers() {
        return members;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setMembers(final String members) {
        this.members = members;
    }
}
