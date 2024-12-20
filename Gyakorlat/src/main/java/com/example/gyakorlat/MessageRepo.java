package com.example.gyakorlat;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Integer> {
    Message[] findByOrderBySendDateTimeDesc();
}
