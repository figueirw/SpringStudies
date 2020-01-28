package br.com.devdojo.awesome.endpoint;

import br.com.devdojo.awesome.Utils.ModelUtils;
import br.com.devdojo.awesome.error.CustomErrorType;
import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.model.Student;
import br.com.devdojo.awesome.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1")
public class StudentEndpoint {
    private final StudentRepository studentDAO;

    @Autowired
    public StudentEndpoint(StudentRepository studentDAO) {
        this.studentDAO = studentDAO;
    }
    
    @Autowired
    ModelUtils modelUtils;

    @GetMapping("protected/students")
    public ResponseEntity<?> listALL(Pageable pageable) {
        return new ResponseEntity<>(studentDAO.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "protected/students/{id}")
    public ResponseEntity<?> findStudentById(@PathVariable("id") Long id,
                                             @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("\n TestUser: " + userDetails);
        modelUtils.verifyStudentExists(id);
        Student student = studentDAO.findOne(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("protected/students/findByName/{name}")
    public ResponseEntity<?> findStudentsbyName(@PathVariable("name") String name){
        return new ResponseEntity<>(studentDAO.findByNameIgnoreCaseContaining(name), HttpStatus.OK);
    }


    @PostMapping("admin/students")
    public ResponseEntity<?> save(@Valid @RequestBody Student student) {
        return new ResponseEntity<>(studentDAO.save(student), HttpStatus.CREATED);
    }

    @DeleteMapping("admin/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        modelUtils.verifyStudentExists(id);
        studentDAO.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("admin/students")
    public ResponseEntity<?> update(@RequestBody Student student) {
            modelUtils.verifyStudentExists(student.getId());
            studentDAO.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
