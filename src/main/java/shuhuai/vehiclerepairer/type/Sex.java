package shuhuai.vehiclerepairer.type;

public enum Sex {
    男("男"), 女("女");

    private String type;

    Sex(String type) {
        this.type = type;
    }

    public static Sex getSexEnum(String type) {
        if (type.equals("男")) {
            return 男;
        } else if (type.equals("女")) {
            return 女;
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}