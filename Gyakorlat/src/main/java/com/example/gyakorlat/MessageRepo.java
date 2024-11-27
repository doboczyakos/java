package com.example.gyakorlat;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Message[] findByOrderBySendDateTimeDesc();
//    Iterable<Message> findAll();
}
