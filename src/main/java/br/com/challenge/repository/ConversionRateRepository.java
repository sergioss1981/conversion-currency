package br.com.challenge.repository;

import br.com.challenge.exception.ExternalApiException;
import br.com.challenge.model.ConversionRate;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ConversionRateRepository implements PanacheRepository<ConversionRate> {
    public List<ConversionRate> findFilteredRates(String fromCurrency, String toCurrency) {
        try {
            StringBuilder queryBuilder = new StringBuilder("SELECT cr FROM ConversionRate cr WHERE 1=1");

            if (fromCurrency != null) {
                queryBuilder.append(" AND cr.fromCurrency = :fromCurrency");
            }
            if (toCurrency != null) {
                queryBuilder.append(" AND cr.toCurrency = :toCurrency");
            }

            TypedQuery<ConversionRate> query = getEntityManager().createQuery(queryBuilder.toString(), ConversionRate.class);

            if (fromCurrency != null) {
                query.setParameter("fromCurrency", fromCurrency);
            }

            if (toCurrency != null) {
                query.setParameter("toCurrency", toCurrency);
            }

            return query.getResultList();

        } catch (Exception e) {
            throw new ExternalApiException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Erro ao buscar na base as taxas de conversão filtradas: " + e.getMessage());
        }
    }
}

