package spring.sse.sseemitter;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import spring.sse.sseemitter.DataService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class SseEmitterController {

    private final DataService dataService;

    @GetMapping
    public SseEmitter getSse() {
        dataService.getNumber();
        SseEmitter sseEmitter = dataService.getSse();
        return sseEmitter;
    }

    @GetMapping("/ver2")
    public SseEmitter getSse2() {
        SseEmitter sseEmitter = dataService.getSse2();
        return sseEmitter;
    }

    @GetMapping("/ver3")
    public SseEmitter getSse3() {
        SseEmitter sseEmitter = dataService.getSse3();
        return sseEmitter;
    }

    @GetMapping("/ver4")
    public SseEmitter getSse4() {
        SseEmitter sseEmitter = dataService.getSse4();
        return sseEmitter;
    }


    @GetMapping("/add")
    public void addNum(Model model) {
        dataService.addNumber();
    }
}
