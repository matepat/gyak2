package com.example.jsfdemo.repositories;

import com.example.jsfdemo.data.Robot;
import org.springframework.stereotype.Repository;

@Repository
public class RobotRepositoryImpl extends CoreCrudRepositoryImpl<Robot> implements RobotRepository{
    @Override
    public Class getManagedClass() {
        return Robot.class;
    }
}
