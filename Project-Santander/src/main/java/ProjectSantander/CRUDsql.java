package ProjectSantander;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class CRUDsql {

    private JdbcTemplate jdbcTemplate;

    public void DemoApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CRUDsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void delete() {
        String sql = "delete from pessoas where id = ?";
        jdbcTemplate.update(sql, 5);
    }

    void update() {
        String sql = "update pessoas set nome = ? where id = ?";
        jdbcTemplate.update(sql, "Glauber", 6);
    }

    void insert() {
        String sql = "INSERT INTO produtos (id, nome, quantidade, categoria, price) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, 1, "Lapiz", 25, "Material Escolar", 1.30 );
    }

	void selectProdutos() {
		String sql = "SELECT * FROM PRODUTOS";

		RowMapper<Produto> rowMapper = ((rs, rowNum) -> new Produto(
				rs.getInt("ID"),
			    rs.getString("NOME"),
				rs.getInt("QUANTIDADE"),
                rs.getString("CATEGORIA"),
                rs.getBigDecimal("PRICE")
		));

		List<Produto> listaProdutos = jdbcTemplate.query(sql, rowMapper);

		listaProdutos.forEach(p -> {
			System.out.println(p.getNome());
		});
	}
}
