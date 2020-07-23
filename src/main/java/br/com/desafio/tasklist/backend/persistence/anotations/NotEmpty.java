package br.com.desafio.tasklist.backend.persistence.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.desafio.tasklist.backend.persistence.validators.NotEmptyValidator;

@Constraint(validatedBy = NotEmptyValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
	
	String message() default "Este Campo Não pode ser vazio";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}