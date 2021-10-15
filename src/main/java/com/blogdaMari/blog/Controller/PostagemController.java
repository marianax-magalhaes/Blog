package com.blogdaMari.blog.Controller;

import com.blogdaMari.blog.Model.Postagem;
import com.blogdaMari.blog.Repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*") //assim aceita qualuqer origem
public class PostagemController {

    @Autowired
    private PostagemRepository repository;

    @GetMapping //sempre que vier algo externo atraves da url será tratado aqui
    public ResponseEntity<List<Postagem>> GetAll(){
        return ResponseEntity.ok(repository.findAll());

    }

    @GetMapping("/{id}")//monta uma url onde indicamos o id do item desejado
    //agora nao traz mais uma lista de postagem, mas uma postagem unica escolhida /id
    //precisamos usar dentro da passagem do parâmetro a annotation PathVariable pra dizer ao Spring que cuide da parte de trazer da url a informação e use com parametro.
    //o @path... vai dizer para o getId que aquele Long id é o mesmo que esta na url /id
    public ResponseEntity<Postagem> GetById(@PathVariable Long id ){
        //vamos precisar usar um optional(tipo um if) e expressão lambda
        return repository.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
        //se der tudo certo o objeto encontrado vem no responseEntity.om
        //pode não existir então o orElse informar o not found
    }

    @GetMapping("/titulo/{titulo}")
    //acima criamos uma "subrota", pois se o titulo contiver numero e pesquisrmos por ele, por exemplo, vai identificar como id
    //uma lista pois podemos ter mais de um post com mesmo titulo
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
    }

    //visualizacao dos posts adicionados pelo mysqlbench
    @GetMapping("/texto/{texto}")
    public ResponseEntity<List<Postagem>> getByTexto(@PathVariable String texto){
        //nao esquecer que esse findAll.... tem que estar criado no repository
        return ResponseEntity.ok(repository.findAllByTextoContainingIgnoreCase(texto));
    }

    //inclusao de novos posts atraves da API. POST=>PostMapping
    @PostMapping
    public ResponseEntity<Postagem> post (@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
    }

    //atualizacao de dados, alteracao
    @PutMapping
    public ResponseEntity<Postagem> put(@RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
    }

    //deletando um post por completo
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }
}









