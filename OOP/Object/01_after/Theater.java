public class Theater{
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller){
        this.ticketSeller ticketSeller;
    }

    //의존성 문제 -> 자율성을 높이기
    public void enter(Audience audience){
        //sellTo의 내부 코드가 바뀌어도 theater에는 영향이 없음
        ticketSller.sellTo(audience);
        // if(audience.getBag().hasInvitation()){
        //     Ticket ticket = ticketSeller.getTicketOffice().getTicket();
        //     audience.getBag().setTicket(ticket);
        // }else{
        //     Ticket ticket = ticketSeller.getTicketOffice().getTicket();
        //     audience.getBag().minusAmount(ticket.getFee());
        //     ticketSeller.getTicketOffice().plusAmount(ticket.getfee());
        //     ticketSeller.getTicketOffice().plusAmount(ticket.getFee());
        //     audience.getBag().setTicket(ticket);
        // }
    }
}