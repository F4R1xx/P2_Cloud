package br.edu.ibmec.album.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ibmec.album.model.Musica;

@Repository
public interface MusicaRepository extends JpaRepository<Musica, Long> {
}
