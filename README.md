# Spring_SSE
Spring에서 Server-Sent-Event 를 연습하기 위한 레포지터리

## SSE란?
- 특정 데이터의 변화가 일어났을 때 실시간으로 해당 데이터를 클라이언트에게 전달하는 기술
- 한번 연결을 맺고 일정 시간동안 변경이 일어날 때마다 데이터를 전달함
- 서버에서 클라이언트로 text message를 보내는 브라우저 기반 웹 애플리케이션 기술
- HTTP의 persistent connections을 기반으로하는 HTML5 표준 기술
- 데이터는 UTF-8로 인코딩된 텍스트 데이터만 가능
- 클라이언트가 서버와 크게 통신할 필요 없이 단지 업데이트된 데이터만 받아야 하는 실시간 데이터 스트림에 대한 구현이 필요할 때는 매우 훌륭한 선택

<img src="https://github.com/JSY8869/Spring_SSE/assets/65009713/c5025a61-f800-43aa-9290-3119059cf7d2" width="700" height="500"/>

## 흐름
1. 클라이언트에서 SSE 연결 요청을 보낸다.
2. 서버에서는 클라이언트와 매핑되는 SSE 통신 객체를 만든다.
3. 서버에서 이벤트가 발생하면 해당 객체를 통해 클라이언트로 데이터를 전달한다.

## Spring의 SSE
- spring framework 4.2부터 SSE 통신을 지원하는 SseEmitter API를 제공
- Emitter를 생성하고 나서 만료 시간까지 아무런 데이터도 보내지 않으면 재연결 요청시 503 Service Unavailable 에러가 발생 (더미 데이터로 해결)
- SseEmitter를 생성할 때는 비동기 요청이 완료되거나 타임아웃 발생 시 실행할 콜백을 등록할 수 있다.
- 타임아웃 발생 시 콜백을 실행하는데 이 때 재연결을 요청하므로 기존 연결(emitter)을 삭제해야 한다.
- 이 콜백은 emitter를 관리하는 다른 스레드에서 실행되므로 emitter의 저장은 thread-safe한 구조를 사용하여야 한다. (ex: CopyOnWriteArrayList)

## 예시
### 버전
- Spring Boot 3.1.3
- Java 17
- lombok 1.18.28
- h2 2.1.214
- spring-boot-devtools 3.1.3
- thymeleaf 3.1.2
- spring-data-jpa 3.1.3

## 참고 자료
https://tecoble.techcourse.co.kr/post/2022-10-11-server-sent-events/

https://docs.spring.io/spring-framework/docs/4.2.0.RC2_to_4.2.0.RC3/Spring%20Framework%204.2.0.RC3/org/springframework/web/servlet/mvc/method/annotation/SseEmitter.html

https://hudi.blog/server-sent-events-with-spring/
