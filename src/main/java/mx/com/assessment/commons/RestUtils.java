package mx.com.assessment.commons;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class RestUtils {
	
	@SuppressWarnings("rawtypes")
	public ResponseEntity errorMessage(final HttpStatus code, final String message) {
        Map<String, String> error = new LinkedHashMap<String, String>();
        error.put("error", "" + code.value());
        error.put("error_description", message);
        return ResponseEntity.status(code).body(error);
    }
	
}
