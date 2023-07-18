package br.com.lgomesb.EducativeJpa.Controllers;

import br.com.lgomesb.EducativeJpa.Entities.Player;
import br.com.lgomesb.EducativeJpa.services.PlayerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PlayerController {
    @Autowired
    PlayerServices service;

    @GetMapping("/hello")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("Hello players", HttpStatus.OK);
    }

    //@GetMapping = @RequestMapping(method = RequestMethod.GET)
    @GetMapping("/players")
    public ResponseEntity<List<Player>> list(){
        return new ResponseEntity<>(service.getAllPlayers(), HttpStatus.OK);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> save(@RequestBody Player p){
        //make sure ia a new players
        p.setId(0);
        return new ResponseEntity<>(service.createPlayer(p),HttpStatus.CREATED);
    }

    @GetMapping("/players/{id}")
    public ResponseEntity<Player> find(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getPlayer(id),HttpStatus.OK);
    }

    @PutMapping("/players/{id}")
    public ResponseEntity<Player> update(@RequestBody Player p, @PathVariable Integer id){
        //make sure ia a new players
        return new ResponseEntity<>(service.updatePlayer(id,p), HttpStatus.OK);
    }

    @PatchMapping("/players/{id}")
    public ResponseEntity<Player> partialUpdate(@PathVariable Integer id, @RequestBody Map<String,Object> data){
        return new ResponseEntity<>(service.partialUpdate(id,data),HttpStatus.OK);
    }

    @DeleteMapping("/players/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {
        service.deletePlayer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
