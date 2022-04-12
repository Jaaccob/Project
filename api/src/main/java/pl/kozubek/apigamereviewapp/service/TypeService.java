package pl.kozubek.apigamereviewapp.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.kozubek.apigamereviewapp.entity.Type;
import pl.kozubek.apigamereviewapp.repository.TypeRepository;


import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class TypeService {

    private final TypeRepository typeRepository;

    public TypeService(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    public List<Type> getAllTypes() {
        List<Type> types = new ArrayList<>();
        typeRepository.findAll().forEach(types::add);
        return types;
    }
}
