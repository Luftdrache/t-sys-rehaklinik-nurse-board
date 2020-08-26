package com.tsystems.javaschool.data;

import lombok.Getter;
import lombok.Setter;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ApplicationScoped
public class TreatmentEventsRepository {
    List<TreatmentEvent> treatmentEventList = new ArrayList<>();
}
