package br.com.boot.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationErrorHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> handle(MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        return fieldErrors.stream().map(this::getErrorDTO).collect(Collectors.toList());
    }

    private ErrorDTO getErrorDTO(FieldError fieldError) {
        String mensagem = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
        return new ErrorDTO(fieldError.getField(),mensagem);
    }

}
