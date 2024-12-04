package com.clintonbrito.squadraproject.common;

import com.clintonbrito.squadraproject.common.exception.OperacaoNaoPermitidaException;
import com.clintonbrito.squadraproject.common.exception.RegistroDuplicadoException;
import com.clintonbrito.squadraproject.common.exception.RegistroNaoEncontradoException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<ErroCampo> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErroCampo(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação.",
                listaErros);
    }

    @ExceptionHandler(RegistroDuplicadoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException e) {
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(RegistroNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErroResposta handleRegistroNaoEncontradoException(RegistroNaoEncontradoException e) {
        return ErroResposta.conflito(e.getMessage());
    }

    @ExceptionHandler(OperacaoNaoPermitidaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException e) {
        return ErroResposta.respostaPadrao(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        String errorMessage = String.format("O valor de tipo '%s' não é válido para o campo '%s'. Este campo deve ser do tipo '%s'.",
                e.getValue().getClass().getSimpleName(), e.getName(), e.getRequiredType().getSimpleName());
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação.",
                List.of(new ErroCampo(e.getName(), errorMessage))
        );
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleUnexpectedTypeException(UnexpectedTypeException e) {
        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação.",
                List.of(new ErroCampo(e.getMessage(), e.getMessage()))
        );
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroResposta handleHandlerMethodValidationException(HandlerMethodValidationException e) {
        List<ErroCampo> listaErros = e.getAllValidationResults().stream()
                .flatMap(vr -> vr.getResolvableErrors().stream())
                .map(violation -> {
                        String rawFieldName = violation.getCodes() != null && violation.getCodes().length > 0
                        ? violation.getCodes()[0]
                        : "campo desconhecido";

                    String fieldName = rawFieldName.replaceAll("^.*\\.", "");

                    return new ErroCampo(fieldName, violation.getDefaultMessage());
    })
            .collect(Collectors.toList());

        return new ErroResposta(
                HttpStatus.BAD_REQUEST.value(),
                "Erro de validação.",
                listaErros);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErroResposta handleErrosNaoTratados(RuntimeException e) {
        System.out.println(e.getMessage());
        return new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro inesperado. Favor entrar em contato com o Suporte de TI.",
                List.of());
    }
}
