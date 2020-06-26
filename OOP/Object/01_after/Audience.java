public class Audience{
    private Bag bag;

    //bag을 내부로 캡슐화
    //audience만 알 수 있음
    public Audience(Bag bag){
        this.bag = bag;
    }

    // public Bag getBag(){
    //     return bag;
    // }
    public Long buy(Ticket ticket){
        bag.hold();
    }
}