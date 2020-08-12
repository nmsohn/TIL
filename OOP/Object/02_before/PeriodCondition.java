import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {
    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    public PeriodCondition(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime){
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //상영 기간에 있음 && 상영 시작 시간 후 && 상영 종료 시간 전 
    public boolean isSatisfiedBy(Screening screening){
        return screening.getStartTime().getDayOfWeek().equals(dayOfWeek) && startTime.compareTo(screening.getStartTime().toLocalTime()) <= 0 && endTime.compareTo(screening.getStartTime().toLocalTime()) >= 0;
    }
}