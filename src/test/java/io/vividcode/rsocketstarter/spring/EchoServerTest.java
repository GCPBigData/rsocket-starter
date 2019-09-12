package io.vividcode.rsocketstarter.spring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;

import java.time.Duration;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class EchoServerTest extends AbstractTest {

  @Test
  @DisplayName("Test echo server")
  void testEcho() {
    RSocketRequester requester = createRSocketRequester();
    Mono<String> response = requester.route("echo")
        .data("hello")
        .retrieveMono(String.class);
    StepVerifier.create(response)
        .expectNext("ECHO >> hello")
        .expectComplete()
        .verify();
  }

}
