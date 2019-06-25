package com.example.jsfdemo.mbean;

import com.example.jsfdemo.data.Robot;
import com.example.jsfdemo.data.Weapon;
import com.example.jsfdemo.repositories.RobotRepository;
import com.example.jsfdemo.repositories.WeaponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Named
@ViewScoped
public class RobotManagerMBean {
    private List<Robot> robotList;
    private List<Weapon> weaponList;
    @Autowired
    private RobotRepository repository;

    @Autowired
    private WeaponRepository weaponRepository;

    private Robot selectedRobot;

    @PostConstruct
    private void init() {
        loadAll();
        selectedRobot = new Robot();
        try {
            weaponList = weaponRepository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public void save() {
        try {
            if (selectedRobot.getId() == null) {

                repository.save(selectedRobot);

            } else {
                repository.update(selectedRobot);
            }
            loadAll();
            selectedRobot = new Robot();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres adat mentés!", null));
        } catch (Exception e) {
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

    public void selectOne(Robot weapon) {
        selectedRobot = weapon;

    }

    private void loadAll() {
        try {
            robotList = repository.findAll();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Rendszerhiba", null));
        }
    }

    public List<Robot> getRobotList() {
        return robotList;
    }

    public void setRobotList(List<Robot> robotList) {
        this.robotList = robotList;
    }

    public Robot getSelectedRobot() {
        return selectedRobot;
    }

    public void setSelectedRobot(Robot selectedRobot) {
        this.selectedRobot = selectedRobot;
    }

    public List<Weapon> getWeaponList() {
        return weaponList;
    }
}
