package com.example.aviatickets.repositories;

import com.example.aviatickets.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepo extends JpaRepository<Ticket, Long>{
  @Query("select f from Ticket f where f.amount>0")
  List<Ticket> findAllAvailable();
  @Query("SELECT p FROM Ticket p WHERE p.departure LIKE %?1%")
  public List<Ticket> findByDeparture(String keywordDep);
  @Query("SELECT z FROM Ticket z WHERE z.destination LIKE %?1%")
  public List<Ticket> findByDestination(String keywordDest);
  @Query("SELECT t FROM Ticket t WHERE t.strDepTime LIKE %?1%")
  public List<Ticket> findByDepTime(String keywordDepTime);
  @Query("SELECT r FROM Ticket r WHERE r.transportType LIKE %?1%")
  public List<Ticket> findByTransport(String keywordTransport);
  @Query("SELECT r FROM Ticket r WHERE r.departure LIKE %?1% AND r.destination LIKE %?2% AND r.strDepTime LIKE %?3% AND r.transportType LIKE %?4%")
  public List<Ticket> findByFilters(String keywordDep, String keywordDest,
                                    String keywordDepTime, String keywordTransport);
}
