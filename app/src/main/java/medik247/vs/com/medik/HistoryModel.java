package medik247.vs.com.medik;

/**
 * Created by VS3 on 5/31/2017.
 */

public class HistoryModel {
    String patentName,lastname,patentPhone,patentAddress,patentdate,image,status,requestId,patientId;

    public HistoryModel(String patentName,String lastname,String patentPhone, String patentdate,String image,String status,String requestId,String patientId) {
        this.patentName = patentName;
        this.lastname = lastname;
        this.patentPhone = patentPhone;

        this.patentdate = patentdate;
        this.image = image;
        this.status = status;
        this.requestId = requestId;
        this.patientId = patientId;
    }
    public HistoryModel(){

    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPatentName() {
        return patentName;
    }

    public void setPatentName(String patentName) {
        this.patentName = patentName;
    }

    public String getPatentPhone() {
        return patentPhone;
    }

    public void setPatentPhone(String patentPhone) {
        this.patentPhone = patentPhone;
    }

    public String getPatentAddress() {
        return patentAddress;
    }

    public void setPatentAddress(String patentAddress) {
        this.patentAddress = patentAddress;
    }

    public String getPatentdate() {
        return patentdate;
    }

    public void setPatentdate(String patentdate) {
        this.patentdate = patentdate;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
