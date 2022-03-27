package com.lshhi5.betplan.repository;

import com.lshhi5.betplan.domain.Bet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BetRepository {

    private final EntityManager em;

    public void save(Bet bet) {
        em.persist(bet);
    }

    public Bet findOne(Long id) {
        return em.find(Bet.class, id);
    }

    public List<Bet> findAll() {
        return em.createQuery("select b from Bet b", Bet.class)
                .getResultList();
    }

}
