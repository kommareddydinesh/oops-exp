<<< ex8.1 >>>
FruitMarket

package ex8;

import java.util.*;
import java.util.concurrent.*;
class FruitMarket {
    private final int capacity;
    private final Map<String, BlockingQueue<Integer>> fruitBuffers;
    public FruitMarket(int capacity) {
        this.capacity = capacity;
        fruitBuffers = new HashMap<>();
        fruitBuffers.put("apple", new LinkedBlockingQueue<>(capacity));
        fruitBuffers.put("orange", new LinkedBlockingQueue<>(capacity));
        fruitBuffers.put("grape", new LinkedBlockingQueue<>(capacity));
        fruitBuffers.put("watermelon", new LinkedBlockingQueue<>(capacity));
    }
    public void produce(String fruit, int quantity) throws InterruptedException {
        BlockingQueue<Integer> buffer = fruitBuffers.get(fruit);
        for (int i = 0; i < quantity; i++) {
            buffer.put(1); // Produce one unit of the fruit
            System.out.println("Produced 1 " + fruit + ". Total in market: " + buffer.size());
        }
    }
    public void consume(String fruit, int quantity) throws InterruptedException {
        BlockingQueue<Integer> buffer = fruitBuffers.get(fruit);
        for (int i = 0; i < quantity; i++) {
            buffer.take(); // Consume one unit of the fruit
            System.out.println("Consumed 1 " + fruit + ". Remaining in market: " + buffer.size());
        }
    }
}
class Farmer implements Runnable {
    private final FruitMarket market;
    private final String fruit;
    private final int totalProductions;
    public Farmer(FruitMarket market, String fruit, int totalProductions) {
        this.market = market;
        this.fruit = fruit;
        this.totalProductions = totalProductions;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < totalProductions; i++) {
                market.produce(fruit, 1); // Produce one unit of the fruit each time
                Thread.sleep(1000); // Simulate time taken to produce fruits
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
class Consumer implements Runnable {
    private final FruitMarket market;
    private final String fruit;
    private final int totalConsumptions;
    public Consumer(FruitMarket market, String fruit, int totalConsumptions) {
        this.market = market;
        this.fruit = fruit;
        this.totalConsumptions = totalConsumptions;
    }
    @Override
    public void run() {
        try {
            for (int i = 0; i < totalConsumptions; i++) {
                market.consume(fruit, 1); // Consume one unit of the fruit each time
                Thread.sleep(500); // Simulate time taken to consume fruits
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
public class FruitMarketSimulation {
    public static void main(String[] args) {
        FruitMarket market = new FruitMarket(10); // Market capacity
        // Number of times to produce and consume
        int totalProductionsPerFarmer = 5; // Each farmer will produce a total of 5 times
        int totalConsumptionsPerConsumer = 5; // Each consumer will consume a total of 5 times
        // Start farmers for different fruits
        new Thread(new Farmer(market, "apple", totalProductionsPerFarmer)).start();
        new Thread(new Farmer(market, "orange", totalProductionsPerFarmer)).start();
        new Thread(new Farmer(market, "grape", totalProductionsPerFarmer)).start();
        new Thread(new Farmer(market, "watermelon", totalProductionsPerFarmer)).start();
        // Start consumers for different fruits
        new Thread(new Consumer(market, "apple", totalConsumptionsPerConsumer)).start();
        new Thread(new Consumer(market, "orange", totalConsumptionsPerConsumer)).start();
        new Thread(new Consumer(market, "grape", totalConsumptionsPerConsumer)).start();
        new Thread(new Consumer(market, "watermelon", totalConsumptionsPerConsumer)).start();
    }
}
