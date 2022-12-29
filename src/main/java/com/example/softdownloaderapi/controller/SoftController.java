package com.example.softdownloaderapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.softdownloaderapi.model.ChildCategory;
import com.example.softdownloaderapi.model.ResponseMessage;
import com.example.softdownloaderapi.model.ResponseMessageWithOption;
import com.example.softdownloaderapi.model.Soft;
import com.example.softdownloaderapi.model.User;
import com.example.softdownloaderapi.repository.SoftRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public ResponseEntity<ResponseMessage> addSoft(@NotBlank String title, @NotBlank String content, MultipartFile[] images,
        @NotBlank @Min(1) String authorId, @NotBlank @Min(1) Integer childCategoryId){
        
        for(int i = 0; i < images.length; i++){
            MultipartFile image = images[i];
            try{
                String des =  "src\\main\\resources\\static\\images\\soft\\upload\\";
                des += image.getOriginalFilename();
                System.out.println(des);
                Files.copy(image.getInputStream(), Paths.get(des));
            }
            catch(IOException ex){
                ex.printStackTrace();
                return new ResponseEntity<ResponseMessage>(
                    new ResponseMessage("error", "image upload error"), 
                    HttpStatus.OK
                );
            }
            
        }

        Soft soft = new Soft();

        User user = new User();
        user.setId(authorId);

        soft.setTitle(title);
        soft.setAuthor(user);
        soft.setContent(content);
        soft.setAmountView(0);
        soft.setCreateDate(new Date());

        ChildCategory childCategory = new ChildCategory();
        childCategory.setId(childCategoryId);
        ArrayList<ChildCategory> childList = new ArrayList<ChildCategory>();
        childList.add(childCategory);
        soft.setChildCategories(childList);

        softRepository.insert(soft);

        return new ResponseEntity<ResponseMessage>(
            new ResponseMessageWithOption<Soft>("success", "", softRepository.getMaxSoft()),
            HttpStatus.OK
        );
    }

    @DeleteMapping("/{idSoft}")
    public ResponseEntity<ResponseMessage> deleteSoft(@PathVariable(value = "idSoft")@NotBlank @Min(1) Integer id){
        softRepository.delete(id);

        return new ResponseEntity<ResponseMessage>(new ResponseMessage("success", ""), HttpStatus.OK);
    }

    @PutMapping("/{idSoft}")
    public ResponseEntity<ResponseMessage> editSoft(@PathVariable(value = "idSoft")@NotBlank @Min(1) Integer id, @NotBlank String title, @NotBlank String content, MultipartFile[] images,
        @NotBlank @Min(1) String authorId, @NotBlank @Min(1) Integer childCategoryId){
        
        if(images != null){
            for(int i = 0; i < images.length; i++){
                MultipartFile image = images[i];
                try{
                    String des =  "src\\main\\resources\\static\\images\\soft\\upload\\";
                    des += image.getOriginalFilename();
                    System.out.println(des);
                    Files.copy(image.getInputStream(), Paths.get(des));
                }
                catch(IOException ex){
                    ex.printStackTrace();
                    return new ResponseEntity<ResponseMessage>(
                        new ResponseMessage("error", "image upload error"), 
                        HttpStatus.OK
                    );
                }
            }
        }
        

        Soft curSoft = softRepository.getSoft(id);

        User user = new User();
        user.setId(authorId);

        curSoft.setTitle(title);
        curSoft.setAuthor(user);
        curSoft.setContent(content);

        ChildCategory childCategory = new ChildCategory();
        childCategory.setId(childCategoryId);
        ArrayList<ChildCategory> childList = new ArrayList<ChildCategory>();
        childList.add(childCategory);
        curSoft.setChildCategories(childList);

        softRepository.update(curSoft);

        return new ResponseEntity<ResponseMessage>(
            new ResponseMessageWithOption<Soft>("success", "", softRepository.getSoft(id)),
            HttpStatus.OK
        );
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
