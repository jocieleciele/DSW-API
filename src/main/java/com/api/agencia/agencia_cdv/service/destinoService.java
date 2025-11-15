package com.api.agencia.agencia_cdv.service;

import com.api.agencia.agencia_cdv.model.destino;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

 
@Service
public class destinoService {

    // Simulação do banco de dados (In-memory storage)
    private final List<destino> destinos = new ArrayList<>();

    // inicializar alguns destinos de exemplo
    public destinoService() {
        destinos.add(new destino("Praia do Forte", "Bahia, Brasil", "Uma bela praia com águas cristalinas e natureza exuberante."));
        destinos.add(new destino("Machu Picchu", "Peru", "Ruínas incas localizadas nas montanhas dos Andes, um dos destinos mais icônicos da América do Sul."));
        destinos.add(new destino("Paris", "França", "A cidade do amor, famosa por seus monumentos históricos, arte e gastronomia."));
        
    }

    // 1.Cadastrar Destino
    public destino cadastrar(destino novoDestino) {
        // O ID é gerado no construtor de Destino
        destinos.add(novoDestino);
        return novoDestino;
    }

    // 2. Listar todos os Destinos
    public List<destino> listarTodos() {
        return new ArrayList<>(destinos);
    }

    // 3. Pesquisar Destinos por nome ou localização
    public List<destino> buscarPorTermo(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            return listarTodos();
        }
        String termoLowerCase = termo.trim().toLowerCase();
        return destinos.stream()
                .filter(d -> d.getNome().toLowerCase().contains(termoLowerCase) ||
                             d.getLocalizacao().toLowerCase().contains(termoLowerCase))
                .collect(Collectors.toList());
    }

    // 4. Visualizar Informações Detalhadas (por ID)
    public Optional<destino> buscarPorId(Long id) {
        return destinos.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    /// 5. Avaliar Destino (Lógica de PATCH/PUT)
    public Optional<destino> avaliarDestino(Long id, int nota) {
        Optional<destino> destinoOptional = buscarPorId(id);

        if (destinoOptional.isPresent()) {
            destino destino = destinoOptional.get();
            try {
                destino.atualizarAvaliacao(nota);
                // Retorna o destino atualizado
                return Optional.of(destino);
            } catch (IllegalArgumentException e) {
                // Em um ambiente real, você logaria o erro. Aqui, apenas retorna vazio.
                return Optional.empty(); 
            }
        }
        return Optional.empty(); // Destino não encontrado
    }

    // 6. Excluir Destino
    public boolean excluir(Long id) {
        // RemoveIf retorna true se um ou mais elementos foram removidos
        return destinos.removeIf(d -> d.getId().equals(id));
    }
}
