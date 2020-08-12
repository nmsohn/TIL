public class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition ... conditions){
        this.conditons = Arrays.asList(conditions);
    }

    public Money calculateDiscountAmount(Screening screening){
        for(DiscountCondition each : conditions){
            if(each.isSatisfiedBy(screening){
                if(each.isSatisfiedBy(screening)){
                    //조건을 만족하면 추상함수 호출
                    //자식 클래스에서 구현된 (오버라이딩) 메서드 실행
                    // 템플릿 메소드 패턴
                    return getDiscountAmount(screening);
                }
            })
        }

        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}