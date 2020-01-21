package lq.studay.day03;

public class DataObj {

    private String tv1;
    private String tv2;
//    private int iv1;
//
//    public DataObj(String tv1,String tv2, int iv1) {
//        this.tv1 = tv1;
//        this.tv2 = tv2;
//        this.iv1 = iv1;
//    }

    private String iv1;

    public DataObj(String tv1,String tv2, String iv1) {
        this.tv1 = tv1;
        this.tv2 = tv2;
        this.iv1 = iv1;
    }


    @Override
    public String toString() {
        return "DataObj{" +
                "tv1='" + tv1 + '\'' +
                ", iv1=" + iv1 +
                '}';
    }
}
