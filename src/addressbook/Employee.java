package addressbook;

import java.io.Serializable;

public class Employee implements Serializable {

    private String name;
    private long mobileNum;
    private String emailId;

    public void setName(String name) {
        this.name = name;
    }

    public void setMobileNum(long mobileNum) {
        this.mobileNum = mobileNum;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "Employee[" + "name=" + name + ", mobileNum=" + mobileNum + ", emailId=" + emailId + ']';
    }

    public long getMobileNum() {
        return this.mobileNum;
    }

    public String getEmailId() {
        return this.emailId;
    }
}
