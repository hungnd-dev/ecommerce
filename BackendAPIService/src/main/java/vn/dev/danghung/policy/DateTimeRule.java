package vn.dev.danghung.policy;

public interface DateTimeRule {
    void verify(String fromDate, String toDate) throws Exception;
}
