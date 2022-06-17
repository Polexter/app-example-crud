package com.app.example.crud.repository;

import com.app.example.crud.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    List<ClienteEntity> findByEliminado(Integer eliminado);

    Optional<ClienteEntity> findByIdAndEliminado(String id, Integer eliminado);

}
