package com.pizza.exception;

import com.pizza.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorDto handleNotFound(Exception exception) {
        return handleBaseException(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequest(Exception exception) {
        return handleBaseException(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleMethodNotSupported(Exception exception) {
        return handleBaseException(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorDto handleBadRequestValidation(Exception exception) {
        MethodArgumentNotValidException notValidException = (MethodArgumentNotValidException) exception;
        List<ObjectError> allErrors = notValidException.getBindingResult().getAllErrors();
        StringBuilder message = new StringBuilder();
        for (ObjectError objectError : allErrors) {
            message.append(objectError.getDefaultMessage());
            message.append(";");
        }

        return new ErrorDto("failed.validation.for.request.body", message.toString(),
                HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(Exception exception) {
        logger.error("An error occurred", exception);

        return new ErrorDto("internal.server.error", "An error occurred.",
                HttpStatus.INTERNAL_SERVER_ERROR.value());
    }

    private ErrorDto handleBaseException(Exception exception, HttpStatus status) {
        BaseException baseException = (BaseException) exception;
        logger.warn(exception.getMessage(), exception);

        return new ErrorDto(baseException.getErrorCode(),
                baseException.getMessage(), status.value());
    }
}
