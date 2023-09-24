package com.ray3k.template.entities;

import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineRegister.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityRegister extends Entity {
    public GraphNode graphNode;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds);
        animationState.setAnimation(0, animationAnimation, true);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (zebra == null || grabbedCharacter != null) return;
        
        var containsPoint = containsPoint(mouseX, mouseY);
        var animation = containsPoint ? animationHighlight : animationAnimation;
        if (animationState.getCurrent(0).getAnimation() != animation) animationState.setAnimation(0, animation, true);
        if (containsPoint) newCursor = cursorPoint;
        
        if (containsPoint && Core.isButtonJustPressed(ANY_BUTTON)) {
            zebra.calculateNewPath(graphNode);
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
}
