package com.example.Project.Santander;

import org.springframework.jdbc.core.JdbcTemplate;

public class CRUDsql {

    private JdbcTemplate jdbcTemplate;

    public void DemoApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CRUDsql(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private void delete() {
        String sql = "delete from pessoas where id = ?";
        jdbcTemplate.update(sql, 5);
    }

    private void update() {
        String sql = "update pessoas set nome = ? where id = ?";
        jdbcTemplate.update(sql, "Glauber", 6);
    }

    private void insert() {
        String sql = "INSERT INTO pessoas (id, nome, idade) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, 6, "Glauber", 25);
    }

//	private void selectPessoas() {
//		String sql = "SELECT * FROM PESSOAS";
//
//		RowMapper<Pessoa> rowMapper = ((rs, rowNum) -> new Pessoa(
//				rs.getInt("ID"),
//				rs.getString("NOME"),
//				rs.getInt("IDADE")
//		));
//
//		List<Pessoa> listaPessoas = jdbcTemplate.query(sql, rowMapper);
//
//		listaPessoas.forEach(p -> {
//			System.out.println(p.getNome());
//		});
//	}
}
