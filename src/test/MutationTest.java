package test;

import main.Bag;
import main.GA_bags;
import main.Individual;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;

public class MutationTest {
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
        ga.crossover();  //To check mutation function, we have to have offsprings which is intialized in crossove
        //check mutation function
        List<String> before=new ArrayList<>();
        for(Individual individual:ga.getOffsprings()){
            String str="";
            for(int x:individual.getGenes()){
                str+=x;
                System.out.print(x);
            }
            before.add(str);
            System.out.print(",");
        }
        System.out.println();
        System.out.println("After mutation");
        ga.mutatation();
        List<String> after=new ArrayList<>();
        for(Individual individual:ga.getOffsprings()){
            String str="";
            for(int x:individual.getGenes()){
                str+=x;
                System.out.print(x);
            }
            after.add(str);
            System.out.print(",");
        }
        assertFalse(before.equals(after));
    }
}
