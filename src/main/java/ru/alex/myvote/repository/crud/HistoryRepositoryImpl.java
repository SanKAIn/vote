package ru.alex.myvote.repository.crud;

import org.springframework.stereotype.Repository;
import ru.alex.myvote.model.History;
import ru.alex.myvote.repository.HistoryRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class HistoryRepositoryImpl implements HistoryRepository {

    private CrudHistoryRepository repository;

    public HistoryRepositoryImpl(CrudHistoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public History save(History history) {
        return repository.saveAndFlush(history);
    }

    @Override
    public List<History> getAll() {
        return repository.getAll();
    }

    @Override
    public List<History> getBetweenHalfOpen(LocalDate startDate, LocalDate endDate) {
        return repository.getBetweenHalfOpen(startDate, endDate);
    }
}
