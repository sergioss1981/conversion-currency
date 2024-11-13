package br.com.challenge.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "conversion_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConversionRate extends PanacheEntity {

    public String fromCurrency;
    public String toCurrency;
    public double rate;
    public LocalDateTime timestamp;
}
