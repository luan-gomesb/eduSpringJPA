package br.com.lgomesb.EducativeJpa.errors;

public class PlayerNotFoundException  extends RuntimeException {


    public PlayerNotFoundException(){}

    public PlayerNotFoundException(Integer id){
        super("Player with id {"+ id.toString() +"} not found");
    }
}
