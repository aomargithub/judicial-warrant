package com.informatique.gov.judicialwarrant.support.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;

import java.io.IOException;

public class JudicialWarrantExceptionSerializer  extends StdSerializer<JudicialWarrantException> {

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
    public void serialize(JudicialWarrantException ketabException, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("errorId", ketabException.getErrorId() == null ? 0 : ketabException.getErrorId());
        jsonGenerator.writeStringField("errorCode", ketabException.getCode() == null ? "" : ketabException.getCode());
        jsonGenerator.writeStringField("errorDescription", ketabException.getDescription() == null ? "" : ketabException.getDescription());
        jsonGenerator.writeStringField("fixSuggestion", ketabException.getFixSuggestion() == null ? "" : ketabException.getFixSuggestion());
        jsonGenerator.writeStringField("requestId", ketabException.getRequestId() == null ? "" : ketabException.getRequestId().toString());
        jsonGenerator.writeStringField("userLoginName", ketabException.getUserDomainName() == null ? "" : ketabException.getUserDomainName());
        jsonGenerator.writeEndObject();
    }
}
