package com.ta.coremolde.master.repository;

import com.ta.coremolde.master.model.entity.Customization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomizationRepository extends JpaRepository<Customization, Integer> {

    Customization findCustomizationById(Integer id) throws IllegalArgumentException;

}
