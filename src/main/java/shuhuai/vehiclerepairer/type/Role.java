package shuhuai.vehiclerepairer.type;

public enum Role {
    维修员("维修员"), 业务员("业务员");

    private String type;

    Role(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public static Role getRoleEnum(String type) {
        if (type.equals("维修员")) {
            return 维修员;
        } else if (type.equals("业务员")) {
            return 业务员;
        }
        return null;
    }
}