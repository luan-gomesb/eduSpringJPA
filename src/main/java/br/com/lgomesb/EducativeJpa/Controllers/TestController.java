package br.com.lgomesb.EducativeJpa.Controllers;

import br.com.lgomesb.EducativeJpa.Entities.Player;
import br.com.lgomesb.EducativeJpa.Repositories.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    PlayerRepository playerRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/")
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
}
