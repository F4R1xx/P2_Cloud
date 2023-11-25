package br.edu.ibmec.album.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ibmec.album.exception.CompositorException;
import br.edu.ibmec.album.model.Compositor;
import br.edu.ibmec.album.repository.CompositorRepository;

@Service
public class CompositorService {
    @Autowired
    private CompositorRepository CompositorRepository;

    public Compositor create(Compositor compositor){
        return this.CompositorRepository.save(compositor);
    }

    public Optional<Compositor> getById(long id){
        return this.CompositorRepository.findById(id);
    }

    public List<Compositor> getAll() {
        return this.CompositorRepository.findAll();
    }

    public void saveOrUpdate(Compositor item){
        this.CompositorRepository.save(item);
    }

    public Compositor update(long id, Compositor newData) throws CompositorException{
        Optional<Compositor> oldCompositor = this.CompositorRepository.findById(id);
        if(oldCompositor.isPresent() == false){
            throw new CompositorException("Não encontrei o compositor a ser atualizado");
        }

        Compositor compositor = oldCompositor.get();
        compositor.setNome(newData.getNome());

        this.CompositorRepository.save(compositor);
        return compositor;
    }

    public void delete(long id) throws CompositorException {
        Optional<Compositor> oldCompositor = this.CompositorRepository.findById(id);

        if(oldCompositor.isPresent() == false){
            throw new CompositorException("Não encontrei o compositor a ser deletado");
        }

        this.CompositorRepository.delete(oldCompositor.get());

        }
}
