package vn.dev.danghung.external;

import com.google.gson.Gson;
import net.jodah.failsafe.RetryPolicy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;

/**
 * external component, responsible for transferring data between  actors in system
 *
 * @author tatsuya
 */
public class AbstractExternalComponent {

    @Autowired
    @Qualifier("gsonCustom")
    protected Gson gson;

    protected RetryPolicy<Object> retryPolicy = new RetryPolicy<>()
            .handle(Exception.class)
            .withDelay(Duration.ofMillis(10))
            .withMaxRetries(1);

    protected static final Logger eLogger = LogManager.getLogger("ErrorLog");
    protected static final Logger pLogger = LogManager.getLogger("WorkerLog");
    protected static final Logger cLogger = LogManager.getLogger("CacheLog");

}

