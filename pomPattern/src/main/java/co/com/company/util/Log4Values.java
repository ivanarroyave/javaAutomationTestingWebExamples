package co.com.company.util;

public enum Log4Values {
    LOG4J_PROPERTIES_FILE_PATH("\\src\\main\\resources\\log4j2.properties");
    private final String value;

    Log4Values(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
