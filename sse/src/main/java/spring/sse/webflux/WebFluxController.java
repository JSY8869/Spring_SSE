package spring.sse.webflux;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class WebFluxController {
    private WebFluxChannelManager channels = new WebFluxChannelManager();
    private final WebFluxService webFluxService;

    @GetMapping("/webflux/{userId}")
    public Flux<ServerSentEvent<String>> connect(@PathVariable("userId") Long userId) {
        Flux<String> userStream = channels.connect(userId).toFlux();
        Flux<String> tickStream = Flux.just(webFluxService.getNum());
        return Flux.merge(userStream, tickStream)
                .map(str -> ServerSentEvent.builder(str).build());
    }

    @PostMapping(path = "/webflux")
    public void send() {
        String num = webFluxService.addNum();
        channels.post(num);
    }
}
