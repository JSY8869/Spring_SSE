package spring.sse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

    private final DataRepository dataRepository;
    private final List<SseEmitter> sseEmitters = new CopyOnWriteArrayList();

    public SseEmitter getSse() {
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.sseEmitters.remove(sseEmitter);    // 만료되면 리스트에서 삭제
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            sseEmitter.complete();
        });
        sseEmitters.add(sseEmitter);
        return sseEmitter;
    }

    @Transactional(readOnly = true)
    public Long getNumber() {
        return dataRepository.getNumber();
    }

    @Transactional
    public void addNumber() {
        Long number = dataRepository.addNumber();
        sseEmitters.forEach(sseEmitter -> {
            try {
                sseEmitter.send(SseEmitter.event()
                        .name("number")
                        .data(number));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
