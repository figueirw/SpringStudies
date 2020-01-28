package br.com.devdojo.awesome.javaclient;

import br.com.devdojo.awesome.model.Student;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class JavaSpringClientTest {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:8181/v1/protected/students")
                .basicAuthorization("toyo", "devdojo").build();

        Student studentObject = restTemplate.getForObject("/{id}", Student.class, 41);
        ResponseEntity<Student> studentResponseEntity = restTemplate.getForEntity("/{id}", Student.class, 41);
        System.out.println(studentObject);
        System.out.println(studentResponseEntity.getBody());

        Student[] studentsArray = restTemplate.getForObject("/", Student[].class);
        System.out.println(Arrays.toString(studentsArray));

        ResponseEntity<List<Student>> studentResponseEntityExchange = restTemplate.exchange("/", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Student>>() {
                });

        System.out.println(studentResponseEntityExchange.getBody());

    }
}
