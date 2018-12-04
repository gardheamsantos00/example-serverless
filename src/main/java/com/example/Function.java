
package com.example;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

class Cidade {
    private Long id;
    private String nome;
    private Estado estado;

    public Cidade(){

    }
    public Cidade(Long id, String nome, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.estado = estado;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the estado
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}

class Estado {
    private Long id;
    private String nome;

    public Estado(){

    }

    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }
    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }
    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}

class DAO {

    public List<Cidade> listCidade = new ArrayList<>();

    public List<Cidade> fakeList(){

        listCidade.add(new Cidade(1L, "cornelio", new Estado(1L, "PR")));
        listCidade.add(new Cidade(2L, "londrina", new Estado(2L, "PR")));
        listCidade.add(new Cidade(3L, "sao paulo", new Estado(3L, "SP")));
        listCidade.add(new Cidade(4L, "fortaleza", new Estado(4L, "CE")));

        return listCidade;
    }

    public Cidade create(Cidade cid) {

        Cidade cidade = new Cidade();
        cidade.setNome(cid.getNome());

        return cid;
    }

    public List<Cidade> read() {
        
        return fakeList();
    }

    public Cidade update(Cidade cid) {
        cid.setId(cid.getId() + 1L);
        cid.setNome(cid.getNome() + "mudou");
        return cid;
    }

    public int delete(Long id) {
        return 200;
    }

}



public class Function {
    

    DAO dao = new DAO();

    @FunctionName("criar-cidade")
    public Cidade create(
            @HttpTrigger(name = "criarCidade", 
            methods = { HttpMethod.POST }, route = "city") Cidade cidade) {
        return dao.create(cidade);
    }

    @FunctionName("ler-Cidade")
    public List<Cidade> read(
            @HttpTrigger(name = "lerCidade",
             methods = { HttpMethod.GET }, route = "city") String cidade) {
        return dao.read();
    }

    @FunctionName("alterar-Cidade")
    public Cidade update(
            @HttpTrigger(name = "alterarCidade", 
            methods = { HttpMethod.PUT }, route = "city") Cidade cidade) {
        return dao.update(cidade);
    }

    @FunctionName("deletar-Cidade")
    public int delete(
        @HttpTrigger(name = "deletarCidade", 
        methods = {HttpMethod.DELETE }, route = "city/{id}")
         @BindingName("id") Long id) {
        return dao.delete(id);
    }

}