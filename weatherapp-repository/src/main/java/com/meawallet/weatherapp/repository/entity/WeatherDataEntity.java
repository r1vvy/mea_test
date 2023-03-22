package com.meawallet.weatherapp.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TimeZoneStorage;
import org.hibernate.annotations.TimeZoneStorageType;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@Entity
@Table(name = "weather_info")
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "timestamp")
    @TimeZoneStorage(TimeZoneStorageType.NORMALIZE_UTC)
    ZonedDateTime timestamp;
    @Column(name = "air_temperature")
    BigDecimal airTemperature;
}
