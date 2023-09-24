package com.ray3k.template.entities;

import com.ray3k.template.*;
import com.ray3k.template.screens.*;

public class GumballSpawn extends Entity {
    public EntityGumball gumball;
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    public void spawn() {
        if (gumball == null) {
            gumball = new EntityGumball();
            gumball.spawn = this;
            gumball.setPosition(x, y);
            Core.entityController.add(gumball);
        }
    }
}
