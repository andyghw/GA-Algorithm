package main;
import java.util.Random;

public class Individual {
    //each individual is a solution of KnapsackProblem
    private int[] genes;      //"1" stands for the bag is chosen, while "0" stands for the bag is not
    private Bag[] bags;       //each digit of gene is responsible for a specific Bag object
    private int fitness;     //the fitness of an individual
    private int curWeight;   //the weight of this individual
    private int maxWeight;   //once the total weight of bags of this individual over maxWeight, then fitness is 0

    public Individual(int sumBags,int maxWeight,Bag[] bags){
         this.genes=new int[sumBags];
         this.maxWeight=maxWeight;
         this.bags=bags;
         Random random=new Random();
         for(int i=0;i<sumBags;i++){
             genes[i]=random.nextInt(2);       //random generate genes,"0" or "1"
         }
     }

    public Bag[] getBags() {
        return bags;
    }

    public int[] getGenes() {
        return genes;
    }

    //fitness function
    public int calculteFitness(){
        int sumWeight=0;
        int value=0;
        for(int i=0;i<bags.length;i++){
            if(genes[i]==1) {
                sumWeight+=bags[i].getWeight();
                value+=bags[i].getValue();
            }
         }
         curWeight=sumWeight;
         if(sumWeight>maxWeight){
            fitness=0;         //if the weight of this individual is over maxWeight, fitness is 0
         }
        else fitness=value;   //total value is the fitness
         return fitness;
     }

     public int calculateWeight(){
         int sumWeight=0;
         for(int i=0;i<bags.length;i++){
             if(genes[i]==1) {
                 sumWeight+=bags[i].getWeight();
             }
         }
         curWeight=sumWeight;
         return curWeight;
     }
}
