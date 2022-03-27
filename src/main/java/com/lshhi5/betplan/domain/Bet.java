package com.lshhi5.betplan.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Getter
@Setter
@Entity
public class Bet {

    @Id
    @GeneratedValue
    @Column(name = "bet_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private BetStatus status;

    private long money;
    private long dueDate;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    //==연관관계 메소드==//
    public void setMember(Member member) {
        this.member = member;
        member.getBets().add(this);
    }

    //== 생성 메소드==//
    public static Bet createBet(Member member, long money, long dueDate, LocalDateTime betTime, BetStatus status) {
        Bet bet = Bet.builder()
                .status(BetStatus.BETTING)
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(dueDate))
                .money(money)
                .dueDate(dueDate)
                .build();

        bet.setMember(member);
        return bet;
    }

    //== 비즈니스 로직==//

    public void success() {


    }

    @Builder
    public Bet(BetStatus status, long money, long dueDate, LocalDateTime startTime, LocalDateTime endTime) {
        this.status = status;
        this.money = money;
        this.dueDate = dueDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
