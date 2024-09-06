package com.biblioteca.services.interfaces;

import com.biblioteca.models.Settings;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ISettingsService {

    List<Settings> getFAQ();
    List<Settings> getSupport();
}
