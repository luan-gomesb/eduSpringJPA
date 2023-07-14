package br.com.lgomesb.EducativeJpa.Repositories;

import br.com.lgomesb.EducativeJpa.Entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepositoryJPA  extends JpaRepository<Player,Integer> {
}
