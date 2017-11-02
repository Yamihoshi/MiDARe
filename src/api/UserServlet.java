package api;

import java.io.IOException;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generated.Account;
import generated.AccountHome;
import modelData.User;
import tools.JSONConverter;
import tools.URLParser;

@WebServlet("/user/*")
public class UserServlet extends Endpoint {
	private static final long serialVersionUID = 1L;
	
	private static final String USER_URL="^/[1-9][0-9]*";
	private static final String USER_BETS_URL= USER_URL + "/bets";
    	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String url = request.getPathInfo();
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 
		
		 if(url==null || url.isEmpty())
		 {
			 //GET : api/user
			 
			 EntityManagerFactory emf = Persistence.createEntityManagerFactory("AccountHome");
			 EntityManager em = emf.createEntityManager();
			    AccountHome service = new AccountHome(em);

			    em.getTransaction().begin();
			    service.persist(new Account("User", "Pass"));
			    em.getTransaction().commit();
			    
			    em.close();
			    emf.close();
			 
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 response.getWriter().write("FETCH LA LIST");
		 }
		 else if(url.matches(USER_URL))
		 {
			 //GET : api/user/{id}
			 
			 int id = URLParser.getParameterOfURL(url,1);
				
			 if(id!=-1)
			 {	
				 HashMap<String,Object> newFields = new HashMap<String,Object>();
				 User a = new User();
				 a.setUsername("Billy");
				 
				 newFields.put("other_user",a);
				 
				 String data = JSONConverter.convert(a, newFields);
				 
				 response.getWriter().write(data);
			 }
			 else
			 {
				 response.sendError(404,"MAFORMATED URL");
			 }
		 }
		 else if(url.matches(USER_BETS_URL))
		 {
			//GET : api/user/{id}/bets
			 
			 int id = URLParser.getParameterOfURL(url,1);
				
			 if(id!=-1)
			 {			 
				 response.setContentType("text/plain");
				 response.setCharacterEncoding("UTF-8");
				 response.getWriter().write("BETS FROM "+id);
			 }
		 }
		 else
		 {
			 response.setContentType("text/plain");
			 response.setCharacterEncoding("UTF-8");
			 
			 response.getWriter().write("MAFORMATED");
		 }
		
	}

}
