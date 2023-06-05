package pl.palkaszymon.sales.offer;

import java.math.BigDecimal;

public class TotalDiscountPolicy {
    private final BigDecimal totalThreshold;
    private final BigDecimal discountValue;

    public TotalDiscountPolicy(BigDecimal totalThreshold, BigDecimal discountValue) {

        this.totalThreshold = totalThreshold;
        this.discountValue = discountValue;
    }

    public static TotalDiscountPolicy noDiscount() {
        return new TotalDiscountPolicy(BigDecimal.ZERO, BigDecimal.ZERO);
    }

    public Offer apply(Offer offer) {

        if (!canApply(offer)) {
            return offer;
        }

        return new Offer(offer.getOrderLines(), offer.getTotal().subtract(this.discountValue));
    }

    private boolean canApply(Offer offer) {
        return offer.getTotal().compareTo(this.totalThreshold) > -1;
    }
}
