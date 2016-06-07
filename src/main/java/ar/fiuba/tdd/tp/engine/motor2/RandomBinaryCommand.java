package ar.fiuba.tdd.tp.engine.motor2;

import java.util.HashMap;

public class RandomBinaryCommand extends Command {
    private double probability;
    private Command notLuckyCommand;
    public RandomBinaryCommand(String name) {
        super(name);
        probability = 0.5;
        notLuckyCommand = new Command("default");
        notLuckyCommand.setExecutableCommand((HashMap<String, Container> components)-> {
            return "Not lucky";
        });

    }

    public void setProbability(double probability) {
        if (probability > 1){
            this.probability = 1;
        }else{
            this.probability = probability;
        }
    }

    public void setNotLuckyCommand(Command notLuckyCommand) {
        this.notLuckyCommand = notLuckyCommand;
    }

    private boolean isLucky() {
        return Math.random() <= probability;
    }

    @Override
    public String execute() {
        if(isLucky()){
            return super.execute();
        }
       else{
            return notLuckyCommand.execute();
        }
    }
}
