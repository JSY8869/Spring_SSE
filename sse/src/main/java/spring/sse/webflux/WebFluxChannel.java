package spring.sse.webflux;

import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

public class WebFluxChannel {
    private EmitterProcessor<String> processor;
    private Flux<String> flux;
    private FluxSink<String> sink;
    private Runnable closeCallback;

    public WebFluxChannel() {
        processor = EmitterProcessor.create();
        this.sink = processor.sink();
        this.flux = processor
                .doOnCancel(() -> {
                    if (processor.downstreamCount() == 1) close();
                })
                .doOnTerminate(() -> {
                });
    }

    public void send(String message) {
        sink.next(message);
    }

    public Flux<String> toFlux() {
        return flux;
    }

    private void close() {
        if (closeCallback != null) closeCallback.run();
        sink.complete();
    }

    public WebFluxChannel onClose(Runnable closeCallback) {
        this.closeCallback = closeCallback;
        return this;
    }
}
