package org.example.projeto2.controllers.Admin;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.example.projeto2.models.Consulta;
import org.example.projeto2.models.Pagamento;
import org.example.projeto2.models.TipoPagamento;
import org.example.projeto2.services.ConsultaService;
import org.example.projeto2.services.PagamentoService;
import org.example.projeto2.services.TipoPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class VerEstatisticasController {

    /* ------------------- FXML ------------------- */
    @FXML private Label totalRealizadasLabel;
    @FXML private Label totalAgendadasLabel;
    @FXML private Label totalPagamentosLabel;
    @FXML private PieChart graficoTiposPagamento;
    @FXML private VBox estatisticasBox;

    /* ----------------- Services ------------------ */
    @Autowired private ConsultaService consultaService;
    @Autowired private PagamentoService pagamentoService;
    @Autowired private TipoPagamentoService tipoPagamentoService;

    /* -------------------- INIT ------------------- */
    @FXML
    public void initialize() {
        gerarEstatisticas();
    }

    /* =============== Lógica Principal =============== */
    private void gerarEstatisticas() {
        List<Consulta> consultas = consultaService.listarTodas();
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        List<TipoPagamento> tiposPagamento = tipoPagamentoService.listarTodos();

        // Total de consultas realizadas e agendadas
        long realizadas = consultas.stream()
                .filter(c -> c.getEstado().equalsIgnoreCase("realizada"))
                .count();

        long agendadas = consultas.stream()
                .filter(c -> c.getEstado().equalsIgnoreCase("agendada"))
                .count();

        // Total de valor dos pagamentos
        double totalPagamentos = pagamentos.stream()
                .mapToDouble(Pagamento::getValorDouble)
                .sum();

        // Atualizar labels
        totalRealizadasLabel.setText("Consultas Realizadas: " + realizadas);
        totalAgendadasLabel.setText("Consultas Agendadas: " + agendadas);
        totalPagamentosLabel.setText(String.format("Total de Pagamentos: %.2f €", totalPagamentos));

        // Contagem dos tipos de pagamento
        Map<Integer, Long> contagemPorTipo = pagamentos.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getTipoPagamento().getId(),
                        Collectors.counting()
                ));

        // Preparar dados para o gráfico de pizza
        graficoTiposPagamento.setData(FXCollections.observableArrayList(
                contagemPorTipo.entrySet().stream()
                        .map(entry -> {
                            TipoPagamento tipo = tiposPagamento.stream()
                                    .filter(tp -> tp.getId().equals(entry.getKey()))
                                    .findFirst()
                                    .orElse(null);

                            String nome = tipo != null ? tipo.getMetodo() : "Desconhecido";
                            return new PieChart.Data(nome, entry.getValue());
                        })
                        .toList()
        ));

        graficoTiposPagamento.setTitle("Distribuição dos Tipos de Pagamento");
    }

    /* ============== Botão (caso precises) ============== */
    @FXML
    public void handleGerarRelatorio() {
        gerarEstatisticas();  // Atualiza o gráfico e os totais
    }
}
