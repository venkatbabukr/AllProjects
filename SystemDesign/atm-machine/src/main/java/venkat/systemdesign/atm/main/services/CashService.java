package venkat.systemdesign.atm.main.services;

import com.sun.source.tree.Tree;
import venkat.systemdesign.atm.util.MathUtil;

import java.util.*;

public class CashService {

    public TreeMap<Integer, Integer> getDispenseDenominations(Integer amount, Set<Integer> availableDenominations) {
        TreeSet<Integer> sortedDenominationsSet = new TreeSet<>(availableDenominations);

        TreeMap<Integer, Integer> denominationsMap = findDenominations(amount, sortedDenominationsSet);
        denominationsMap = reduceDenominations(denominationsMap, sortedDenominationsSet);
        return denominationsMap;
    }

    private Integer sumUpDenominationMap(TreeMap<Integer, Integer> denominationsMap) {
        return Optional.ofNullable(denominationsMap)
                .map(m -> m.entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum())
                .orElse(null);
    }

    private TreeMap<Integer, Integer> findDenominations(Integer amount, TreeSet<Integer> sortedDenominationsSet) {
        for (Integer denomination : sortedDenominationsSet) {
            TreeMap<Integer, Integer> denominationsMap = new TreeMap<>();
            int denomCount = amount / denomination;
            if (denomCount > 0) {
                denominationsMap.put(denomination, denomCount);
                int remAmount = amount % denomination;
                denominationsMap.putAll(
                        findDenominations(remAmount, sortedDenominationsSet)
                );
            }
            int denominationTotal = sumUpDenominationMap(denominationsMap);
            if (denominationTotal == amount) {
                return denominationsMap;
            }
        }
        return new TreeMap<>();
    }

    // Right now assuming unlimited availability of denomination cash. Add limited availability feature later...
    private TreeMap<Integer, Integer> reduceDenominations(TreeMap<Integer, Integer> denominationsMap, TreeSet<Integer> sortedDenominationsSet) {
        if (sortedDenominationsSet != null && !sortedDenominationsSet.isEmpty()) {
            Iterator<Integer> denominationsIterator = sortedDenominationsSet.iterator();
            Integer prevDenomination = denominationsIterator.next();
            while (denominationsIterator.hasNext()) {
                Integer currentDemonimation = denominationsIterator.next();
                int lcmDenomination = MathUtil.lcm(prevDenomination, currentDemonimation);
                int prevDenominsConvertionFactor = lcmDenomination / prevDenomination;
                int previousDenominationCount = denominationsMap.getOrDefault(prevDenomination, 0);
                int amountToTransfer = 0;
                while (previousDenominationCount >= prevDenominsConvertionFactor) {
                    amountToTransfer += prevDenomination * prevDenominsConvertionFactor;
                    previousDenominationCount -= prevDenominsConvertionFactor;
                }
                if (amountToTransfer > 0) {
                    int currentDenominationsToAdd = amountToTransfer / currentDemonimation;
                    int currentDenominationCount = denominationsMap.getOrDefault(currentDemonimation, 0);
                    denominationsMap.put(currentDemonimation, currentDenominationCount + currentDenominationsToAdd);

                    if (previousDenominationCount > 0) {
                        denominationsMap.put(prevDenomination, previousDenominationCount);
                    } else {
                        denominationsMap.remove(prevDenomination);
                    }
                }
                prevDenomination = currentDemonimation;
            }
        }
        return denominationsMap;
    }

    public static void main(String[] args) {
        Set<Integer> denominations = Set.of(200, 500, 2000);
        int MIN_AMOUNT = 100;
        int MAX_AMOUNT = 10000;

        CashService service = new CashService();

        for (int amount = MIN_AMOUNT; amount <= MAX_AMOUNT ; amount += 100) {
            System.out.format("Amount=%d, Denoms=%s%n",
                    amount,
                    service.getDispenseDenominations(amount, denominations));
        }
    }

}
