package com.librarymanagement.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.librarymanagement.enums.BookCategory;
import com.librarymanagement.enums.OurUserRole;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {


    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handleDataNotFoundException(DataNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }
    @ExceptionHandler(PermissionException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage handlePermissionException(PermissionException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }

    /* // bu code aktif olduğunda doğru error mesajı veriyor ama status 500 dönüyor
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return errorMessage;
    }
*/

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String error = "Invalid argument type: " + ex.getName();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }





    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpServletRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Geçersiz Veri");

        String errorMessage = ex.getMessage();

        // Rol hatası olup olmadığını kontrol edin
        if (errorMessage.contains("roles")) {
            errorResponse.setErrorMessage("Geçersiz Rol Değeri");
            errorResponse.setDetails("Girdiğiniz rol değeri enumda bulunamadı. Kabul Edilen Değerler: " + Arrays.toString(OurUserRole.values()));
        }
        // Kategori hatası olup olmadığını kontrol edin
        else if (errorMessage.contains("category")) {
            errorResponse.setErrorMessage("Geçersiz Kategori Değeri");
            errorResponse.setDetails("Girdiğiniz kategori değeri enumda bulunamadı. Kabul Edilen Değerler: " + Arrays.toString(BookCategory.values()));
        }
        // Diğer hatalar için genel bir mesaj gösterin
        else {
            errorResponse.setErrorMessage("Hata Oluştu");
            //errorResponse.setDetails(ex.getMessage()); bu enumla birlikte bütün mesajı dönderir
            errorResponse.setDetails("geçersiz rol");
        }

        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }


}