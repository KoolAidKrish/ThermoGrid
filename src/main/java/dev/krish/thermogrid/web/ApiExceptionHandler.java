package dev.krish.thermogrid.web;

import dev.krish.thermogrid.service.RegionNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Arrays;
import java.util.stream.Collectors;

/** Turns bad input into RFC 7807 problem details instead of stack traces. */
@RestControllerAdvice
public class ApiExceptionHandler {

    /** e.g. {@code ?minRiskLevel=SEVERE} or {@code ?targetYear=abc}. */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ProblemDetail badParam(MethodArgumentTypeMismatchException ex) {
        String detail = "Invalid value '" + ex.getValue() + "' for '" + ex.getName() + "'.";
        Class<?> type = ex.getRequiredType();
        if (type != null && type.isEnum()) {
            detail += " Use one of " + Arrays.toString(type.getEnumConstants()) + ".";
        }
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    }

    /** e.g. {@code ?targetYear=1850}. */
    @ExceptionHandler(ConstraintViolationException.class)
    ProblemDetail outOfRange(ConstraintViolationException ex) {
        String detail = ex.getConstraintViolations().stream()
            .map(v -> parameterName(v) + ": " + v.getMessage())
            .sorted()
            .collect(Collectors.joining("; "));
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, detail);
    }

    /** e.g. {@code /api/v1/regions/99/vulnerabilities}. */
    @ExceptionHandler(RegionNotFoundException.class)
    ProblemDetail regionNotFound(RegionNotFoundException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    /** The violation path is "method.param"; clients only care about the param. */
    private static String parameterName(ConstraintViolation<?> violation) {
        Path.Node last = null;
        for (Path.Node node : violation.getPropertyPath()) {
            last = node;
        }
        return last == null ? "request" : last.getName();
    }
}
