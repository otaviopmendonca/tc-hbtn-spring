package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarUsuarioQuandoIdExistir() {
        Usuario usuario = new Usuario(1L, "João", "joao@email.com");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarUsuarioPorId(1L);

        assertNotNull(resultado);
        assertEquals("João", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(1L));
        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuario = new Usuario(null, "Maria", "maria@email.com");
        Usuario usuarioSalvo = new Usuario(2L, "Maria", "maria@email.com");

        when(usuarioRepository.save(usuario)).thenReturn(usuarioSalvo);

        Usuario resultado = usuarioService.salvarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals(2L, resultado.getId());
        assertEquals("Maria", resultado.getNome());
    }
}