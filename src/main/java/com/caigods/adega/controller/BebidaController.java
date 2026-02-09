package com.caigods.adega.controller;

import com.caigods.adega.business.BebidaService;
import com.caigods.adega.infrastructure.entitys.Bebida;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Define que eh o controller padrao REST
@RequestMapping("/bebidas")  //Define que todas as URLs comecem com /bebidas
@RequiredArgsConstructor //Injeta o Service pelo Lombok
public class BebidaController {
    private final BebidaService bebidaService;  //Service injetado


    @PostMapping //Criar ou salvar dados, o banco ganha um registro que não existia antes
    public ResponseEntity<Bebida> salvarBebida(@RequestBody Bebida bebida) {  //
        // 1. Capturamos a bebida que o Service salvou (agora ela tem ID e passou nas validações)
        Bebida bebidaSalva = bebidaService.salvarBebida(bebida);
        // 2. Retorna o status 201 (Created) e enviamos a bebida no corpo (body)
        return ResponseEntity.status(HttpStatus.CREATED).body(bebidaSalva);

        //bebidaService.salvarBebida(bebida)                    //jeito simples, funciona mas nao segue as melhores praticas
        //return ResponseEntity.ok().build();        //
    }


    /* Endpoint para listar todas as bebidas do banco.
        Retorna 200 ok e o corpo (body) com a lista JSON
     */
    @GetMapping  //Atende requisicoes do tipo GET(Leitura) no endereco /bebidas . Buscar ou ler dados
    public ResponseEntity<List<Bebida>> listarBebidas() {  //Como eh apenas listar sem nenhum parametro, nao precisa de nenhum Request
        List<Bebida> lista = bebidaService.listarTudo();
        return ResponseEntity.ok(lista);
    }

    // buscar bebidas pelo ano
    @GetMapping("/ano/{ano}")  //Atende requisicoes do tipo GET(Leitura) no endereco /bebidas . Buscar ou ler dados
    public ResponseEntity<List<Bebida>> buscarPorAno(@PathVariable Integer ano) { // Usar @PathVariable para IDs ou recursos específicos.
        List<Bebida> resultado = bebidaService.BuscarPorAno(ano);
        return ResponseEntity.ok(resultado);
    }

    // Buscar bebidas pelo ID
    @GetMapping("/{id}")  //Atende requisicoes do tipo GET(Leitura) no endereco /bebidas . Buscar ou ler dados
    public ResponseEntity<Bebida> buscarPorId(@PathVariable Integer id) { // Usar @PathVariable para IDs ou recursos específicos.
        Bebida bebida = bebidaService.buscarPorID(id);
        return ResponseEntity.ok(bebida);
    }

    // Buscar bebidas por nome
    @GetMapping("/nome") //Atende requisicoes do tipo GET(Leitura) no endereco /bebidas . Buscar ou ler dados
    public ResponseEntity<List<Bebida>> buscarPorNome(@RequestParam String nome) {
        List<Bebida> nomeResultado = bebidaService.buscarPorNome(nome);
        return ResponseEntity.ok(nomeResultado);
    }

    // Buscar bebidas por status de envelhecimento
    @GetMapping("/status/{emEnvelhecimento}") //Atende requisicoes do tipo GET(Leitura) no endereco /bebidas . Buscar ou ler dados
    public ResponseEntity<List<Bebida>> buscarPorEnvelhecimento(@PathVariable boolean emEnvelhecimento) {
        List<Bebida> statusBebidas = bebidaService.buscarPorStatusEnvelhecimento(emEnvelhecimento);
        return ResponseEntity.ok(statusBebidas);
    }

    // Verificar se uma bebida específica está pronta
    @GetMapping("/{id}/pronta")
    public ResponseEntity<Boolean> verificarSePronta(@PathVariable Integer id) {
        boolean estaPronta = bebidaService.verificarSeBebidaEstaPronta(id);
        return ResponseEntity.ok(estaPronta);
    }

    @PutMapping("/{id}") //Put eh usado para atualizar
    public ResponseEntity<Bebida> atualizarBebidaPorId(@PathVariable Integer id ,
                                                       @RequestBody Bebida bebida){
        Bebida bebidaSalva = bebidaService.atualizarBebidaPorId(id, bebida);
        return ResponseEntity.ok(bebidaSalva);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Integer id){
        bebidaService.deletarBebidaPorId(id);
        return ResponseEntity.ok().build();
    }
}
