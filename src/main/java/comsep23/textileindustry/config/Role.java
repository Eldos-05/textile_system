package comsep23.textileindustry.config;


public enum Role {
    ROLE_SALEMAN,
    ROLE_DELIVERYMAN,
    ROLE_PROVIDER,
    ROLE_CUSTOMER;
    @Override
    public String toString() {
        return name().replace("ROLE_", "").toLowerCase();
    }
}

