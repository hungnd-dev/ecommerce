package vn.dev.danghung.external.task;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import vn.dev.danghung.factory.HttpRequestFactory;

public class EmployeeScoreGetterTask extends AbstractTask<String> {

    private long employeeID;

    public EmployeeScoreGetterTask(long employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public String getURL() {
        return "URL TO CALL";
    }

    @Override
    public String performTask() throws Exception {
        // get http connection from pool --> call to API and return raw String response
        OkHttpClient httpClient = HttpRequestFactory.getInstance().getHttpClient();
        Request request = new Request.Builder()
                .addHeader("Connection", "close")
                .url(getURL())
                .build();

        Response response = httpClient.newCall(request).execute();

        String rs;

        ResponseBody body = response.body();
        try {
            rs = body.string();
        } finally {
            body.close();
        }
        return rs;
    }
}
