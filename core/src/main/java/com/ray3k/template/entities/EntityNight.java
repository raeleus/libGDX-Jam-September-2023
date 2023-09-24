package com.ray3k.template.entities;

import static com.ray3k.template.Resources.SpineNight.*;

public class EntityNight extends Entity {
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationState.setAnimation(0, animationAnimation, false);
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
    
    public void showNight() {
        animationState.setAnimation(0, animationNightfall, false);
    }
}
