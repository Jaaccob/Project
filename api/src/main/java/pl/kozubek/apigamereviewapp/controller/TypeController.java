package pl.kozubek.apigamereviewapp.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.kozubek.apigamereviewapp.entity.Type;
import pl.kozubek.apigamereviewapp.service.TypeService;

import java.util.List;

@RestController
@Data
public class TypeController {
    private final TypeService typeService;

    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/types")
    public List<Type> getAllTypes(){
        return typeService.getAllTypes();
    }

}
