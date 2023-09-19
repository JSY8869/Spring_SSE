package spring.sse.webflux;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.sse.DataRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebFluxService {

    private final DataRepository dataRepository;

    @Transactional
    public String getNum() {
        return String.valueOf(dataRepository.getNumber());
    }

    @Transactional
    public String addNum() {
        return String.valueOf(dataRepository.addNumber());
    }
}
