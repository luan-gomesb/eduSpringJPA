package br.com.lgomesb.EducativeJpa.services;

import br.com.lgomesb.EducativeJpa.Entities.Player;
import br.com.lgomesb.EducativeJpa.Repositories.PlayerRepositoryJPA;
import br.com.lgomesb.EducativeJpa.errors.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PlayerServices {

    @Autowired
    PlayerRepositoryJPA repository;

    public List<Player> getAllPlayers(){
        return repository.findAll();
    }

    public void deletePlayer(Integer id){
            Optional<Player> player = repository.findById(id);
            if (player.isPresent()) {
                 repository.deleteById(id);
            }else{
                throw new PlayerNotFoundException(id);
            }
    }

    public Player getPlayer(Integer id) throws RuntimeException{
        Optional<Player> player = repository.findById(id);
        if(player.isEmpty()){
            throw new PlayerNotFoundException(id);
        }
        return player.get();
    }

    public Player createPlayer(Player player){
        return repository.save(player);
    }

    public Player updatePlayer(Integer id, Player player){
        Player p = getPlayer(id);
        p.setName(player.getName());
        p.setNationality(player.getNationality());
        p.setBirthDate(player.getBirthDate());
        p.setTitles(player.getTitles());
        return repository.save(p);
    }
    public Player partialUpdate(Integer id, Map<String, Object> playerData){
        Player p = getPlayer(id);

        playerData.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Player.class, key);
            ReflectionUtils.makeAccessible(field);
            ReflectionUtils.setField(field,p,value);
        });
        return repository.save(p);
    }
}
