package main;
import java.util.*;

public class GA_bags {
    private Population population;
    private int fittest;
    private List<Individual> offsprings;
    private int sumBags;
    private int maxWeight;
    private Bag[] bags;

    public GA_bags(int n,int sumBags,int maxWeight,Bag[] bags){
        this.population=new Population(n,sumBags,maxWeight,bags);
        this.sumBags=sumBags;
        this.maxWeight=maxWeight;
        this.bags=bags;
    }

    public Population getPopulation() {
        return population;
    }

    public List<Individual> getOffsprings() {
        return offsprings;
    }

    //Selection function
    public void selection(){
        //Priority Queue to sort the individuals by their fitness
        Comparator<Individual> order = (Individual i1, Individual i2) -> {            //redefine the comparator of PQ
                double numbera = i1.calculteFitness();
                double numberb = i2.calculteFitness();
                if (numberb > numbera) return 1;
                else if (numberb < numbera) return -1;
                else return 0;
        };
        Queue<Individual> pq=new PriorityQueue<>(order);
        List<Individual> individuals=population.getIndividuals();
        for(int i=0;i<individuals.size();i++) pq.add(individuals.get(i));
        int remCount=individuals.size()/2;
        individuals.clear();
        for(int i=0;i<remCount;i++){       //keep the top 50% fittest individuals to the next generation
            individuals.add(pq.poll());
        }
    }

    //Crossover Function
    public void crossover(){
        Random random=new Random();
        List<Individual> individuals=population.getIndividuals();
        //copy the genes of individuals to the offsprings
        offsprings=new ArrayList<>();
        for(int i=0;i<individuals.size();i++){
            Individual individual=new Individual(sumBags,maxWeight,bags);
            for(int j=0;j<individual.getGenes().length;j++) individual.getGenes()[j]=individuals.get(i).getGenes()[j];
            offsprings.add(individual);
        }
        int pos=random.nextInt(offsprings.get(0).getGenes().length);    //decide the position of genes to crossover
        for(int i=0;i<offsprings.size()-1;i+=2){         //1st fittest pair with 2nd fittest,3nd with the 4th...
            for(int j=0;j<=pos;j++){
                int tem=offsprings.get(i).getGenes()[j];
                offsprings.get(i).getGenes()[j]=offsprings.get(i+1).getGenes()[j];
                offsprings.get(i+1).getGenes()[j]=tem;
            }
        }
    }

    //Mutation function
    public void mutatation(){
        Random random=new Random();
        for(int i=0;i<offsprings.size();i++) {
            if(Math.random()>0.001) continue;                                  //the rate to happen mutation
            int mutPos = random.nextInt(offsprings.get(i).getGenes().length);    //the position of genes to happen matation
            int[] genes = offsprings.get(i).getGenes();
            if (genes[mutPos] == 1) genes[mutPos] = 0;
            else genes[mutPos] = 1;
        }
    }

    //Combine the offsprings with the remained individuals to compose the next generation
    public void addChildern(){
        List<Individual> individuals=population.getIndividuals();
        for(int i=0;i<offsprings.size();i++){
            individuals.add(offsprings.get(i));
        }
    }

    //Calculate fitness and report
    public void logging(int generationCount){
        int index=population.calculateFittest();   //the index of the fittest individual
        List<Individual> individuals=population.getIndividuals();
        this.fittest=individuals.get(index).calculteFitness();  //the fitness of the fittest individual
        System.out.print("  Generation:"+generationCount+" ,The gene of fittest individual:");
        for(int x:individuals.get(index).getGenes()) System.out.print(x);   //The genes of the fittest individual
        System.out.print("  ,The biggest value:"+fittest);
        System.out.println( "  ,Average value:"+population.calculateAverage()+"  ,Fittest weight:"+population.getIndividuals().get(index).calculateWeight());
    }


    public static void main(String[] args){
        int sumBags=20;
        int maxWeight=40;
        int[] bagValue= {1,2,3,4,5,6,7,8,9,10,5,4,6,3,8,4,4,15,10,7};
        int[] bagWeight={3,4,6,7,6,10,4,8,5,4,1,5,8,16,2,3,5,6,3,1};
        Bag[] bags=new Bag[sumBags];
        for(int i=0;i<bags.length;i++) {
            bags[i]=new Bag();
            bags[i].setValue(bagValue[i]);
            bags[i].setWeight(bagWeight[i]);
        }
        GA_bags ga=new GA_bags(10000,sumBags,maxWeight,bags);
        int generationCount=0;
        for(int x=0;x<100;x++){
            ga.logging(generationCount);
            ga.selection();
            ga.crossover();
            ga.mutatation();
            ga.addChildern();
            generationCount++;
        }
    }
}
