package com.learn.springbootswagger.domain.exception;


import static com.learn.springbootswagger.domain.exception.ErrorCommon.EXAMPLE_NOT_FOUND_ERROR;

public class ExampleNotFoundException extends ExceptionCommon{
    public ExampleNotFoundException() {
        super(EXAMPLE_NOT_FOUND_ERROR);
    }

    public ExampleNotFoundException(String message) {
        super(EXAMPLE_NOT_FOUND_ERROR, message);
    }
}