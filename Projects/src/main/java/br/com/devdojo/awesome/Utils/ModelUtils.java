package br.com.devdojo.awesome.Utils;

import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class ModelUtils {
    private final StudentRepository studentDAO;

    @Autowired
    public ModelUtils(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void verifyStudentExists(Long id) {
        if(studentDAO.findOne(id) == null)
            throw new ResourceNotFoundException("Student Not Found for ID: " + id);
    }


    public boolean isStudentEmpty(Student student){
        if(student == null || student.getId() == null || "".equals(student.getId())){
            return true;
        }
        return false;
    }
}
