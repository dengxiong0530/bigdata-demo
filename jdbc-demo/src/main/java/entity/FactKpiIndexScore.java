package entity;

public class FactKpiIndexScore {

    private String statMon;
    private String statType;
    private String peopleNo;
    private String dotCode;
    private String qualityScore;


    public FactKpiIndexScore() {
    }

    public FactKpiIndexScore(String statMon, String statType, String peopleNo, String dotCode, String qualityScore) {
        this.statMon = statMon;
        this.statType = statType;
        this.peopleNo = peopleNo;
        this.dotCode = dotCode;
        this.qualityScore = qualityScore;
    }

    public String getStatMon() {
        return statMon;
    }

    public void setStatMon(String statMon) {
        this.statMon = statMon;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }

    public String getPeopleNo() {
        return peopleNo;
    }

    public void setPeopleNo(String peopleNo) {
        this.peopleNo = peopleNo;
    }

    public String getDotCode() {
        return dotCode;
    }

    public void setDotCode(String dotCode) {
        this.dotCode = dotCode;
    }

    public String getQualityScore() {
        return qualityScore;
    }

    public void setQualityScore(String qualityScore) {
        this.qualityScore = qualityScore;
    }
}

