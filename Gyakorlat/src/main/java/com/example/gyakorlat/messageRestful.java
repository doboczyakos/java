package com.example.gyakorlat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
class messageRestful {
    private final MessageRepo messageRepo;
    messageRestful(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @GetMapping("/restMessages")
    Iterable<Message> getAllMessages(){
        return messageRepo.findAll();
    }

    @GetMapping("/restMessages/{id}")
    Optional<Message> getMessage(@PathVariable Long id){
        return Optional.ofNullable(messageRepo.findById(Math.toIntExact(id)).orElseThrow(() -> new messageNotFoundException(id)));
    }

    @PostMapping("/restAdd")
    Message addMessage(@RequestBody Message message){
        return messageRepo.save(message);
    }

    @PutMapping("/restPut/{id}")
    Message updateMessage(@RequestBody Message message, @PathVariable Long id){
        return messageRepo.findById(Math.toIntExact(id))
                .map(a -> {
                    a.setText(message.getText());
                    a.setText(message.getSender());
                    a.setSendDateTime(message.getSendDateTime());
                    return messageRepo.save(a);
                })
                .orElseGet(() -> {
                    message.setId(id);
                    return messageRepo.save(message);
                });
    }

    @DeleteMapping("/restDel/{id}")
    void deleteMessage(@PathVariable Long id){
        messageRepo.deleteById(Math.toIntExact(id));
    }





}
