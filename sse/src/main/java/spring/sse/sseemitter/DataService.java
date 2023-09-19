package spring.sse.sseemitter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import spring.sse.DataRepository;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {

    private final DataRepository dataRepository;
    private final List<SseEmitter> sseEmitters = new CopyOnWriteArrayList();
    private final List<SseEmitter> sseEmitters2 = new CopyOnWriteArrayList();
    private final List<SseEmitter> sseEmitters3 = new CopyOnWriteArrayList();
    private final List<SseEmitter> sseEmitters4 = new CopyOnWriteArrayList();

    public SseEmitter getSse() {
        SseEmitter sseEmitter = new SseEmitter(1000000L);
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

    public SseEmitter getSse2() {
        SseEmitter sseEmitter = new SseEmitter(1000000L);
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.sseEmitters2.remove(sseEmitter);    // 만료되면 리스트에서 삭제
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            sseEmitter.complete();
        });
        sseEmitters2.add(sseEmitter);
        return sseEmitter;
    }

    public SseEmitter getSse3() {
        SseEmitter sseEmitter = new SseEmitter(1000000L);
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.sseEmitters3.remove(sseEmitter);    // 만료되면 리스트에서 삭제
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            sseEmitter.complete();
        });
        sseEmitters3.add(sseEmitter);
        return sseEmitter;
    }

    public SseEmitter getSse4() {
        SseEmitter sseEmitter = new SseEmitter(1000000L);
        sseEmitter.onCompletion(() -> {
            log.info("onCompletion callback");
            this.sseEmitters4.remove(sseEmitter);    // 만료되면 리스트에서 삭제
        });
        sseEmitter.onTimeout(() -> {
            log.info("onTimeout callback");
            sseEmitter.complete();
        });
        sseEmitters4.add(sseEmitter);
        return sseEmitter;
    }


    @Transactional(readOnly = true)
    public Long getNumber() {
        return dataRepository.getNumber();
    }

    @Transactional
    public void addNumber() {
        Long number = dataRepository.addNumber();
    }
}
