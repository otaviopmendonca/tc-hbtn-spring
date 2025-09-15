package com.example.demo.service;

import com.example.demo.model.Produto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.stream;

@Service
public class ProdutoService {

    private List<Produto> produtos = new ArrayList<>();
    private Long contadorId = 1L;

    public ProdutoService() {
        produtos.add(new Produto("Produto 1", 10.50));
        produtos.add(new Produto("Produto 2", 100.00));
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public Produto adicionarProduto(Produto produto) {
        produto.setId(contadorId++);
        produtos.add(produto);
        return produto;
    }

    public Produto atualizarProduto(Long id, Produto produtoAtualizado) {
        Optional<Produto> produto = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if(produto.isPresent()){
            Produto p = produto.get();

            p.setNome(produtoAtualizado.getNome());
            p.setPreco(produtoAtualizado.getPreco());
            return p;
        }
        return null;
    }

    public boolean deletarProduto(Long id) {
        Optional<Produto> produto = produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();

        if(produto.isPresent()){
            Produto produtoADeletar = produto.get();
            produtos.remove(produtoADeletar);
            return true;
        }
        return false;
    }
}