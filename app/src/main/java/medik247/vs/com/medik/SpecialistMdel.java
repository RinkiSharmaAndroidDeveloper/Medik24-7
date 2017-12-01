package medik247.vs.com.medik;

/**
 * Created by Vs1 on 5/29/2017.
 */

public class SpecialistMdel  {
    String speciality_id;
    String speciality_name;

    public String getSpeciality_id() {
        return speciality_id;
    }

    public void setSpeciality_id(String speciality_id) {
        this.speciality_id = speciality_id;
    }

    public String getSpeciality_name() {
        return speciality_name;
    }

    public void setSpeciality_name(String speciality_name) {
        this.speciality_name = speciality_name;
    }

    public SpecialistMdel(String speciality_id, String speciality_name) {

        this.speciality_id = speciality_id;
        this.speciality_name = speciality_name;
    }
}
