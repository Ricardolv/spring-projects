package com.richard.eventhubs.infrastructure.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

@Slf4j
@RestController
public class EventProducerResource {

    @Autowired
    private Sinks.Many<Message<String>> many;

    @PostMapping("/messages")
    public ResponseEntity<String> sendMessage(@RequestParam String message) {
        log.info("Going to add message {} to sendMessage.", message);
        many.emitNext(MessageBuilder.withPayload(message).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return ResponseEntity.ok(message);
    }

}
