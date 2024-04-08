package ProjectSantander;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.*;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LeitorDeCSV leitor = new LeitorDeCSV();
		CRUDsql crud = new CRUDsql(new JdbcTemplate());

		List<Produto> produtos = leitor.openCsv();
		leitor.countCategorias(produtos);
		leitor.valorMedio(produtos);
		leitor.estoqueBaixo(produtos);
		leitor.countProdutosPorCategoria(produtos);
	}
}
