package test;
import main.Bag;
import main.GA_bags;
import main.Individual;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SelectionTest {
    @Test
    public void Test1(){
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
        //check selection function
        for(Individual individual:ga.getPopulation().getIndividuals()) System.out.print(individual.calculteFitness()+",");
        ga.selection();
        System.out.println();
        System.out.println("After selection:");
        for(Individual individual:ga.getPopulation().getIndividuals()) System.out.print(individual.calculteFitness()+",");
        System.out.println();
        assertEquals(ga.getPopulation().getIndividuals().get(ga.getPopulation().getIndividuals().size()-1).calculteFitness(), 0);
    }
}
