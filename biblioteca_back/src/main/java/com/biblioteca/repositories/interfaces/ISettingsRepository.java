package com.biblioteca.repositories.interfaces;

import com.biblioteca.models.Settings;

import java.util.List;

public interface ISettingsRepository {

    List<Settings> getSettingsByType( String infoType);
}
