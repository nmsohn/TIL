public class TicketSeller{
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice){
        this.ticketOffice = ticketOffice;
    }

    // public TicketOffice getTicketOffice(){
    //     return ticketOffice;
    // }

    //캡슐화 -> 객체 내부의 세부적인 사항을 감추는 것
    public void sellTo(Audience audience){
        ticketOffice.sellTicketTo(audience);
    }
}