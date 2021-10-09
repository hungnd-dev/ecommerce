package vn.dev.danghung.external.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tatsuya
 */
public abstract class AbstractTask<T> {
    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");
    protected static final Logger pLogger = LogManager.getLogger("WorkerLog");
    public abstract String getURL();
    public abstract T performTask() throws Exception;
}
