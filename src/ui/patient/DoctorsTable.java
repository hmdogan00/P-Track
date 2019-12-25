package ui.patient;

public class DoctorsTable {
    String docName, docDepartment, appDate, appTime;

    public DoctorsTable(String docName, String docDepartment, String appDate, String appTime) {
        this.docName = docName;
        this.docDepartment = docDepartment;
        this.appDate = appDate;
        this.appTime = appTime;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDocDepartment() {
        return docDepartment;
    }

    public void setDocDepartment(String docDepartment) {
        this.docDepartment = docDepartment;
    }

    public String getAppDate() {
        return appDate;
    }

    public void setAppDate(String appDate) {
        this.appDate = appDate;
    }

    public String getAppTime() {
        return appTime;
    }

    public void setAppTime(String appTime) {
        this.appTime = appTime;
    }
}
