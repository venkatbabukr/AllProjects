package venkat.systemdesign.atm.common.model.cash;

import java.util.TreeSet;

import lombok.Data;

@Data
public class CashRack {
    private TreeSet<CashBox> cashBoxes;
}
