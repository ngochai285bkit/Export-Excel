public class Student {
    private String MSSV;
    private String hoTen;
    private String diaChi;
    private double gpa;


    public Student() {
    }

    public Student(String MSSV, String hoTen, String diaChi, double gpa) {
        this.MSSV = MSSV;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gpa = gpa;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
}
