package by.weekmenu.DTO;

public class AccountDTO {

    private Integer familyCount;
    private String familyName;

    public AccountDTO(Integer familyCount, String familyName) {
        this.familyCount = familyCount;
        this.familyName = familyName;
    }

    public Integer getFamilyCount() {
        return familyCount;
    }

    public void setFamilyCount(Integer familyCount) {
        this.familyCount = familyCount;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
