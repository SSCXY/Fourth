public class LastInfo {
    private String province;
    private Integer province_num;
    private String area;
    private Integer area_num;

    @Override
    public String toString() {
        return "LastInfo{" +
                "province='" + province + '\'' +
                ", province_num=" + province_num +
                ", area='" + area + '\'' +
                ", area_num=" + area_num +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getProvince_num() {
        return province_num;
    }

    public void setProvince_num(Integer province_num) {
        this.province_num = province_num;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getArea_num() {
        return area_num;
    }

    public void setArea_num(Integer area_num) {
        this.area_num = area_num;
    }

    public LastInfo(String province, Integer province_num, String area, Integer area_num) {
        this.province = province;
        this.province_num = province_num;
        this.area = area;
        this.area_num = area_num;
    }
}
