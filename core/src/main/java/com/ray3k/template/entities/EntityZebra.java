package com.ray3k.template.entities;

import com.esotericsoftware.spine.Animation;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import space.earlygrey.simplegraphs.Path;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SpineZebra.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityZebra extends Entity {
    private Path<GraphNode> pathToFollow;
    public GraphNode currentPoint, nextPoint;
    public static final float MOVE_SPEED = 600;
    public static final float MOVE_SPEED_INTRO = 200;
    public float moveSpeed;
    public EntityChairWaiting chair;
    
    @Override
    public void create() {
        setSkeletonData(skeletonData, animationData);
        animationData.setDefaultMix(.1f);
        animationState.setAnimation(0, animationWalk, true);
        
        calculateNewPath(rallyNode);
        moveSpeed = MOVE_SPEED_INTRO;
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        depth = DEPTH_ENTITIES + y;
        
        moveToNextNode();
        
        if (currentPoint instanceof GraphNodeRegister) {
            GameScreen.clearCustomerInLine();
        }
    }
    
    private void moveToNextNode() {
        if (nextPoint == null && pathToFollow == null) return;
        
        if (nextPoint == null) {
            nextPoint = pathToFollow.get(0);
            pathToFollow.remove(0);
            if (pathToFollow.size == 0) pathToFollow = null;
        }
        
        var hitTarget = !moveTowardsTarget(moveSpeed, nextPoint.coord.x, nextPoint.coord.y);
        if (hitTarget) {
            currentPoint = nextPoint;
            nextPoint = null;
        }
        
        Animation newAnimation = pathToFollow == null && hitTarget ? animationStand : animationWalk;
        if (pathToFollow == null && hitTarget && currentPoint instanceof GraphNodeChair) {
            newAnimation = animationOperation;
            if (chair.character != null) chair.character.operate();
        }
        if (pathToFollow == null && hitTarget && currentPoint instanceof GraphNodeGumBall) {
            gumballMachine.process();
        }
        
        if (animationState.getCurrent(0).getAnimation() != newAnimation && newAnimation != null)
            animationState.setAnimation(0, newAnimation, true);
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
    
    }
    
    public void calculateNewPath(GraphNode newPoint) {
        moveSpeed = MOVE_SPEED;
        var point = nextPoint != null ? nextPoint : currentPoint;
        if (point == newPoint) return;
        
        pathToFollow = graph.algorithms().findShortestPath(point, newPoint, (a, b) -> a.coord.dst2(b.coord));
        
        if (pathToFollow.size == 0) {
            pathToFollow = null;
            return;
        }
        
        pathToFollow.remove(0);
    }
    
    public void stopOperation() {
        if (animationState.getCurrent(0).getAnimation() == animationOperation) {
            animationState.setAnimation(0, animationOperationDone, true);
        }
    }
}
