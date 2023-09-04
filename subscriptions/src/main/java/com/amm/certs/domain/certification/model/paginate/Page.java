package com.amm.certs.domain.certification.model.paginate;

public record Page (Integer pageNumber, Integer resultsPerPage, Integer totalPages, Long totalElements) {
}
