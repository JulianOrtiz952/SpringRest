package com.application.rest.controllers;

import com.application.rest.controllers.dto.MakerDTO;
import com.application.rest.entities.Maker;
import com.application.rest.services.IMakerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maker")
public class MakerController {
    @Autowired
    private IMakerService makerService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Maker> makerOptional = makerService.findById(id);

        if(makerOptional.isPresent()){
            Maker maker = makerOptional.get();

            MakerDTO makerDTO = MakerDTO.builder().id(maker.getId()).name(maker.getName()).productList(maker.getProductList()).build();
            return ResponseEntity.ok(makerDTO);
        }
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<MakerDTO> makerList = makerService.findAllUsers().stream().map(maker -> MakerDTO.builder().id(maker.getId()).name(maker.getName()).productList(maker.getProductList()).build()).toList();

        return ResponseEntity.ok(makerList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException {
        if(makerDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        makerService.save(Maker.builder().name(makerDTO.getName()).build());
        return ResponseEntity.created(new URI("/api/maker/save")).build();

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id , @RequestBody MakerDTO makerDTO){
        Optional<Maker> makerOptional = makerService.findById(id);

        if(makerOptional.isPresent()){
            Maker maker = makerOptional.get();
            maker.setName(makerDTO.getName());
            makerService.save(maker);
            return ResponseEntity.ok("user has been update successfully");
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(id!=null){
            makerService.deleteById(id);
            return ResponseEntity.ok("user has been deleted");
        }
        return ResponseEntity.notFound().build();
    }

}
