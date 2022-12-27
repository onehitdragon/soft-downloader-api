package com.example.softdownloaderapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softdownloaderapi.model.ResponseMessage;
import com.example.softdownloaderapi.model.Soft;
import com.example.softdownloaderapi.model.User;
import com.example.softdownloaderapi.repository.SoftRepository;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/soft")
public class SoftController {
    @Autowired
    private SoftRepository softRepository;

    @GetMapping("/gethightestviewing")
    public List<Soft> getHightestViewing(Integer amount){
        return softRepository.getHighestViewingSoft(amount);
    }
    
    @GetMapping("/getnewestviewing")
    public List<Soft> getNewestViewing(Integer amount){
        return softRepository.getNewestViewingSoft(amount);
    }

    @GetMapping("/getbyparentcategory")
    public List<Soft> getByParentCategory(Integer parentCategoryId){
        return softRepository.getByParentCategorySoft(parentCategoryId);
    }

    @GetMapping("/getbychildcategory")
    public List<Soft> getByChildCategory(Integer childCategoryId){
        return softRepository.getByChildCategorySoft(childCategoryId);
    }

    @GetMapping("/{idSoft}")
    public Soft getSoft(@PathVariable(value = "idSoft") Integer id){
        return softRepository.getSoft(id);
    }

    @PostMapping("/add")
    public Soft addSoft(@NotBlank String title, @NotBlank String content,
        @NotBlank @Min(1) String authorId){
        Soft soft = new Soft();

        User user = new User();
        user.setId(authorId);

        soft.setTitle(title);
        soft.setAuthor(user);
        soft.setContent(content);
        soft.setAmountView(0);
        soft.setCreateDate(new Date());

        return softRepository.insert(soft);
    }

    @DeleteMapping("/{idSoft}")
    public void deleteSoft(@PathVariable(value = "idSoft")@NotBlank @Min(1) Integer id){
        softRepository.delete(id);
    }

    @GetMapping("/search")
    public List<Soft> searchSoft(@NotBlank String keyword){
        return softRepository.search(keyword);
    }

    @PutMapping("/addViewingSoft/{idSoft}")
    public ResponseEntity<ResponseMessage> addViewingSoft(@PathVariable(value = "idSoft")@NotBlank @Min(1) Integer id){
        softRepository.addViewing(id);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("success", ""), HttpStatus.OK);
    }

    @GetMapping("/getall")
    public List<Soft> getAllSoft(){
        return softRepository.getAll();
    }
}
