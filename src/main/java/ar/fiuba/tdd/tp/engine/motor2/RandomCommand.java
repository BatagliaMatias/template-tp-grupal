package ar.fiuba.tdd.tp.engine.motor2;

public class RandomCommand extends Command {
    private double probability;
    private String notLuckyMsg;
    public RandomCommand(String name) {
        super(name);
        probability = 0.1;
        notLuckyMsg = "Not lucky";
    }

    public void setProbability(double probability) {
        if (probability > 1){
            this.probability = 1;
        }else{
            this.probability = probability;
        }
    }

    public void setNotLuckyMsg(String notLuckyMsg) {
        this.notLuckyMsg = notLuckyMsg;
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
            return notLuckyMsg;
        }
    }
}
