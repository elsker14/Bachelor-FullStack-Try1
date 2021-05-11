package com.in28minutes.SpringMvc.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service = new TodoService();
	
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dataFormat, false));
	}
	
	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {
		model.addAttribute("todos", service.retrieveTodos("Checu"));
		return "list-todos";
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo(0, "Checu", "", new Date(), false));
		return "todo";	//trimite un string catre View Resolver, acesta adaugand .jsp si utilizand view-ul respectiv
	}
	
	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result){
		//@Valid = anotatie pt hibernate validator, care prin asta va sti sa se 
		//uite in clasa la constraintul pus pe size si va da mesaj daca e nasoale
		if(result.hasErrors())	//acest result de va spune ca daca exista erori legate de validare, marsh inapoi la pagina todo 
		{
			return "todo"; 
		}
		service.addTodo("Checu", todo.getDesc(), new Date(), false);
		model.clear();
		return "redirect:list-todos";	
	}
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		//Delete To Do
		service.deleteTodo(id);
		model.clear();
		return "redirect:list-todos";	
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(ModelMap model, @RequestParam int id) {	//ID ul vom primi ca parametru pe request
		//Update To Do
		Todo todo = service.retrieveTodo(id);	//Cautam obiectul dupa id
		model.addAttribute("todo", todo);		//Bagam obiectul in model sa fie vizibil si in jsp
		return "todo";		
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		//Update To Do
		if(result.hasErrors())	//validare
		{
			return "todo"; 
		}
		todo.setUser("Checu");
		service.updateTodo(todo);
		
		model.clear();				//to prevent request parameter "name" to be passed
		return "redirect:list-todos";		
	}
}   
