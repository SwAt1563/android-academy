package birzeit.edu.trainingcenter;

public class Student {
    private int id;
    private String name;
    private int mobile_number;
    private String address;

    public Student(String studentName) {
        this.name=studentName;

    }

    public Student(String name, int mobile_number, String address) {
        this.name = name;
        this.mobile_number = mobile_number;
        this.address = address;
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
