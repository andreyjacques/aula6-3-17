package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;

@WebServlet(urlPatterns="/usucontroller")
public class UsuarioController extends  HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private List<Usuario> lista = new ArrayList<>();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		//Capturando o que vem do client
		Usuario usu = new Usuario(nome,email); 
		//Inserindo
		boolean achou=false;
		for(int i=0;i<lista.size();i++){
			if(lista.get(i).getEmail()==email){
				achou=true;
			}
		}
		if(achou==true){
			resp.getWriter().print("E-mail ja esta cadastrado");
		}else{
			lista.add(usu);
		}
		resp.getWriter().print("{Nome:"+usu.getNome()+"  Email:"+usu.getEmail()+"}");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String json ="[\n";
		for(int i=0;i<lista.size();i++){
			json += "{Nome:"+lista.get(i).getNome()+"  Email:"+lista.get(i).getEmail()+"}";
			if(i<lista.size()-1)
				json+=",\n";
		}
		json+="\n]";
		resp.getWriter().print(json);
	}
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String nome = req.getParameter("nome");
//		String email = req.getParameter("email");
//		for(int i=0;i<lista.size();i++){
//			if(lista.get(i).getNome()!=nome&&lista.get(i).getEmail()!=email){
//				lista.remove(i);
//				break;
//			}
//		}
//	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("posicao"));
		lista.remove(num);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int num = Integer.parseInt(req.getParameter("posicao"));
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		lista.get(num).setNome(nome);
		lista.get(num).setEmail(email);
	}
	
}
