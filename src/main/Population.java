package main;

import java.util.ArrayList;
import java.util.List;

//a set of individuals(solutions)
public class Population {
    private List<Individual> individuals;

    public Population(int n,int sumBags,int maxWeight,Bag[] bags){
        this.individuals=new ArrayList<>();
        for(int i=0;i<n;i++){
            Individual individual=new Individual(sumBags,maxWeight,bags);      //intialization
            individuals.add(individual);
        }
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    //calculate the fittest individual, and return its index
    public int calculateFittest(){
        int max=0;
        int index=0;
        for(int i=0;i<individuals.size();i++){
            if(individuals.get(i).calculteFitness()>max){
                max=individuals.get(i).calculteFitness();
                index=i;
            }
        }
        return index;
    }

    //calculate the average fitness of all individuals among the whole population
    public double calculateAverage(){
        double sum=0;
        for(int i=0;i<individuals.size();i++){
            sum+=individuals.get(i).calculteFitness();
        }
        return sum/individuals.size();
    }
}
