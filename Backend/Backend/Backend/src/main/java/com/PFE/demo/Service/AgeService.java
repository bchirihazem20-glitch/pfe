package com.PFE.demo.Service;

import com.PFE.demo.Entity.Enum.AgeCategory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class AgeService {

    public AgeCategory getCategory(LocalDate birthDate) {

        int age = Period.between(birthDate, LocalDate.now()).getYears();

        if (age >= 4 && age <= 7) return AgeCategory.U4;
        if (age >= 8 && age <= 11) return AgeCategory.U8;
        if (age >= 12 && age <= 15) return AgeCategory.U12;
        return AgeCategory.U16;
    }
}