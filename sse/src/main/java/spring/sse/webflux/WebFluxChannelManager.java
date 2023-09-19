package spring.sse.webflux;

import java.util.concurrent.ConcurrentHashMap;

public class WebFluxChannelManager {
    private ConcurrentHashMap<Long, WebFluxChannel> map = new ConcurrentHashMap<>();

    public WebFluxChannel connect(Long userId) {
        return map.computeIfAbsent(userId, key -> new WebFluxChannel().onClose(() ->
                map.remove(userId)));
    }

    public void post(String message) {
        map.forEach((aLong, webFluxChannel) -> webFluxChannel.send(message));
    }
}
