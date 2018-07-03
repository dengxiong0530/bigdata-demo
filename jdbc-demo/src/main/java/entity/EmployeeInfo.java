package entity;

public class EmployeeInfo {

    private String peopleName;
    private String peopleNo;
    private String dot;//所属点部
    private String inDate;//入职时间


    public EmployeeInfo() {

    }

    public EmployeeInfo(String peopleNo,String peopleName, String dot, String inDate) {
        this.peopleNo = peopleNo;
        this.peopleName = peopleName;

        this.dot = dot;
        this.inDate = inDate;
    }


    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "peopleName='" + peopleName + '\'' +
                ", peopleNo='" + peopleNo + '\'' +
                ", dot='" + dot + '\'' +
                ", inDate='" + inDate + '\'' +
                '}';
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleNo() {
        return peopleNo;
    }

    public void setPeopleNo(String peopleNo) {
        this.peopleNo = peopleNo;
    }

    public String getDot() {
        return dot;
    }

    public void setDot(String dot) {
        this.dot = dot;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }
}
