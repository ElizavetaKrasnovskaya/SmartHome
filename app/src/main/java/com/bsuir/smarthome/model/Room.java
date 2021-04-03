package com.bsuir.smarthome.model;

/**
 * Smart Home
 * https://github.com/quintuslabs/SmartHome
 * Created on 27-OCT-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */

public class Room {
    Long id;
    String name;
    Long humidity;
    Long led;
    Long temperature;

    public Room() {
    }

    public Room(String name) {
        this.name = name;
    }

    public Room(Long id, String name, Long humidity, Long led, Long temperature) {
        this.id = id;
        this.name = name;
        this.humidity = humidity;
        this.led = led;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Long getLed() {
        return led;
    }

    public void setLed(Long led) {
        this.led = led;
    }

    public Long getTemperature() {
        return temperature;
    }

    public void setTemperature(Long temperature) {
        this.temperature = temperature;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
