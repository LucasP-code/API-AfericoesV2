package application.afericao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/afericoes")
@Tag(name = "Afericoes", description = "Gerencia os registros de aferições de sensores")
public class AfericaoController {
    @Autowired
    private AfericaoService afericaoService;

    @PostMapping
    @Operation(summary = "Insere uma nova aferição", description = "Cria um novo registro na base de afericoes com os dados enviados")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public AfericaoDTO insert(@RequestBody AfericaoInsertDTO novaAfericao) {
        return afericaoService.insert(novaAfericao);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma aferição pelo ID", description = "Retorna os dados de uma aferição específica com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public AfericaoDTO getOne(
        @Parameter(description = "ID da aferição a ser obtida")
        @PathVariable long id) {
        return afericaoService.getOne(id);
    }

    @GetMapping
    @Operation(summary = "Obtém todas as aferições", description = "Retorna uma lista com todas as aferições registradas na base de dados")   
    @ApiResponse(responseCode = "200", description = "Lista de aferições encontrada com sucesso")
    public Iterable<AfericaoDTO> getAll() {
        return afericaoService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma aferição existente", description = "Atualiza os dados de uma aferição específica com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public AfericaoDTO update(
        @Parameter(description = "ID da aferição a ser atualizada")
        @PathVariable long id, @RequestBody AfericaoInsertDTO novosDados) {
        return afericaoService.update(id, novosDados);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remove uma aferição", description = "Exclui uma aferição específica com base no ID fornecido")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Aferição removida com sucesso"),
        @ApiResponse(responseCode = "404", description = "Aferição não encontrada")
    })
    public void remove(@PathVariable long id) {
        afericaoService.delete(id);
    }
}