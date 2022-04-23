package acme.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;

@Service
public class DashboardLibrary {

	public void createDashboard(final Serializable entity, final Model model) {
		final List<String> items = new ArrayList<>();
		final ScriptEngineManager manager = new ScriptEngineManager();
		final ScriptEngine engine = manager.getEngineByName("js");
		final List<String> methods = model.getAttributes().stream().collect(Collectors.toList());
		engine.put("items", items);
		engine.put("entity", entity);
		try {
			for(int i=0; i<model.getAttributes().size(); i++) {
				String method = methods.get(i);
				method = Character.toUpperCase(method.charAt(0)) + method.substring(1);
				engine.eval("items.add(entity.get" +  method + "().toString());");
			}
		} catch (final ScriptException e) {
			e.printStackTrace();
		}
		model.setAttribute("methods", methods);
		model.setAttribute("items", items);
	}
	
}
