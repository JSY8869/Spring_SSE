package spring.sse;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class dataController {

    private final DataService dataService;

    @GetMapping
    public SseEmitter getSse() {
        dataService.getNumber();
        SseEmitter sseEmitter = dataService.getSse();
        return sseEmitter;
    }


    @GetMapping("/add")
    public void addNum(Model model) {
        dataService.addNumber();
    }
}
