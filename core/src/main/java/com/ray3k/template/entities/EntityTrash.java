package com.ray3k.template.entities;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.ray3k.template.*;
import com.ray3k.template.entities.EntityCharacter.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineRegister.animationAnimation;
import static com.ray3k.template.Resources.SpineTrash.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityTrash extends Entity {
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
        if (zebra == null || grabbedCharacter == null || !grabbedCharacter.dead) return;
        
        var containsPoint = containsPoint(mouseX, mouseY);
        var animation = containsPoint ? animationHighlight : animationAnimation;
        if (animationState.getCurrent(0).getAnimation() != animation) animationState.setAnimation(0, animation, true);
        
        if (containsPoint && (Core.isButtonJustReleased(ANY_BUTTON))) {
            Resources.sfx_rim.play(sfx);
            animationState.setAnimation(0, animationShake, false);
            grabbedCharacter.destroy = true;
            grabbedCharacter = null;
            stage.addAction(Actions.delay(2f, Actions.run(() -> {
                GameScreen.showGoHomeDialog();
            })));
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
}
