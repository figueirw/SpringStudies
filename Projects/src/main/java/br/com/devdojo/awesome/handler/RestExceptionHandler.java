package br.com.devdojo.awesome.handler;


import br.com.devdojo.awesome.error.ErrorDetails;
import br.com.devdojo.awesome.error.ResourceNotFoundDetails;
import br.com.devdojo.awesome.error.ResourceNotFoundException;
import br.com.devdojo.awesome.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.util.*;
import java.util.stream.Collectors;

//ResponseEntityExceptionHandler classe do spring responsavel por todas as exceptions
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /*
    configurando a excecao ResourceNotFoundException
    para customizar o resultado
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rfnException) {
        ResourceNotFoundDetails rfnDetails = ResourceNotFoundDetails.Builder
                .newBuilder()
                .withTimeStamp(new Date().getTime())
                .withStatus(HttpStatus.NOT_FOUND.value())
                .withTitle("Resource not found")
                .withDetail(rfnException.getMessage())
                .withDeveloperMessage(rfnException.getClass().getName())
                .build();

        return new ResponseEntity<>(rfnDetails, HttpStatus.NOT_FOUND);
    }


    private Map<String, List<String>> buildErrorsMap(List<FieldError> fieldErrors) {
        Map<String, List<String>> result =
                fieldErrors.stream().collect(
                        Collectors.groupingBy(FieldError::getField,
                                Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())
                        )
                );
        return result;
    }

    /*
    configurando a exception MethodArgumentNotValidException
    para trazer um hash com os parametros invalidos e as excessoes
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException manvException, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldError> fieldErrors = manvException.getBindingResult().getFieldErrors();

        Map<String, List<String>> stringListHashMap = buildErrorsMap(fieldErrors);

        ValidationErrorDetails veDetails = ValidationErrorDetails.Builder
                .newBuilder()
                .withTimeStamp(new Date().getTime())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withTitle("Field validation Error")
                .withDetail("Field validation Error")
                .withDeveloperMessage(manvException.getClass().getName())
                .withField(stringListHashMap)
                .build();

        return new ResponseEntity<>(veDetails, HttpStatus.BAD_REQUEST);
    }

    //todos as exception iram chamar essa classe padrao de erro
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers, HttpStatus status, WebRequest request) {

        ErrorDetails errorDetails = ErrorDetails.ErrorDetailsBuilder
                .newBuilder()
                .timeStamp(new Date().getTime())
                .status(status.value())
                .title("Internal Exception")
                .detail(ex.getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(errorDetails, headers, status);
    }
}
