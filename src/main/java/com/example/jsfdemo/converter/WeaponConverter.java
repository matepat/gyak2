package com.example.jsfdemo.converter;

import com.example.jsfdemo.data.Weapon;
import com.example.jsfdemo.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

@ApplicationScoped
@Named
public class WeaponConverter implements Converter {

    @Autowired
    private WeaponRepository repository;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null) {
            return null;
        }
        try {
            return repository.findByName(s);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {

        if (o == null) {
            return "";
        }
        return ((Weapon) o).getName();
    }
}