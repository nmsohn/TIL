public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition ... conditions){
        super(conditions);
        this.discountAmount = discountAmount;
    }
        
    //추상 함수 구현
    @Override
    protected Money getDiscountAmount(Screening screening){
        return discountAmount;
    }
}