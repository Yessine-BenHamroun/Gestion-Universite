package com.example.servicedou.external;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

public class HolidayApiResponse {
    private Meta meta;
    private Response response;

    public static class Meta {
        @JsonProperty("code")
        private int code;

        public int getCode() { return code; }
        public void setCode(int code) { this.code = code; }
    }

    public static class Response {
        @JsonProperty("holidays")
        private List<Holiday> holidays;

        public List<Holiday> getHolidays() { return holidays; }
        public void setHolidays(List<Holiday> holidays) { this.holidays = holidays; }
    }

    public static class Holiday {
        private String name;

        @JsonProperty("date")
        private HolidayDate date;

        public static class HolidayDate {
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
            private Date iso;

            public Date getIso() { return iso; }
            public void setIso(Date iso) { this.iso = iso; }
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public Date getDate() { return date != null ? date.getIso() : null; }
        public void setDate(HolidayDate date) { this.date = date; }
    }

    // Getters and setters
    public Meta getMeta() { return meta; }
    public void setMeta(Meta meta) { this.meta = meta; }
    public Response getResponse() { return response; }
    public void setResponse(Response response) { this.response = response; }
}