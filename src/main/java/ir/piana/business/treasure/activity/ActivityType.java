package ir.piana.business.treasure.activity;

public enum ActivityType {
    ROBBERY(1),
    KILL(2),
    PICKING_UP(3),
    PUT(4),
    PARTNER(5),
    NEGOTIATION(999);

    private int code;

    ActivityType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
