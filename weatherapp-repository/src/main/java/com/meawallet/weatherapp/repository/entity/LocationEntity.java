package com.meawallet.weatherapp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@Entity
@Table(name = "locations")
@AllArgsConstructor
@NoArgsConstructor
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(name = "latitude")
    BigDecimal latitude;

    @Column(name = "longitude")
    BigDecimal longitude;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_info_id", referencedColumnName = "id")
    WeatherDataEntity weatherData;
}
