package br.com.lgomesb.EducativeJpa.Repositories;

import br.com.lgomesb.EducativeJpa.Entities.Player;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class PlayerRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Player insertPlayer(Player player){
       return  entityManager.merge(player);
    }


    public Player updatePlayer(Player player){
        return  entityManager.merge(player);
    }

    public  Player getPlayerById(int id){
        return entityManager.find(Player.class,id);
    }

    public void deleteById(int id){
        Player player = entityManager.find(Player.class, id);
        entityManager.remove(player);
    }
    public List<Player> getAll(){
        Query getAllPlayers = entityManager.createNamedQuery("get_all_players");
        List<Player> resultList = getAllPlayers.getResultList();
        return resultList;
    }
}
