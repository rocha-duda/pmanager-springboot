package com.rochaduda.pmanager;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // Dizendo que trata-se de uma classe cujo objeto vai responder requisições do cliente

public class ExampleController {

    @GetMapping("/ok") //Estou alertando quando esse método vai ser ativado (nesse caso quando for um get na url com /ok)
    public ResponseEntity<String> sayOk(){
        return ResponseEntity.ok("Everything is ok");
    }

    @PostMapping("/echo")
    public ResponseEntity<String> echo(@RequestBody String value){

        StringBuilder sb = new StringBuilder(value);
         
        
        return ResponseEntity.ok(sb.reverse().toString()); //monto uma resposta de sucesso para ele receber
    }
    
    //O @RequestBody ta falando: extraia do corpo do protocolo essa informação e armazene nessa variavel que está aqui, no caso, o String value

}
