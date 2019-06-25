package com.example.jsfdemo.mbean;

import com.example.jsfdemo.data.Weapon;
import com.example.jsfdemo.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;

@Transactional
@Named
@ViewScoped
public class WeaponManagerMBean {

    private List<Weapon> weaponList;

    @Autowired
    private WeaponRepository repository;

    private Weapon selectedWeapon;

    @PostConstruct
    private void init() {
        loadAll();
        selectedWeapon = new Weapon();
    }

    public void save() {
        try {
            Weapon oldWeapon = repository.findByName(selectedWeapon.getName());
            if(oldWeapon!=null && selectedWeapon.getId() == null){
                throw new RuntimeException("Van már ilyen elem");
            }else if(oldWeapon!=null  && selectedWeapon.getId() != null && !oldWeapon.getId().equals(selectedWeapon.getId())){
                throw new RuntimeException("Van már ilyen elem");
            }
            if (selectedWeapon.getId() == null) {

                repository.save(selectedWeapon);

            } else {
                repository.update(selectedWeapon);
            }
            loadAll();
            selectedWeapon = new Weapon();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres adat mentés!", null));
        }catch (RuntimeException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validációs hiba", null));

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));

        }
    }

    public void delete(Long id) {
        try {
            repository.delete(id);
            loadAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }

    }

    public void selectOne(Weapon weapon) {
        selectedWeapon = weapon;

    }


    private void loadAll() {
        try {
            weaponList = repository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }

    public void setWeaponList(List<Weapon> weaponList) {
        this.weaponList = weaponList;
    }

    public Weapon getSelectedWeapon() {
        return selectedWeapon;
    }

    public void setSelectedWeapon(Weapon selectedWeapon) {
        this.selectedWeapon = selectedWeapon;
    }
}