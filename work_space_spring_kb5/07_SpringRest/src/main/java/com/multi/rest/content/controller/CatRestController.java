package com.multi.rest.content.controller;


import com.multi.rest.content.model.vo.Cat;
import com.multi.rest.content.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 해당 클래스에 해당하는 핸들러는 /cat이 기본 URL로 설정됨
@RequestMapping("/cat")
@RestController
public class CatRestController {

    @Autowired
    private CatService catService;


    @CrossOrigin(origins = "http://localhost", maxAge = 3000)
    @PostMapping("/form") // create 전용, 쓰기 용도
// -> HTML에서 form으로 받올때 쓰는 방식! REST에서는 올바르지 않은 방식!
    public ResponseEntity<Cat> createCatForHtmlForm(Cat requestCat){
        int result = catService.insertCat(requestCat);
        if(result > 0) {
            Cat cat = catService.selectById(requestCat.getId());
            return ResponseEntity.status(HttpStatus.OK).body(cat);
        }{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // @RequestBody : json으로 구성되어 있는 요청 값을 객체로 변환하는 어노테이션, 언마샬링 전용
    @CrossOrigin(origins = "http://localhost", maxAge = 3000)
// POST : /animal/cat/
    @PostMapping("") // create 전용, 쓰기 용도
    public ResponseEntity<Cat> createCat(@RequestBody Cat requestCat){
        int result = catService.insertCat(requestCat);
        if(result > 0) {
            Cat cat = catService.selectById(requestCat.getId());
            return ResponseEntity.status(HttpStatus.OK).body(cat);
        }{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin(origins = "http://localhost", maxAge = 3000)
// PUT : /animal/cat/{id}
    @PutMapping("/{id}") // put : update 할때 사용하는 어노테이션
    public ResponseEntity<Cat> updateCat(
            @PathVariable("id") int id, @RequestBody Cat requestCat){
        requestCat.setId(id);
        Cat cat = catService.updateCat(requestCat);
        if(cat != null) {
            return ResponseEntity.status(HttpStatus.OK).body(cat);
        }{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @CrossOrigin(origins = "http://localhost", maxAge = 3000)
// DELETE : /animal/cat/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Cat> deleteCat(@PathVariable("id") int id){
        Cat cat = catService.selectById(id);
        int result = catService.deleteCat(id);
        if(result > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(cat);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
