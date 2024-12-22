package com.biblioteca.services;

import com.biblioteca.models.Settings;
import com.biblioteca.repositories.interfaces.ISettingsRepository;
import com.biblioteca.services.interfaces.ISettingsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
@AllArgsConstructor
public class SettingsService implements ISettingsService {

    private ISettingsRepository settingsRepository;

    @Override
    public List<Settings> getSettingsByType(String infoType) {
        return settingsRepository.getSettingsByType(infoType);
    }
}
