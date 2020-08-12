public class Movie {
    private String title;
    private Duration runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(Stirng title, Duration runningTime, Money fee, DiscountPolicy discountPolicy){
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        //오직 하나의 할인 정책만 적용됨
        this.discountPolicy = discountPolicy;
    }

    public Money getFee(){
        return fee;
    }

    public Money calculateMovieFee(Screening screening){
        // 할인 정책이 없는 경우
        // Movie가 책임을 가지는 것은 설계 측면에서 좋지 않음
        // 예외 케이스를 최소화하고 일관성을 유지할 수 있는 방법을 선택할 것
        // 할인 요금이 없는 시나리오는 DiscountPolicy가 책임을 져야함
        // if(discountPolicy == null){
        //     return fee;
        // }
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

    public void changeDiscountPolicy(DiscountPolicy discountPolicy){
        this.discountPolicy = discountPolicy;
    }
}