package net.danh.rpgmythicc.Enum;

public enum ServerVersion {


    VERSION_1_7("v1_7", "1.7", false),
    VERSION_1_8("v1_8", "1.8", false),
    VERSION_1_9("v1_9", "1.9", false),
    VERSION_1_10("v1_10", "1.10", false),
    VERSION_1_11("v1_11", "1.11", false),
    VERSION_1_12("v1_12", "1.12", false),
    VERSION_1_13_R1("v1_13_R1", "1.13", false),
    VERSION_1_13_R2("v1_13_R2", "1.13.1", false),
    VERSION_1_14("v1_14_R1", "1.14", true),
    VERSION_1_15("v1_15_R1", "1.15", true),
    VERSION_1_16_R1("v1_16_R1", "1.16.1", true),
    VERSION_1_16_R2("v1_16_R2", "1.16.2", true),
    VERSION_1_16_R3("v1_16_R3", "1.16.5", true),
    VERSION_1_17("v1_17_R1", "1.17.1", true),
    VERSION_1_18("v1_18_R1", "1.18.1", false),
    VERSION_1_18_R2("v1_18_R2", "1.18.2", false),
    VERSION_1_19("v1_19", "1.19", false),

    NOT_SUPPORTED("Not Supported", "Not Supported", false),
    UNKNOWN("UNKNOWN", "UNKNOWN", false),
    ;

    private final String value;
    private final String displayValue;
    private final boolean isSupported;

    ServerVersion(String value, String displayValue, boolean isSupported) {
        this.value = value;
        this.displayValue = displayValue;
        this.isSupported = isSupported;
    }

    public String getValue() {
        return value;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public boolean isSupported() {
        return isSupported;
    }

}
