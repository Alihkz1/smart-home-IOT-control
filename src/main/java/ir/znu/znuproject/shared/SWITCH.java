package ir.znu.znuproject.shared;

public enum SWITCH {
    ON("ON"),
    OFF("OFF");

    private String v;

    SWITCH(String v) {
        this.v = v;
    }

    public String getSWITCH() {
        return v;
    }
}
