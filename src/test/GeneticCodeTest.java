package test;

import main.Bag;
import main.Individual;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertFalse;


public class GeneticCodeTest {
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
        Individual individual1=new Individual(sumBags,maxWeight,bags);
        Individual individual2=new Individual(sumBags,maxWeight,bags);
        assertFalse(Arrays.equals(individual1.getGenes(),individual2.getGenes()));
    }
}
