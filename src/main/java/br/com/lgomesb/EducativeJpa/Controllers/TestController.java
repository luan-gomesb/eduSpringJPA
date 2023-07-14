package br.com.lgomesb.EducativeJpa.Controllers;

import br.com.lgomesb.EducativeJpa.Entities.Player;
import br.com.lgomesb.EducativeJpa.Repositories.PlayerRepository;
import br.com.lgomesb.EducativeJpa.Repositories.PlayerRepositoryJPA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class TestController {
    @Autowired
    PlayerRepository playerRepo;

    @Autowired
    PlayerRepositoryJPA repoJPA;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/test")
    public List<Player> test(){
        Player p1= new Player("Luan","Brazil", Date.valueOf("1994-03-26"), 10);
        Player p2  = new Player("Raul","Brazil", Date.valueOf("1994-01-06"), 10);
        Player p3  = new Player("Rodolfo","Brazil", Date.valueOf("1994-07-12"), 10);
        Player p4  = new Player("Aline","Brazil", Date.valueOf("1994-07-08"), 10);
        Player p5  = new Player("Melyssa","Brazil", Date.valueOf("1994-08-07"), 10);

        logger.info("inserted: {}",playerRepo.insertPlayer(p1));
        logger.info("inserted: {}",playerRepo.insertPlayer(p2));
        logger.info("inserted: {}",playerRepo.insertPlayer(p3));
        logger.info("inserted: {}",playerRepo.insertPlayer(p4));
        logger.info("inserted: {}",playerRepo.insertPlayer(p5));

        Player searchResponse = playerRepo.getPlayerById(1);
        searchResponse.setName("Luan Gomes Berto");
        searchResponse.setTitles(15);
        logger.info("search player response: {}",searchResponse);

        Player updatedPlayer = playerRepo.updatePlayer(searchResponse);
        logger.info("updated player: {}",updatedPlayer);

        playerRepo.deleteById(3);
        logger.info("player deleted");

        return   playerRepo.getAll();
    }

    @GetMapping("/test2")
    public List<Player> test2(){

        Player p1= new Player("Fenometo","Brazil", Date.valueOf("1994-03-26"), 22);
        Player p2  = new Player("Bruxo","Brazil", Date.valueOf("1994-01-06"), 30);
        Player p3  = new Player("Didico","Brazil", Date.valueOf("1994-07-12"), 19);
        Player p4  = new Player("Kaka","Brazil", Date.valueOf("1994-07-08"), 10);
        Player p5  = new Player("Ney Ney","Brazil", Date.valueOf("1994-08-07"), 20);
        List<Player> playersList = Arrays.asList(p1, p2, p3, p4, p5);
        List<Player> newPlayers = repoJPA.saveAll(playersList);
        logger.info("search player response: {}",newPlayers);
        return  newPlayers;
    }
    @GetMapping("/players")
    public List<Player> list(){
        return repoJPA.findAll();
    }
    @GetMapping("/players/{id}")
    public Player find(@PathVariable Integer id) {
        Optional<Player> player = repoJPA.findById(id);
        return player.orElseGet(Player::new);
    }

    @DeleteMapping("/players/{id}")
    public String delete(@PathVariable Integer id) {
        try {
            Optional<Player> player = repoJPA.findById(id);
            if (player.isPresent()) {
                repoJPA.deleteById(id);
                return "Player removed";
            }
            return "Players Not found.";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
