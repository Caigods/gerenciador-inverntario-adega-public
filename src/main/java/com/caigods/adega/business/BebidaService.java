package com.caigods.adega.business;

import com.caigods.adega.infrastructure.entitys.Bebida;
import com.caigods.adega.infrastructure.repository.BebidaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor //Usado para injetar via método Lombok
public class BebidaService {
    private final BebidaRepository bebidaRepository;

    public List<Bebida> listarTudo() {
        return bebidaRepository.findAll();
    }

    @Transactional      //Garante se algo der errado, nada será salvo no banco de dados
    public Bebida salvarBebida(Bebida bebida) {
        if (bebida.getVolumeMl() != null && bebida.getVolumeMl() <= 0) {
            throw new RuntimeException("volume deve ser maior do que zero!");
        } else {
            return bebidaRepository.saveAndFlush(bebida);
        }
    }

    public Bebida buscarPorID(Integer id) {
        return bebidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bebida nao encontrada"));
    }

    public List<Bebida> buscarPorNome(String nome) {
        //return repository.findByNome(nome);  // Em uma lista não precisa colocar excessão, pois se nao achar, ela retorna vazia []
        List<Bebida> resultado = bebidaRepository.findByNomeContainingIgnoreCase(nome); //assim armazeno e resultado e consigo fazer a excessão
        if (resultado.isEmpty()) {
            throw new RuntimeException("Nenhuma bebida foi encontrada");
        }
        return resultado;
    }

    @Transactional
    public void deletarBebidaPorId(Integer id) {
        Bebida bebida = buscarPorID(id);
        bebidaRepository.delete(bebida);
    }

    public List<Bebida> BuscarPorAno(Integer anoFabricacao) {
        if (anoFabricacao <= 1500) {
            throw new RuntimeException("Ano invalido de cachaça");
        }
        return bebidaRepository.findByAnoFabricacao(anoFabricacao);
    }

    public List<Bebida> buscarPorStatusEnvelhecimento(boolean emEnvelhecimento) {
        return bebidaRepository.findByEmEnvelhecimento(emEnvelhecimento);
    }

    public Boolean verificarSeBebidaEstaPronta(Integer id) {
        Bebida bebida = buscarPorID(id);
        return bebida.getEmEnvelhecimento() == false; // mais explícito
    }

    @Transactional
    public Bebida atualizarBebidaPorId(Integer id, Bebida bebida) {
        Bebida bebidaEntity = buscarPorID(id);

        Bebida bebidaAtualizada = Bebida.builder()
                //Identidade: Ao passar o .id(bebidaEntity.getId()), você diz ao banco:
                // "Não crie nada, apenas encontre o registro com este ID e troque os valores dele pelos novos que estou enviando".

                .id(bebidaEntity.getId())                                           // eh o que faz ser um update!
                .nome(bebida.getNome() != null ?                                    //O nome da bebida está vazio?
                        bebida.getNome() : bebidaEntity.getNome())                  //se sim pega adiciona o nome, se não pega o nome já salvo em entity
                .anoFabricacao(bebida.getAnoFabricacao() != 0 ?
                        bebida.getAnoFabricacao() : bebidaEntity.getAnoFabricacao())
                .emEnvelhecimento(bebida.getEmEnvelhecimento() != null ?
                        bebida.getEmEnvelhecimento() : bebidaEntity.getEmEnvelhecimento())
                .volumeMl(bebida.getVolumeMl() != 0 ?
                        bebida.getVolumeMl() : bebidaEntity.getVolumeMl())
                .build();
        return bebidaRepository.saveAndFlush(bebidaAtualizada);
    }

}
