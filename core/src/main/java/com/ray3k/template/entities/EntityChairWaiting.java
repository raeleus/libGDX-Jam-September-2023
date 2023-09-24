package com.ray3k.template.entities;

import com.ray3k.template.*;
import com.ray3k.template.entities.EntityCharacter.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineChair.animationData;
import static com.ray3k.template.Resources.SpineChair.animationHighlight;
import static com.ray3k.template.Resources.SpineChair.skeletonData;
import static com.ray3k.template.Resources.SpineChair.*;
import static com.ray3k.template.Resources.SpineRegister.animationAnimation;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityChairWaiting extends Entity {
    public GraphNode graphNode;
    public EntityCharacter character;
    public Need need;
    
    public EntityChairWaiting() {
    
    }
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds);
        animationState.setAnimation(0, animationAnimation, true);
        skeleton.setSkin(skinChair);
    }
    
    @Override
    public void actBefore(float delta) {
    }
    
    @Override
    public void act(float delta) {
        if (zebra == null || grabbedCharacter == null || grabbedCharacter.dead == true || character != null) return;
        
        var containsPoint = containsPoint(mouseX, mouseY);
        var animation = containsPoint ? animationHighlight : animationAnimation;
        if (animationState.getCurrent(0).getAnimation() != animation) animationState.setAnimation(0, animation, true);

        if (containsPoint && (Core.isButtonJustReleased(ANY_BUTTON))) {
            grabbedCharacter.setDown(this, graphNode);
            animationState.setAnimation(0, animationAnimation, true);
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
}
