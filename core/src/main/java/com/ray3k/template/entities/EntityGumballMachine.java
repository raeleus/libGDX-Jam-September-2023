package com.ray3k.template.entities;

import com.ray3k.template.*;
import com.ray3k.template.screens.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineGumballMachine.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityGumballMachine extends Entity {
    public GraphNode graphNode;
    public boolean processing;
    public float processTimer;
    public static final float PROCESS_DELAY = 6f;
    
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
        if (processing) {
            processTimer -= delta;
            if (processTimer < 0) {
                processing = false;
                animationState.setAnimation(0, animationAnimation, true);
                makeGumballs();
            }
        }
        
        if (zebra == null || grabbedCharacter != null || processing) return;
        
        var containsPoint = containsPoint(mouseX, mouseY);
        var animation = containsPoint ? animationHighlight : animationAnimation;
        if (animationState.getCurrent(0).getAnimation() != animation) animationState.setAnimation(0, animation, true);
        if (containsPoint) newCursor = cursorPoint;
        
        if (containsPoint && Core.isButtonJustPressed(ANY_BUTTON)) {
            if (zebra.currentPoint instanceof GraphNodeGumBall) process();
            else zebra.calculateNewPath(graphNode);
        }
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    public void process() {
        animationState.setAnimation(0, animationProcessing, true);
        processing = true;
        processTimer = PROCESS_DELAY;
    }
}
