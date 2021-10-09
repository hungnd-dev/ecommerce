package vn.dev.danghung.exception;

public class EmployeeException extends Exception {
    private int code;

    public EmployeeException(String message, int code) {
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