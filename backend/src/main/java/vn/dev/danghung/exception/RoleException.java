package vn.dev.danghung.exception;

public class RoleException extends Exception{
    private int code;

    public RoleException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
