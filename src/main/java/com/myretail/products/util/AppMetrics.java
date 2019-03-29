package com.myretail.products.util;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;

@Component
public class AppMetrics {

    Counter incomingRequestCounter;
    Counter responseSuccessCounter;
    Counter responseFailureCounter;

    private String errorMessage;

    public AppMetrics()
    {
        incomingRequestCounter = Metrics.counter(("products_api_incoming_request_count"));
        responseSuccessCounter = Metrics.counter(("products_api_response_success_count"));
        responseFailureCounter = Metrics.counter(("products_api_response_failure_count"));
    }

    public void incrementIncomingRequestCounter()
    {
        incomingRequestCounter.increment();
    }

    public void incrementResponseSuccessCounter()
    {
        responseSuccessCounter.increment();
    }

    public void incrementResponseFailureCounter()
    {
        responseFailureCounter.increment();
    }
}

