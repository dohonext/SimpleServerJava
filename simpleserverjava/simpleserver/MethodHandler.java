package simpleserver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import simpledb.DatabaseManager;
import simpledb.QueryStrings;

public class MethodHandler {
	HttpServletRequest request; 
	HttpServletResponse response;

	public MethodHandler(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	// [Core methods]
	private void responseWriter(String json) throws IOException{
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().print(json);
	}

	// : /user
	public void userGet() throws IOException{
		if (request.getParameter("userId") != null){
			userGetById();
		}else {
			//userGetByNone();
		}
	}
	private void userGetById() throws IOException{     
		DatabaseManager db = new DatabaseManager();
		db.setQuery(QueryStrings.getUserGetByIdQuery());       
		db.setConditions(request.getParameter("userId"));  
		responseWriter(db.select());    
	}
	
}
