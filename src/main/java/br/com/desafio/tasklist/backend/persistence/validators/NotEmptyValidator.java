package br.com.desafio.tasklist.backend.persistence.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.desafio.tasklist.backend.persistence.anotations.NotEmpty;

public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {

	@Override
	public void initialize(NotEmpty constraintAnnotation) {
	
	}

	@Override
	public boolean isValid(String valor, ConstraintValidatorContext context) {
		return !valor.trim().isEmpty() || valor != null;
	}
}