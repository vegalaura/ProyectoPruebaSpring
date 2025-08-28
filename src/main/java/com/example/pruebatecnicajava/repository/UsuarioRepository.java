package com.example.pruebatecnicajava.repository;
import com.example.pruebatecnicajava.entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

public interface UsuarioRepository extends JpaRepository<Usuario, String>, JpaSpecificationExecutor<Usuario> {
    Optional<Usuario> findByLogin(String login);
    @Transactional
    int deleteByLogin(String login);
    List<Usuario> findAll();
}
