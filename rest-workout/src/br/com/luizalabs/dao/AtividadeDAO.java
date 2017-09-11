package br.com.luizalabs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.luizalabs.config.BDConfig;
import br.com.luizalabs.entidade.Atividade;

public class AtividadeDAO {

	public List<Atividade> listarAtividades() throws Exception {
		List<Atividade> lista = new ArrayList<>();

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM atividadesrealizadas";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();

		while (rs.next()) {
			Atividade atividade = new Atividade();
			atividade.setId(rs.getInt("ID_ATIVIDADE"));
			atividade.setTempo(rs.getString("TEMPO"));
			atividade.setData(rs.getString("DATA"));
			atividade.setId_sports(rs.getInt("TIPOESPORTE"));

			lista.add(atividade);
		}

		return lista;
	}
	
	public Atividade buscarNotaPorId(int idAtividade) throws Exception {
		Atividade atividade = null;

		Connection conexao = BDConfig.getConnection();

		String sql = "SELECT * FROM atividadesrealizadas where id_AtividadesRealizadas = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idAtividade);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			atividade = new Atividade();
			atividade.setId(rs.getInt("ID_ATIVIDADE"));
			atividade.setTempo(rs.getString("TEMPO"));
			atividade.setData(rs.getString("DATA"));
			atividade.setId_sports(rs.getInt("TIPOESPORTE"));
		}

		return atividade;
	}

	public void addAtividade(Atividade atividade) throws Exception {
		int idGerado = 0;
		Connection conexao = BDConfig.getConnection();

		String sql = "INSERT INTO atividadesrealizadas (Tempo_AtividadesRealizadas, Data_AtividadesRealizadas, Sports_id_Sports) values (?, ?, ?)";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, atividade.getTempo());
		statement.setString(2, atividade.getData());
		statement.setInt(3, atividade.getId_sports());
		statement.execute();
	}
	
	public static void editarAtividade(Atividade atividade, int idAtividade) throws Exception {
		Connection conexao = BDConfig.getConnection();

		String sql = "UPDATE AtividadesRealizadas SET Tempo_AtividadesRealizadas = ?, Data_AtividadesRealizadas = ?, Sports_id_Sports = ? WHERE id_AtividadesRealizadas = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, atividade.getTempo());
		statement.setString(2, atividade.getData());
		statement.setInt(3, atividade.getId_sports());
		statement.setInt(4, idAtividade);
		statement.execute();
	}
	
	public void removerAtividade(int idAtividade) throws Exception {
		Connection conexao = BDConfig.getConnection();

		String sql = "DELETE FROM atividadesrealizadas Where id_AtividadesRealizadas = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idAtividade);
		statement.execute();
	}
	
}
