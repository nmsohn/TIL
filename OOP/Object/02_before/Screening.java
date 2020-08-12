public class Screening{
    //클래스의 경계 구분
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened){
        this.movie = movie;
        this.sequence = sequence;
        this.whenScreened = whenScreened;
    }

    public LocalDateTime getStartTime(){
        return whenScreened;
    }

    public boolean isSequence(int sequence){
        return this.sequence == sequence;
    }

    public Money getMovieFee(){
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount){
        // screening이 movie 에게 calculateMovieFee 메시지를 전송
        // Screening은 Movie 안에 메서드를 모름
        // Movie가 calculateMovieFee 메시지에 응답할거라 추측
        //Movie가 스스로 적절한 메서드를 선택하여 응답
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}