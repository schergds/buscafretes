package schergds.com.buscafretes.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import schergds.com.buscafretes.entity.Freight;
import schergds.com.buscafretes.entity.User;
import schergds.com.buscafretes.entity.enums.StatusFrete;
import schergds.com.buscafretes.entity.enums.TipoCaminhao;
import schergds.com.buscafretes.service.FreightService;
import schergds.com.buscafretes.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;
    private final FreightService freightService;

    public DataLoader(UserService userService, FreightService freightService) {
        this.userService = userService;
        this.freightService = freightService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findByEmail("admin@buscafretes.com").isEmpty()) {
            User admin = new User();
            admin.setNome("Administrador");
            admin.setEmail("admin@buscafretes.com");
            admin.setTelefone("11999999999");
            admin.setSenha("admin123");
            userService.registerUser(admin);

            User adminSaved = userService.findByEmail("admin@buscafretes.com").get();

            createFreight("Soja a granel", "Transporte de soja de MT para SP", "Sorriso, MT", "Santos, SP", new BigDecimal("12000.00"), TipoCaminhao.RODOTREM, "37 Toneladas", adminSaved);
            createFreight("Peças automotivas", "Carga paletizada, urgente", "Betim, MG", "São Paulo, SP", new BigDecimal("3500.00"), TipoCaminhao.TRUCK, "12 Toneladas", adminSaved);
            createFreight("Eletrodomésticos", "Carga frágil", "Manaus, AM", "Goiânia, GO", new BigDecimal("18000.00"), TipoCaminhao.CARRETA, "25 Toneladas", adminSaved);
            createFreight("Mudança residencial", "Móveis e caixas", "Curitiba, PR", "Florianópolis, SC", new BigDecimal("1500.00"), TipoCaminhao.VAN, "1.5 Toneladas", adminSaved);
            createFreight("Bobinas de Aço", "Carga pesada", "Volta Redonda, RJ", "Joinville, SC", new BigDecimal("8500.00"), TipoCaminhao.BITREM, "40 Toneladas", adminSaved);
        }
    }

    private void createFreight(String titulo, String descricao, String origem, String destino, BigDecimal valor, TipoCaminhao tipo, String peso, User user) {
        Freight f = new Freight();
        f.setTitulo(titulo);
        f.setDescricao(descricao);
        f.setOrigem(origem);
        f.setDestino(destino);
        f.setValor(valor);
        f.setTipoCaminhao(tipo);
        f.setPeso(peso);
        f.setTelefoneContato(user.getTelefone());
        f.setWhatsapp("55" + user.getTelefone());
        f.setDataColeta(LocalDate.now().plusDays(3));
        f.setStatus(StatusFrete.ABERTO);
        f.setUser(user);
        freightService.save(f);
    }
}
