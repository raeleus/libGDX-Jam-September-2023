package com.ray3k.template.entities;

import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineGumball.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityGumball extends Entity {
    public GumballSpawn spawn;
    public boolean grabbed;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds);
        animationState.setAnimation(0, animationSpawn, false);
        animationState.addAnimation(0, animationAnimation, true,0);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (zebra == null || grabbedCharacter != null) return;
        
        if (!grabbed) {
            var containsPoint = containsPoint(mouseX, mouseY);
            var animation = containsPoint ? animationHighlight : animationAnimation;
            var current = animationState.getCurrent(0).getAnimation();
            if (current != animation && current != animationSpawn)
                animationState.setAnimation(0, animation, true);
            if (containsPoint) newCursor = cursorGrab;
            
            if (containsPoint && Core.isButtonJustPressed(ANY_BUTTON)) {
                grabbed = true;
                animationState.setAnimation(0, animationAnimation, false);
                newCursor = cursorPinch;
                holdingGumball = this;
            }
        } else {
            setPosition(mouseX, mouseY - 10);
            newCursor = cursorPinch;
            
            if (!Core.isButtonPressed(ANY_BUTTON) && !Core.isButtonJustReleased(ANY_BUTTON)) {
                grabbed = false;
                setPosition(spawn.x, spawn.y);
                holdingGumball = null;
                newCursor = cursorArrow;
            }
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
        holdingGumball = null;
        newCursor = cursorArrow;
        spawn.gumball = null;
    }
}
