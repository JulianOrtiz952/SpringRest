package com.application.rest.services.implement;

import com.application.rest.entities.Maker;
import com.application.rest.entities.Product;
import com.application.rest.persistence.implement.MakerDAOImplement;
import com.application.rest.repositories.MakerRepository;
import com.application.rest.services.IMakerService;
import com.application.rest.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class MakerServiceImplement implements IMakerService {


    @Autowired
    private MakerDAOImplement makerDAOImplement;

    @Override
    public List<Maker> findAllUsers() {
        return (List<Maker>) makerDAOImplement.findAllUsers();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return  makerDAOImplement.findById(id);
    }

    @Override
    public void save(Maker maker) {
        makerDAOImplement.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        makerDAOImplement.deleteById(id);
    }
}
