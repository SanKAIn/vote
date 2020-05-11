package ru.alex.myvote.repository;

import ru.alex.myvote.model.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepository {

    History save(History history);

    List<History> getAll();

    List<History> getBetweenHalfOpen(LocalDate startDateTime, LocalDate endDateTime);

}
