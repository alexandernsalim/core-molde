package com.ta.coremolde.repository;

import com.ta.coremolde.model.entity.Customization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizationRepository extends JpaRepository<Customization, Integer> {

    Customization findCustomizationById(Integer id) throws IllegalArgumentException;

}
