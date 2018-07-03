package entity;

public class DimDotInfo {

    private String dotCode;
    private String dotName;
    private String operationDeptCode;
    private String getOperationDeptCodeName;
    private String regionCode;
    private String regionName;
    private String etlUpdateTime;

    public String getDotCode() {
        return dotCode;
    }

    public DimDotInfo() {
    }

    public DimDotInfo(String dotCode, String dotName, String operationDeptCode, String getOperationDeptCodeName, String regionCode, String regionName, String etlUpdateTime) {
        this.dotCode = dotCode;
        this.dotName = dotName;
        this.operationDeptCode = operationDeptCode;
        this.getOperationDeptCodeName = getOperationDeptCodeName;
        this.regionCode = regionCode;
        this.regionName = regionName;
        this.etlUpdateTime = etlUpdateTime;
    }

    public void setDotCode(String dotCode) {
        this.dotCode = dotCode;
    }

    public String getDotName() {
        return dotName;
    }

    public void setDotName(String dotName) {
        this.dotName = dotName;
    }

    public String getOperationDeptCode() {
        return operationDeptCode;
    }

    public void setOperationDeptCode(String operationDeptCode) {
        this.operationDeptCode = operationDeptCode;
    }

    public String getGetOperationDeptCodeName() {
        return getOperationDeptCodeName;
    }

    public void setGetOperationDeptCodeName(String getOperationDeptCodeName) {
        this.getOperationDeptCodeName = getOperationDeptCodeName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getEtlUpdateTime() {
        return etlUpdateTime;
    }

    public void setEtlUpdateTime(String etlUpdateTime) {
        this.etlUpdateTime = etlUpdateTime;
    }

    @Override
    public String toString() {
        return "DimDotInfo{" +
                "dotCode='" + dotCode + '\'' +
                ", dotName='" + dotName + '\'' +
                ", operationDeptCode='" + operationDeptCode + '\'' +
                ", getOperationDeptCodeName='" + getOperationDeptCodeName + '\'' +
                ", regionCode='" + regionCode + '\'' +
                ", regionName='" + regionName + '\'' +
                ", etlUpdateTime='" + etlUpdateTime + '\'' +
                '}';
    }
}
