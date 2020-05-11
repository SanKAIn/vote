package ru.alex.myvote.web;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.alex.myvote.model.History;
import ru.alex.myvote.repository.HistoryRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.alex.myvote.util.DateTimeUtil.atStartOfDayOrMin;
import static ru.alex.myvote.util.DateTimeUtil.atStartOfNextDayOrMax;

@RestController
@RequestMapping("/ajax/history")
public class HistoryUIController {

    private HistoryRepository repository;

    public HistoryUIController(HistoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable("history")
    public List<History> getAll() {
        return repository.getAll();
    }

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<History> getBetween(
            @RequestParam @Nullable LocalDate startDate,
            @RequestParam @Nullable LocalDate endDate) {
        return repository.getBetweenHalfOpen(atStartOfDayOrMin(startDate), atStartOfNextDayOrMax(endDate));
    }
}
