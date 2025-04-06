package com.example.servicedou.service;

import com.example.servicedou.external.HolidayApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class HolidayService {

    private static final String API_URL = "https://calendarific.com/api/v2/holidays?api_key=%s&country=%s&year=%d";
    private static final String SINGLE_DATE_API_URL = "https://calendarific.com/api/v2/holidays?api_key=%s&country=MA&year=%d&date=%s";

    @Value("${calendarific.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public HolidayService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public boolean isHoliday(Date date, String countryCode) {
        try {
            Calendar calInput = Calendar.getInstance();
            calInput.setTime(date);

            List<HolidayApiResponse.Holiday> holidays = getHolidaysForYear(countryCode, calInput.get(Calendar.YEAR));

            Calendar calHoliday = Calendar.getInstance();

            for (HolidayApiResponse.Holiday holiday : holidays) {
                if (holiday.getDate() != null) {
                    calHoliday.setTime(holiday.getDate());

                    if (calInput.get(Calendar.DAY_OF_YEAR) == calHoliday.get(Calendar.DAY_OF_YEAR) &&
                            calInput.get(Calendar.YEAR) == calHoliday.get(Calendar.YEAR)) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            throw new HolidayApiException("Erreur lors de la vérification des jours fériés", e);
        }
    }


    public List<HolidayApiResponse.Holiday> getHolidaysForYear(String country, int year) {
        String url = String.format(API_URL, apiKey, country.toUpperCase(), year);

        try {
            ResponseEntity<HolidayApiResponse> response = restTemplate.getForEntity(url, HolidayApiResponse.class);

            if (response.getStatusCode() != HttpStatus.OK) {
                throw new HolidayApiException("API a retourné le statut: " + response.getStatusCode());
            }

            HolidayApiResponse body = response.getBody();
            if (body == null || body.getResponse() == null) {
                return Collections.emptyList();
            }

            return body.getResponse().getHolidays();
        } catch (Exception e) {
            System.err.println("Erreur lors de l'appel à l'API Calendarific: " + e.getMessage());
            e.printStackTrace();
            throw new HolidayApiException("Échec de la récupération des jours fériés pour le pays: " + country + ", année: " + year, e);
        }
    }

    private boolean isValidResponse(ResponseEntity<HolidayApiResponse> response) {
        return response.getBody() != null
                && response.getBody().getMeta() != null
                && response.getBody().getMeta().getCode() == 200
                && response.getBody().getResponse() != null
                && response.getBody().getResponse().getHolidays() != null;
    }

    private int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    public static class HolidayApiException extends RuntimeException {
        // Constructeur avec message uniquement
        public HolidayApiException(String message) {
            super(message);
        }

        // Constructeur avec message et cause
        public HolidayApiException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    public String getHolidayName(Date date, String countryCode) {
        try {
            Calendar calInput = Calendar.getInstance();
            calInput.setTime(date);

            List<HolidayApiResponse.Holiday> holidays = getHolidaysForYear(countryCode, calInput.get(Calendar.YEAR));

            Calendar calHoliday = Calendar.getInstance();

            for (HolidayApiResponse.Holiday holiday : holidays) {
                if (holiday.getDate() != null) {
                    calHoliday.setTime(holiday.getDate());

                    if (calInput.get(Calendar.DAY_OF_YEAR) == calHoliday.get(Calendar.DAY_OF_YEAR) &&
                            calInput.get(Calendar.YEAR) == calHoliday.get(Calendar.YEAR)) {
                        return holiday.getName();
                    }
                }
            }
            return null;
        } catch (Exception e) {
            throw new HolidayApiException("Erreur lors de la récupération du nom du jour férié", e);
        }
    }


}