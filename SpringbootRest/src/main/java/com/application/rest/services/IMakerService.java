package com.application.rest.services;

import com.application.rest.entities.Maker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface IMakerService {

    List<Maker> findAllUsers();

    Optional<Maker> findById(Long id);

    void save(Maker maker);

    void deleteById(Long id);
}
