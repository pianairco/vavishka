package ir.piana.business.treasure.asset;

public enum AssetType {
    EMPTY(0),
    COFFER(1),
    KEY(2),
    BINOCULARS(3),
    KNIFE(4),
    SWORD(5),
    MAP(6),
    HIDING_RING(7),
    HEART(8),
    SOS(9),
    ARMOR(10),
    COMPASS(999);

    private int code;

    AssetType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
