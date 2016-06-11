package br.com.dedoduro.validation;

import br.com.dedoduro.model.ValidationError;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * Created by gasparbarancelli on 11/06/16.
 */
public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Falha na validação. " + errors.getErrorCount() + " erro(s)");
        for (ObjectError objectError : errors.getAllErrors()) {
            error.addValidationError(objectError.getDefaultMessage());
        }
        return error;
    }
}