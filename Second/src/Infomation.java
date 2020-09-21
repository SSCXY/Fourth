public class Infomation {
    private String province;
    private String area;
    private String num;

    @Override
    public String toString() {
        return "Infomation{" +
                "province='" + province + '\'' +
                ", area='" + area + '\'' +
                ", num='" + num + '\'' +
                '}';
    }

    public Infomation(String province, String area, String num) {
        this.province = province;
        this.area = area;
        this.num = num;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
