package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class JSONConverter {

	public static void sendObjectAsJson(
			HttpServletResponse response, 
			Object obj) throws IOException {
			PrintWriter out = response.getWriter();

			if(obj == null) {
				response.sendError(404, "L'objet n'a pas été trouvé.");
			}else {
				
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				String res = JSONConverter.convert(obj);  
				out.print(res);
			}
			out.flush();
			
	}
	
	public static <T> T deserialize(InputStream stream, Class<T> genericClass)
	{
		if(stream==null)
			return null;
		
		try {
			BufferedReader br = new BufferedReader(new  InputStreamReader(stream));
		    String json = "";
		    if(br != null){
		        json = br.readLine();
		    }
		    
		    if(json==null)
		    	return null;

		    ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		    return mapper.readValue(json, genericClass);
		    
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	
	/** Conversion direct des attributs de l'objet*/
	public static String convert(Object obj)
	{
		if(obj==null)
			return "{}";
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);	
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writeValueAsString(obj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		    return "{}";
		}
	}
	
	/** Conversion avec une règle*/
	public static String convertWithMixin(Object obj,Class<?> mixin)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.addMixIn(obj.getClass(),mixin);
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writeValueAsString(obj);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		    return "{}";
		}
	}
	
	/** Conversion direct avec des champs dynamiques en param*/
	public static String convert(Object obj, Map<String, Object> newFields)
	{
		if(obj==null)
			return "{}";
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		//mapper.setVisibility(JsonMethod.FIELD, Visibility.ANY);	
		
		for(Map.Entry<String, Object> entry : newFields.entrySet())
		{
			try {
				ObjectNode json = (ObjectNode) mapper.readTree(convert(obj));
				
				json.set(entry.getKey(),mapper.readTree(convert(entry.getValue())));
				
				return json.toString();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "{}";
	}
	
	/** Conversion en JSON en gardant le format JSON*/
	public static ObjectNode convertKeepJSONFormat(Object obj)
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		try {
			
			if(obj==null)
				return (ObjectNode) mapper.readTree("{}");
			
			ObjectNode json = (ObjectNode) mapper.readTree(convert(obj));
			
			return json;
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		    return null;
		}
	}
	
	/** Conversion en JSON en gardant le format JSON + champs dynamiques*/
	public static ObjectNode convertKeepJSONFormat(Object obj, Map<String, Object> newFields)
	{		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		try
		{
			if(obj==null)
				return (ObjectNode) mapper.readTree("{}");
	
			for(Map.Entry<String, Object> entry : newFields.entrySet())
			{
				try {
					ObjectNode json = (ObjectNode) mapper.readTree(convert(obj));
					
					json.set(entry.getKey(),mapper.readTree(convert(entry.getValue())));
					
					return json;
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			return (ObjectNode) mapper.readTree("{}");
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
