import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class NumberPlayList {
    public static void main(String[] args) {
        List<Integer> myNumberList = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) myNumberList.add(i);
        //Travsersing using iterator
        Iterator<Integer> it = myNumberList.iterator();
        while (it.hasNext()) {
            Integer i = it.next();
            System.out.println("Mth1: iterator value is ::" + i);
        }
        //mth 2 Travesersing with Explicit consumer interface implementation
        class MyConsumer implements Consumer<Integer> {
            @Override
            public void accept(Integer t) {
                System.out.println("Mth2:consumer impl value::" + t);
            }
        }
            MyConsumer action = new MyConsumer();
            myNumberList.forEach(action);
        // method 3:traversing with annoymous consumer interface implementation
        myNumberList.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer i) {
                System.out.println("mth3: for each annonymous class value"+i);
            }
        });
        //method 4: explicit lamda function
        Consumer<Integer> myListAction =n ->{
            System.out.println("mth4:for eah lamda impl value"+n);
        };
        // method 5: implicit lamda function
       // myListAction.forEach(n->{
              //      System.out.println("mth5: for each lamda impl value"+n);
            //    });
        // method 6: implicit lamda fiunction to print double value
        Function<Integer,Double>  toDoubleFunction =Integer::doubleValue;
        myNumberList.forEach(n->{
            System.out.println("mth6: for each lamda double Value"+toDoubleFunction.apply(n));
        });
        //method 7: Implicit lamda function to check even no
        Predicate<Integer> isEvenFunction =n-> n >0 && n%2==0;
        myNumberList.forEach(n->{
            System.out.println("mth7 :foreach value of :"+n+"check for even"+isEvenFunction.test(n));
        });
        // method 8: processing the stream
        myNumberList.stream().forEach(n1->{
            System.out.println("mth 8: stream foreach value"+ n1);
        });
      //method 9: process the stream,Apply the operation on the stream and store the result
        List<Double> streamList=myNumberList.stream()
                                     .filter(isEvenFunction)
                                     .map(toDoubleFunction)
                                     .collect(Collectors.toList());
        System.out.println("Printing Doublelist: "+ streamList);
        //method 10: Listing the first even
        Integer first= myNumberList.stream()
                      .filter(isEvenFunction)
                      .peek(n-> System.out.println("Peek even no is: "+n))
                      .findFirst()
                      .orElse(null);
        System.out.println("mth10: first even is: "+first);
        //method 11: Minimium even number
        Integer min = myNumberList.stream()
                    .filter(isEvenFunction)
                    .min((n1,n2)->n1-n2)
                    .orElse(null);
        System.out.println("mth11: Min even: "+min);
        //method 12 : maximum even number
        Integer max=myNumberList.stream()
                       .filter(isEvenFunction)
                        .max(Comparator.comparing(Integer::intValue))
                       .orElse(null);
        System.out.println("mth12 Max even no: "+max);
        //Method 13: Sum count and avarage the the number
        Integer sum =myNumberList.stream()
                    .reduce(0,Integer::sum);
        long count =myNumberList.stream().count();
        System.out.println("Mth13 avg of "+sum+"/"+count+"="+sum/count);
     // method 14: checking all even,single even or none are divisible by 6
      boolean allEven =myNumberList.stream().allMatch(isEvenFunction);
      boolean oneEven =myNumberList.stream().anyMatch(isEvenFunction);
      boolean noneMultOfSix =myNumberList.stream().noneMatch(i-> i>0 && i%6==0);
        System.out.println("All Even: "+ allEven+" "+"One Even: "+oneEven+" "+"noneMultOfSix: "+noneMultOfSix);



    }
}