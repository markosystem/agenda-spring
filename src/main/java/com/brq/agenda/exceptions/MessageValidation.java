package com.brq.agenda.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

public class MessageValidation {
	private List<String> messages;

	public List<String> setMessagesConstraintViolations(ConstraintViolationException constraintViolationException) {
		Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations)
			getMessages().add(violation.getMessage());
		return getMessages();
	}

	public void setMessagesValidation(String msg) {
		getMessages().add(msg);
	}

	public List<String> getMessages() {
		return messages = messages == null ? new ArrayList<String>() : messages;
	}

}
