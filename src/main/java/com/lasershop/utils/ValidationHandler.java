package com.lasershop.utils;

import com.lasershop.models.requisicao.Erro;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Erro> handleException(MethodArgumentNotValidException e) {
          Erro errors = new Erro();
          e.getAllErrors().forEach(objectError -> errors.getErros().add(objectError.getDefaultMessage()));
          return ResponseEntity.badRequest().body(errors);
    }

}
