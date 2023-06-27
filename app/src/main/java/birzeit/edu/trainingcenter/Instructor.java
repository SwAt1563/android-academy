package birzeit.edu.trainingcenter;

public class Instructor {

    private int id;
    private String name;
    private int mobile_number;
    private String address;
    private String specialization;
    private String degree;

    public Instructor(String name) {
        this.name=name;

    }

    public Instructor(String name, int mobile_number, String address) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.address = address;
    }

    public Instructor(String name, int mobile_number, String address, String specialization, String degree) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.address = address;
        this.specialization = specialization;
        this.degree = degree;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(int mobile_number) {
        this.mobile_number = mobile_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
