package br.com.luizalabs.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.luizalabs.dao.AtividadeDAO;
import br.com.luizalabs.entidade.Atividade;

@Path("/atividades")
public class AtividadesService {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private AtividadeDAO atividadeDAO;
	
	@PostConstruct
	private void init() {
		atividadeDAO = new AtividadeDAO();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Atividade> listarAtividades() {
		List<Atividade> lista = null;
		try {
			lista = atividadeDAO.listarAtividades();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAtividade(Atividade atividade) {
		String msg = "";

		System.out.println(atividade.getTempo());

		try {
			atividadeDAO.addAtividade(atividade);

			msg = "Atividade add com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao add a atividade!";
			e.printStackTrace();
		}

		return msg;
	}
	
	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Atividade buscarPorId(@PathParam("id") int idAtividade) {
		Atividade atividade = null;
		try {
			atividade = atividadeDAO.buscarNotaPorId(idAtividade);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return atividade;
	}

	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarAtividade(Atividade atividade, @PathParam("id") int idAtividade) {
		String msg = "";
		
		System.out.println(atividade.getTempo());
		
		try {
			atividadeDAO.editarAtividade(atividade, idAtividade);
			
			msg = "Atividade editada com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar a atividade!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerAtividade(@PathParam("id") int idAtividade) {
		String msg = "";
		
		try {
			atividadeDAO.removerAtividade(idAtividade);
			
			msg = "Atividade removida com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover a atividade!";
			e.printStackTrace();
		}
		
		return msg;
	}
	
}
