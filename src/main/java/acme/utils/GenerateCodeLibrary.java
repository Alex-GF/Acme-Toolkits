package acme.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.moznion.random.string.RandomStringGenerator;


public class GenerateCodeLibrary {

	public static String generateCode(final List<String> codes, final String pattern) {
		String result = "";
		
		final Map<String,String> separatePattern = GenerateCodeLibrary.separatePattern(pattern);
		
		final RandomStringGenerator generator = new RandomStringGenerator();
		
		boolean repeatCode = true;

		while(repeatCode) {
			result = generator.generateByRegex(separatePattern.get("Principal"));
	
			final Random r = new Random();
			
			if(separatePattern.containsKey("Optional") && r.nextBoolean()) {
				result += generator.generateByRegex(separatePattern.get("Optional"));
			}
			
			repeatCode = codes.contains(result);
		}
			
		return result;
	}
	
	private static Map<String,String> separatePattern(final String pattern){
		final Map<String,String> result = new HashMap<>();
		
		final String pattern2 = pattern.replace("^", "").replace("$", "");
		
		if(pattern2.contains("?")) {
			final String[] p = pattern2.split("\\(");
			
			result.put("Principal", p[0].trim());
			result.put("Optional", p[1].replace(")?", "").trim());
			
		}else {
			result.put("Principal", pattern2);
		}
		
		return result;
	}
	
}
