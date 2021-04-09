package cz.kostka.polanskakeska.dto;

public class CacheFormDTO {
    private int number;
    private String code;
    private String passwords;

    public CacheFormDTO(final int number, final String code, final String passwords) {
        this.number = number;
        this.code = code;
        this.passwords = passwords;
    }

    public CacheFormDTO() {
    }

    public int getNumber() {
        return number;
    }

    public String getCode() {
        return code;
    }

    public String getPasswords() {
        return passwords;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setPasswords(final String passwords) {
        this.passwords = passwords;
    }
}
