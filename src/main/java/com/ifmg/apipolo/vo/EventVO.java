package com.ifmg.apipolo.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ifmg.apipolo.entity.EventCard;
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

    public EventVO(EventCard eventCard) {
        this.id = eventCard.getId();
        this.month = eventCard.getMonth();
        this.day = eventCard.getDay();
        this.title = eventCard.getTitle();
        this.hour = eventCard.getHour();
        this.local = eventCard.getLocal();
    }
}
