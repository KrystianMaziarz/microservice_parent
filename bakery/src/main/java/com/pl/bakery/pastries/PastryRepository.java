package com.pl.bakery.pastries;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PastryRepository extends JpaRepository<PastryEntity, Long> {}
