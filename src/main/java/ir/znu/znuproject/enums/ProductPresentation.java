package ir.znu.znuproject.enums;

public enum ProductPresentation {
    PRESENT((int) 1),
    FINISHED((int) 2);

    private Integer flag;

    public Integer getFlag() {
        return flag;
    }

    ProductPresentation(int flag) {
        this.flag = flag;
    }
}
