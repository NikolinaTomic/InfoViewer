package controller;

import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.Validator;
import org.json.JSONArray;
import org.json.JSONObject;

public class ValidatorTest {
	private Schema schema;
	private JSONArray input;
	
	public ValidatorTest(Schema schema, JSONArray input) {
		this.schema = schema;
		this.input = input;
	}

	public String validateFailEarly() {
		try {
			Validator validator = Validator.builder()
					.failEarly()
					.build();
			validator.performValidation(schema, input);
			return "";
		} catch (ValidationException e) {
			System.out.println(e.getMessage());
			return e.getAllMessages().toString();
		}
	}
	
	public String validateJsonDetails() {
		try {
			schema.validate(input);
			return "";
		} catch (ValidationException e) {
			System.out.println(e.toJSON());
			return e.getAllMessages().toString();
		} 
		
		
	}
	
	public String validateAll() {
		try {
			schema.validate(input);
			return "";
		} catch (ValidationException e) {
			System.out.println(e.getAllMessages());
			return e.getAllMessages().toString();
		} 
	}

}
