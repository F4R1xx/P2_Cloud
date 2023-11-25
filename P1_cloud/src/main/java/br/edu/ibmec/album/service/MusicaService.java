package br.edu.ibmec.album.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.album.exception.MusicaException;
import br.edu.ibmec.album.model.Compositor;
import br.edu.ibmec.album.model.Musica;
import br.edu.ibmec.album.repository.MusicaRepository;


@Service
public class MusicaService {

    @Autowired
    MusicaRepository musicaRepository;

    @Autowired CompositorService compositorService;

    public List<Musica> findAll() {
        return this.musicaRepository.findAll();
    }

    public Optional<Musica> findById(long id) {
        return this.musicaRepository.findById(id);
    }

    public Musica update(long id, Musica newData) throws MusicaException{
        Optional<Musica> opMusica = this.musicaRepository.findById(id);

        if(opMusica.isPresent() == false)
            throw new MusicaException("música não encontrada");

        Musica musica = opMusica.get();
        musica.setNome(newData.getNome());

        this.musicaRepository.save(musica);
        return musica;

    }

    public Musica save(long idCompositor, Musica item) throws MusicaException {
        Optional<Compositor> opCompositor = this.compositorService.getById(idCompositor);

        if(opCompositor.isPresent() == false)
            throw new MusicaException("Compositor não encontrado");

        Compositor compositor = opCompositor.get();
        item.setCompositor(compositor);

        this.musicaRepository.save(item);

        return item;
    }

    public void delete(long id) throws MusicaException {
        Optional<Musica> Musica = this.musicaRepository.findById(id);
        if(Musica.isPresent() == false){
            throw new MusicaException("Não encontrei a musica a ser deletada");
        }

        this.musicaRepository.delete(Musica.get());
        }

}
