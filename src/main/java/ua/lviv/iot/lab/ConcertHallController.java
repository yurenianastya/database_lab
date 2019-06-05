package ua.lviv.iot.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConcertHallController {

    @Autowired
    private ConcertHallRepository repository;

    public ConcertHallController(ConcertHallRepository repository) {

        this.repository = repository;

    }

    @GetMapping("/events")
    public List<ConcertHall> getAllEvents() {

        return (List<ConcertHall>) repository.findAll();

    }

    @GetMapping("/events/{id}")
    public ConcertHall getSpecificEvent(@PathVariable Integer id) {

        return repository.findById(id).get();
    }

    @PostMapping("/events")
    public ConcertHall newEvent(@RequestBody ConcertHall newEvent) {

        return repository.save(newEvent);

    }

    @PutMapping("/events/{id}")
    public ConcertHall updateEvent(@RequestBody ConcertHall newEvent, @PathVariable Integer id) {

        return repository.findById(id).map(event -> {
            event.setTicketPrice(newEvent.getTicketPrice());
            event.setArenaSize(newEvent.getArenaSize());
            event.setLocationStreet(newEvent.getLocationStreet());
            event.setEventDuration(newEvent.getEventDuration());
            event.setAreThereMusicians(newEvent.isAreThereMusicians());
            event.setMusiciansQuantity(newEvent.getMusiciansQuantity());
            return repository.save(event);
        }).orElseGet(() -> {
            newEvent.setId(id);
            return repository.save(newEvent);
        });
    }

    @DeleteMapping("/events/{id}")
    public ConcertHall deleteEvent(@PathVariable Integer id) {

        ConcertHall event_id  = getSpecificEvent(id);
        repository.deleteById(id);
        return event_id;

    }




}
