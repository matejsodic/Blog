package com.matej.blog.WeatherEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    @JsonProperty("Coord")
    private Coord coord;

    @JsonProperty("weather")
    private Weather2[] weather2;

    @JsonProperty("base")
    private String base;

    @JsonProperty("main")
    private Main2 main;

    @JsonProperty("wind")
    private Wind wind;

    @JsonProperty("clouds")
    private Clouds clouds;

    @ JsonProperty("dt")
    private String dt;

    @JsonProperty("sys")
    private Sys sys;

    @JsonProperty("wid")
    private String wid;

    @JsonProperty("wname")
    private String wname;

    @JsonProperty("cod")
    private String cod;

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public Weather2[] getWeather2() {
        return weather2;
    }

    public void setWeather2(Weather2[] weather2) {
        this.weather2 = weather2;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main2 getMain() {
        return main;
    }

    public void setMain(Main2 main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getWid() {
        return wid;
    }

    public void setWid(String wid) {
        this.wid = wid;
    }

    public String getWname() {
        return wname;
    }

    public void setWname(String wname) {
        this.wname = wname;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
}
