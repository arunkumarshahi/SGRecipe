package com.sg.recipe.recipe.repositories;

import com.sg.recipe.recipe.model.UnitOfMeasure;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
class UOMRepositoryIT {
@Autowired
    UOMRepository uomRepository;
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DirtiesContext
    void findByUom() {
        Optional<UnitOfMeasure> optionalUnitOfMeasure= uomRepository.findByUom("Cup");
        assertEquals(optionalUnitOfMeasure.get().getUom(),"Cup");
    }
    @Test
    void findByUombyOunce() {
        log.debug("findByUombyOunce invoked");
        Optional<UnitOfMeasure> optionalUnitOfMeasure= uomRepository.findByUom("Ounce");
        assertEquals(optionalUnitOfMeasure.get().getUom(),"Ounce","findByUombyOunce test passed");
    }
}