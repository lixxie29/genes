package domain;

public class Gene {
    private String name;
    private String organism;
    private String function;
    private String sequence;

    public Gene(String name, String organism, String function, String sequence) {
        this.name = name;
        this.organism = organism;
        this.function = function;
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganism() {
        return organism;
    }

    public void setOrganism(String organism) {
        this.organism = organism;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return "Gene{" +
                "name='" + name + '\'' +
                ", organism='" + organism + '\'' +
                ", function='" + function + '\'' +
                ", sequence='" + sequence + '\'' +
                '}';
    }
}
