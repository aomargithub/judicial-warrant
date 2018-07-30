package com.informatique.gov.judicialwarrant.support.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;

import java.io.IOException;

public class JudicialWarrantExceptionSerializer extends StdSerializer<JudicialWarrantException> {

	private static final long serialVersionUID = 4633975656374847723L;

	public JudicialWarrantExceptionSerializer() {
		this(null);
	}

	public JudicialWarrantExceptionSerializer(Class<JudicialWarrantException> t) {
		super(t);
	}

	protected JudicialWarrantExceptionSerializer(Class<JudicialWarrantException> t, boolean dummy) {
		super(t, dummy);
	}

	@Override
	public void serialize(JudicialWarrantException judicialWarrantException, JsonGenerator jsonGenerator,
			SerializerProvider serializerProvider) throws IOException {

		jsonGenerator.writeStartObject();
		if (judicialWarrantException instanceof JudicialWarrantInternalException) {
			jsonGenerator.writeNumberField("errorId",
					judicialWarrantException.getErrorId() == null ? 0 : judicialWarrantException.getErrorId());
			jsonGenerator.writeStringField("errorCode",
					judicialWarrantException.getCode() == null ? "" : judicialWarrantException.getCode());
			jsonGenerator.writeStringField("errorDescription",
					judicialWarrantException.getDescription() == null ? "" : judicialWarrantException.getDescription());
			jsonGenerator.writeStringField("fixSuggestion", judicialWarrantException.getFixSuggestion() == null ? ""
					: judicialWarrantException.getFixSuggestion());
			jsonGenerator.writeStringField("requestId", judicialWarrantException.getRequestId() == null ? ""
					: judicialWarrantException.getRequestId().toString());
			jsonGenerator.writeStringField("userLoginName", judicialWarrantException.getUserDomainName() == null ? ""
					: judicialWarrantException.getUserDomainName());

		} else {
			jsonGenerator.writeStringField("errorDescription",
					judicialWarrantException.getDescription() == null ? "" : judicialWarrantException.getDescription());
			jsonGenerator.writeStringField("fixSuggestion", judicialWarrantException.getFixSuggestion() == null ? ""
					: judicialWarrantException.getFixSuggestion());
			jsonGenerator.writeStringField("requestId", judicialWarrantException.getRequestId() == null ? ""
					: judicialWarrantException.getRequestId().toString());

		}
		jsonGenerator.writeEndObject();
	}
}
