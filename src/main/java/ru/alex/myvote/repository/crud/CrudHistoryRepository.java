package ru.alex.myvote.repository.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.alex.myvote.model.History;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudHistoryRepository extends JpaRepository<History, Integer> {

    @Query("SELECT h FROM History h ORDER BY h.voteDate DESC, h.vote DESC")
    List<History> getAll();

    @Query("SELECT h from History h WHERE h.voteDate >= :startDate AND h.voteDate <= :endDate ORDER BY h.voteDate DESC, h.vote DESC")
    List<History> getBetweenHalfOpen(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}

