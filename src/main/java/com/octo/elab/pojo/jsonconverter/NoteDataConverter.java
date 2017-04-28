package com.octo.elab.pojo.jsonconverter;

import javax.persistence.AttributeConverter;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.octo.elab.pojo.reflection.NoteData;

public class NoteDataConverter implements AttributeConverter<NoteData, String> {
	private final static ObjectMapper objectMapper = new ObjectMapper();

	@Override
	@NotNull
	public String convertToDatabaseColumn(NoteData noteData) {
		try {
			return objectMapper.writeValueAsString(noteData);
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	@NotNull
	public NoteData convertToEntityAttribute(String noteDataStr) {
		try {
			return objectMapper.readValue(noteDataStr, NoteData.class);
		} catch (Exception ex) {
			return null;
		}
	}

}
