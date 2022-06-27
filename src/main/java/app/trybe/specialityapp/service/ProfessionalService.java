package app.trybe.specialityapp.service;

import app.trybe.specialityapp.model.Professional;
import app.trybe.specialityapp.repository.ProfessionalRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessionalService {

  @Autowired
  private ProfessionalRepository repository;

  /**
   * Find all.
   *
   * @return the list
   * @throws Exception the exception
   */
  public List<Professional> findAll() throws Exception {
    List<Professional> response = repository.findAll();
    if (response.isEmpty()) {
      throw new Exception("404†Nenhum registro foi encontrado!");
    }
    return response;
  }

  public Professional findById(int id) throws Exception {
    return repository.findById(id).map(e -> e).orElseThrow(
        () -> new Exception("404†Nenhum registro foi encontrado!"));
  }

  /**
   * Insert.
   *
   * @param professional the professional
   * @return the professional
   * @throws Exception the exception
   */
  public Professional insert(Professional professional) throws Exception {
    if (professional.getId() != null) {
      throw new Exception(
          "400†Não é permitido inserir novos registros com ID explícito");
    }
    return repository.save(professional);
  }

  /**
   * Edits the.
   *
   * @param id           the id
   * @param professional the professional
   * @return the professional
   * @throws Exception the exception
   */
  public Professional edit(Integer id, Professional professional)
      throws Exception {
    return repository.findById(id).map(e -> {
      e.setName(professional.getName());
      e.setSpeciality(professional.getSpeciality());
      repository.save(e);
      return e;
    }).orElseThrow(() -> new Exception(
        "404†Não é possível editar, o ID informado não existe"));
  }

  /**
   * Delete.
   *
   * @param id the id
   * @return the professional
   * @throws Exception the exception
   */
  public Professional delete(Integer id) throws Exception {
    return repository.findById(id).map(p -> {
      repository.deleteById(id);
      return p;
    }).orElseThrow(() -> new Exception(
        "404†Não é possível editar, o ID informado não existe"));
  }
}
