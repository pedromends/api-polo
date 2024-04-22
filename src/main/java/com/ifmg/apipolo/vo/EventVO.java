package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventVO {

    private Long id;
    private String month;
    private String day;
    private String title;
    private String hour;
    private String local;

    public EventVO(Event event) {
        this.id = event.getId();
        this.month = event.getMonth();
        this.day = event.getDay();
        this.title = event.getTitle();
        this.hour = event.getHour();
        this.local = event.getLocal();
    }
}
