package services;

import models.Product;
import models.Shippable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {

    /**
     * This method generates a shipment notice for a list of shippable items.
     * It prints the name and weight of each item, and the total weight of the package.
     *
     * @param items List of shippable items to be shipped
     */
    public void shipNotice(List<Shippable> items) {

        if (items == null || items.isEmpty()) {
            System.out.println("No items to ship.");
            return;
        }

        Map<String, Integer> counts = new LinkedHashMap<>();
        Map<String, Double> weights = new HashMap<>();
        double totalWeight = 0;

        for (Shippable item : items) {
            String name = item.getName();
            counts.put(name, counts.getOrDefault(name, 0) + 1);
            weights.put(name, item.getWeight());
            totalWeight += item.getWeight();
        }

        for (String name : counts.keySet()) {
            int count = counts.get(name);
            double weight = weights.get(name);
            System.out.printf("%dx %-13s %.0fg\n", count, name, weight * 1000);
        }

        System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
    }

}
