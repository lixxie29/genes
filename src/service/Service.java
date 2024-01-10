package service;

import domain.Gene;
import repository.Repository;

import java.util.ArrayList;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Gene> getAll() throws Exception{
        var reports = repo.getAll();
        if (reports == null)
            throw new Exception("Could not read");
        return reports;}

    public void UpdateSchema(String newFunction,String newSequence, String name){
        repo.UpdateSchema(newFunction,newSequence,name);
    }
}
