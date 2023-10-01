package com.ray3k.template.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationState.AnimationStateAdapter;
import com.esotericsoftware.spine.AnimationState.TrackEntry;
import com.esotericsoftware.spine.Skin;
import com.ray3k.template.*;
import com.ray3k.template.screens.*;
import space.earlygrey.simplegraphs.Path;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.SkinSkinStyles.*;
import static com.ray3k.template.Resources.SpineCharacter.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.entities.EntityCharacter.Mode.*;
import static com.ray3k.template.screens.GameScreen.*;

public class EntityCharacter extends Entity {
    private Path<GraphNode> pathToFollow;
    public GraphNode currentPoint, nextPoint;
    private Skin skinTemplate;
    private EntityChairWaiting chair;
    public enum Mode {
        ENTERING, WAITING, GRABBED, OPERATION, PAY, LEAVE
    }
    
    public Mode mode;
    public Need need;
    
    private final static Vector2 temp = new Vector2();
    private Table tableIndicator, tableHearts;
    public int hearts;
    public final static int HEARTS_MIN = 3;
    public final static int HEARTS_MAX = 5;
    public float heartTick;
    public final static float HEART_TICK_DELAY = 13f;
    public final static float REWARD = 100;
    public float reward;
    public boolean killMe;
    public boolean dead;
    
    @Override
    public void create() {
        characters.add(this);
        hearts = MathUtils.random(HEARTS_MIN, HEARTS_MAX);
        heartTick = HEART_TICK_DELAY;
        mode = ENTERING;
        setSkeletonData(skeletonData, animationData);
        animationData.setDefaultMix(.1f);
        animationData.setMix(animationPickedup, animationStand, 0);
        animationData.setMix(animationStand, animationXray, 0);
        animationState.setAnimation(0, animationMouthNeutral, true);
        animationState.setAnimation(1, animationWalk, true);
        animationState.setAnimation(2, animationNoHighlight, true);
        
        animationState.addListener(new AnimationStateAdapter() {
            @Override
            public void complete(TrackEntry entry) {
                if (entry.getAnimation() == animationXray) {
                    sfx_drill.play(sfx);
                    addHeart();
                    animationState.setAnimation(1, animationStand, true);
                    updateNeed();
                    updateTableIndicator();
                    zebra.stopOperation();
                    reward += REWARD;
                    
                    var particleEntity = new ParticleEntity(particleCheck);
                    entityController.add(particleEntity);
                    particleEntity.setPosition(EntityCharacter.this.x, EntityCharacter.this.y);
                }
            }
        });
        
        skinHuman();
        
        chair = GameScreen.getFreeWaitingChair();
        calculateNewPath(chair.graphNode);
        
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds);
        
        need = availableNeeds.random();
        tableIndicator = new Table();
        updateTableIndicator();
        tableIndicator.pack();
        
        tableHearts = new Table();
        updateTableHearts();
        tableHearts.pack();
        
        
        GameScreen.stage.addActor(tableIndicator);
        GameScreen.stage.addActor(tableHearts);
    }
    
    public void updateNeed() {
        if (need == Need.WHITE) need = Need.YELLOW;
        else if (need == Need.YELLOW) need = Need.BLUE;
        else if (need == Need.BLUE) need = availableNeeds.contains(Need.ORANGE, true) ? Need.ORANGE : null;
        else need = null;
        
        if (need == null) {
            thanksSounds.random().play(sfx);
            mode = PAY;
            if (chair != null) {
                chair.character = null;
                chair = null;
            }
            calculateNewPath(GameScreen.getFreeLineNode());
        } else {
            mode = need == chair.need ? OPERATION : WAITING;
        }
    }
    
    public void updateTableIndicator() {
        tableIndicator.clearChildren();
        if (need != null) switch (need) {
            case BLUE:
                var image = new Image(skin, "image-want-blue");
                image.setScaling(Scaling.none);
                tableIndicator.add(image);
                break;
            case WHITE:
                image = new Image(skin, "image-want-white");
                image.setScaling(Scaling.none);
                tableIndicator.add(image);
                break;
            case ORANGE:
                image = new Image(skin, "image-want-orange");
                image.setScaling(Scaling.none);
                tableIndicator.add(image);
                break;
            case YELLOW:
                image = new Image(skin, "image-want-yellow");
                image.setScaling(Scaling.none);
                tableIndicator.add(image);
                break;
        }
    }
    
    public void updateTableHearts() {
        tableHearts.clearChildren();
        
        if (mode != Mode.LEAVE) {
            for (int i = 0; i < hearts; i++) {
                var image = new Image(skin, "image-heart");
                image.setScaling(Scaling.none);
                tableHearts.add(image);
            }
            
            for (int i = 0; i < HEARTS_MAX - hearts; i++) {
                var image = new Image(skin, "image-heart-empty");
                image.setScaling(Scaling.none);
                tableHearts.add(image);
            }
            
            if (hearts >= 5) animationState.setAnimation(0, animationMouthHappy, true);
            else if (hearts >= 3) animationState.setAnimation(0, animationMouthNeutral, true);
            else animationState.setAnimation(0, animationMouthSad, true);
        }
    }
    
    public void skinHuman() {
        var skins = new Array<>(new Skin[] {skinBody2, skinBody3, skinBody4, skinBody5, skinBody6, skinBody7, skinBody8});
        var male = MathUtils.randomBoolean();
        var hairs = male ? new Array<>(new Skin[] {skinHairMaleBlack1, skinHairMaleBlack2, skinHairMaleBlack3, skinHairMaleBlack4, skinHairMaleBlack5, skinHairMaleBlack6}) :
                new Array<>(new Skin[] {skinHairFemaleBlack1, skinHairFemaleBlack2, skinHairFemaleBlack3, skinHairFemaleBlack4, skinHairFemaleBlack5, skinHairFemaleBlack6});
        
        skinTemplate = new Skin("skin");
        skinTemplate.addSkin(skins.random());
        skinTemplate.addSkin(hairs.random());
        skeleton.setSkin(skinTemplate);
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
        if (zebra == null) return;
        
        if (mode != LEAVE && !dead && !killMe) {
            heartTick -= delta;
            if (heartTick < 0) {
                heartTick = HEART_TICK_DELAY;
                subtractHeart();
                var particleEntity = new ParticleEntity(particleAngry);
                entityController.add(particleEntity);
                particleEntity.setPosition(EntityCharacter.this.x, EntityCharacter.this.y);
            }
        }
        
        if (holdingGumball != null && hearts < HEARTS_MAX) {
            moveToNextNode();
            var containsPoint = containsPoint(mouseX, mouseY);
            var animation = containsPoint ? animationHighlight : animationNoHighlight;
            if (animationState.getCurrent(2).getAnimation() != animation)
                animationState.setAnimation(2, animation, true);
            if (containsPoint) newCursor = cursorGrab;
            
            if (containsPoint && Core.isButtonJustReleased(ANY_BUTTON)) {
                sfx_hmm.play(sfx);
                holdingGumball.destroy = true;
                holdingGumball = null;
                animationState.setAnimation(2, animationNoHighlight, false);
                addHeart();
                var particleEntity = new ParticleEntity(particleHeart);
                entityController.add(particleEntity);
                particleEntity.setPosition(x, y);
            }
        } else if (grabbedCharacter == null && (mode == WAITING || dead)) {
            depth = DEPTH_ENTITIES + y;
            var containsPoint = containsPoint(mouseX, mouseY);
            var animation = containsPoint ? animationHighlight : animationNoHighlight;
            if (animationState.getCurrent(2).getAnimation() != animation)
                animationState.setAnimation(2, animation, true);
            if (containsPoint) newCursor = cursorGrab;
            
            if (containsPoint && Core.isButtonJustPressed(ANY_BUTTON)) {
                sfx_click.play(sfx);
                animationState.setAnimation(2, animationNoHighlight, true);
                if (!dead) animationState.setAnimation(1, animationPickedup, true);
                grabbedCharacter = this;
                mode = GRABBED;
            }
        } else if (grabbedCharacter == this && mode == GRABBED) {
            newCursor = cursorPinch;
            depth = DEPTH_DRAGGING;
            setPosition(mouseX, mouseY);
            
            if (!isButtonPressed(ANY_BUTTON) && !isButtonJustReleased(ANY_BUTTON)) {
                setDown(chair, chair.graphNode);
            }
        } else if (grabbedCharacter == null && mode == OPERATION) {
            depth = DEPTH_ENTITIES + y;
            var containsPoint = containsPoint(mouseX, mouseY) && animationState.getCurrent(1).getAnimation() != animationXray;
            var animation = containsPoint ? animationHighlight : animationNoHighlight;
            if (animationState.getCurrent(2).getAnimation() != animation)
                animationState.setAnimation(2, animation, true);
            if (containsPoint) newCursor = cursorPoint;
            
            if (containsPoint && Core.isButtonJustPressed(ANY_BUTTON)) {
                sfx_click.play(sfx);
                if (zebra.currentPoint == chair.graphNode) operate();
                else {
                    zebra.calculateNewPath(chair.graphNode);
                    zebra.chair = chair;
                }
            }
        } else {
            moveToNextNode();
            depth = DEPTH_ENTITIES + y;
        }
        
        temp.setZero();
        skeleton.findBone("indicator").localToWorld(temp);
        tableIndicator.setPosition(temp.x, temp.y, Align.center);
        
        temp.setZero();
        skeleton.findBone("hearts").localToWorld(temp);
        tableHearts.setPosition(temp.x, temp.y, Align.center);
    }
    
    private void moveToNextNode() {
        if (nextPoint == null && pathToFollow == null) {
            if (currentPoint instanceof GraphNodeChair) {
                mode = need == chair.need ? OPERATION : WAITING;
                var chairNode = ((GraphNodeChair) currentPoint);
                chairNode.chair.character = this;
            }
            return;
        }
        
        if (nextPoint == null) {
            nextPoint = pathToFollow.get(0);
            pathToFollow.remove(0);
            if (pathToFollow.size == 0) pathToFollow = null;
        }
        
        var hitTarget = !moveTowardsTarget(300, nextPoint.coord.x, nextPoint.coord.y);
        if (hitTarget) {
            currentPoint = nextPoint;
            nextPoint = null;
            if (pathToFollow == null && currentPoint instanceof GraphNodeLine) ((GraphNodeLine) currentPoint).character = this;
            if (pathToFollow == null && currentPoint instanceof GraphNodeExit) destroy = true;
        }
        
        Animation newAnimation = hitTarget ? animationStand : animationWalk;
        if (animationState.getCurrent(1).getAnimation() != newAnimation && newAnimation != null)
            animationState.setAnimation(1, newAnimation, true);
    }
    
    @Override
    public void draw(float delta) {
    
    }
    
    @Override
    public void destroy() {
        tableHearts.remove();
        tableIndicator.remove();
        characters.removeValue(this, true);
    }
    
    /**
     * Finds a new path from the current point to newPoint
     * @param newPoint The new point clicked on to find a new path for
     */
    public void calculateNewPath(GraphNode newPoint) {
        // use next point as start point whenever we're currently traveling down a path
        // so that we finish traveling to the next point and then switch over to the new path
        var point = nextPoint != null ? nextPoint : currentPoint;
        // No need to set a new path when we're already there
        if (point == newPoint)
            return;
        
        pathToFollow = graph.algorithms().findShortestPath(point, newPoint, (a, b) -> a.coord.dst2(b.coord));
        
        if (pathToFollow.size == 0) {
            pathToFollow = null;
            return;
        }
        
        pathToFollow.remove(0); // no need for the start point as we're on it anyway
    }
    
    public void setDown(EntityChairWaiting chair, GraphNode graphNode) {
        sfx_thud.play(sfx);
        this.chair.character = null;
        this.chair = chair;
        chair.character = this;
        currentPoint = graphNode;
        nextPoint = null;
        pathToFollow = null;
        setPosition(graphNode.coord.x, graphNode.coord.y);
        grabbedCharacter = null;
        mode = need == chair.need ? OPERATION : WAITING;
        
        if (animationState.getCurrent(1).getAnimation() != animationStand && !dead) animationState.setAnimation(1, animationStand, true);
    }
    
    float smashTotal;
    float smashDegrade = 3f;
    int bracesIndex;
    Skin bracesSkin;
    
    public void operate() {
        heartTick = HEART_TICK_DELAY;
        
        if (killMe) {
            if (grabbedCharacter != null) grabbedCharacter.setDown(grabbedCharacter.chair, grabbedCharacter.chair.graphNode);
            Gdx.graphics.setSystemCursor(SystemCursor.None);

            var mouseImage = new SpineImage(skeletonRenderer, SpineScalpel.skeletonData, SpineScalpel.animationData) {
                @Override
                public void act(float delta) {
                    super.act(delta);
                    setPosition(mouseX, mouseY);
                    toFront();
                }
            };
            mouseImage.setScaling(Scaling.none);
            mouseImage.pack();

            var dialog = new Dialog("", wDefault);

            var stack = new Stack();
            dialog.getContentTable().add(stack).size(400);

            var container = new Container();
            container.setClip(true);
            stack.add(container);

            var spineImage = new SpineImage(skeletonRenderer, skeletonData, animationData);
            spineImage.setScaling(Scaling.fit);
            var skin = new Skin("skin");
            skin.addSkin(skinTemplate);
            spineImage.spineDrawable.getSkeleton().setSkin(skin);
            spineImage.setTouchable(Touchable.disabled);
            var animationState = spineImage.spineDrawable.getAnimationState();
            animationState.setAnimation(0, animationZoomed, false);
            animationState.setAnimation(1, animationMouthSmile, false);
            container.setActor(spineImage);
            dialog.addListener(new InputListener() {
                boolean passed;
                Vector2 last = new Vector2();
                boolean hiding;

                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    var distance = last.dst(x, y);

                    if (distance > 10 && !passed) {
                        passed = true;
                        sfx_blood.play(sfx);
                        var particleEffect = new ParticleEffect(particleBlood);
                        particleEffect.reset(false);
                        var particleImage = new ParticleImage(particleEffect);
                        dialog.addActor(particleImage);
                        particleImage.setTouchable(Touchable.disabled);
                        particleImage.pack();
                        particleImage.setSize(200, 200);
                        temp.set(512, 188);
                        dialog.stageToLocalCoordinates(temp);
                        particleImage.setPosition(temp.x, temp.y, Align.center);
                        particleImage.particleDrawable.particleEffect.setPosition(temp.x, temp.y);

                        animationState.setAnimation(4, animationDead, true);
                        EntityCharacter.this.animationState.setAnimation(1, animationDead, true);
                        dead = true;
                    }

                    if (!hiding && passed) {
                        hiding = true;
                        stage.addAction(Actions.delay(5f, Actions.run(() -> {
                            dialog.hide();
                            Gdx.input.setInputProcessor(inputMultiplexer);
                            EntityCharacter.this.updateTableIndicator();
                            zebra.stopOperation();

                            mouseImage.remove();
                            Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
                            Gdx.graphics.setCursor(cursorArrow);
                            GameScreen.showOhShit();
                        })));
                    }
                    return true;
                }
            });

            container = new Container();
            container.top();
            stack.add(container);

            var label = new Label("BRUSH!", lDefault);
            container.setActor(label);

            dialog.show(stage);
            Gdx.input.setInputProcessor(stage);

            stage.addActor(mouseImage);
        } else if (chair.need == Need.WHITE) {
            animationState.setAnimation(1, animationXray, false);
        } else if (chair.need == Need.YELLOW) {
            if (grabbedCharacter != null) grabbedCharacter.setDown(grabbedCharacter.chair, grabbedCharacter.chair.graphNode);
            Gdx.graphics.setSystemCursor(SystemCursor.None);
            
            var mouseImage = new SpineImage(skeletonRenderer, SpineToothbrush.skeletonData, SpineToothbrush.animationData) {
                @Override
                public void act(float delta) {
                    super.act(delta);
                    setPosition(mouseX, mouseY);
                    toFront();
                }
            };
            mouseImage.setScaling(Scaling.none);
            mouseImage.setTouchable(Touchable.disabled);
            mouseImage.pack();
            
            var dialog = new Dialog("", wDefault);
            
            var stack = new Stack();
            dialog.getContentTable().add(stack).size(400);
            
            var container = new Container();
            container.setClip(true);
            stack.add(container);
            
            var spineImage = new SpineImage(skeletonRenderer, skeletonData, animationData);
            spineImage.setScaling(Scaling.fit);
            var skin = new Skin("skin");
            skin.addSkin(skinTemplate);
            spineImage.spineDrawable.getSkeleton().setSkin(skin);
            var animationState = spineImage.spineDrawable.getAnimationState();
            animationState.setAnimation(0, animationZoomed, false);
            animationState.setAnimation(1, animationMouthSmile, false);
            animationState.setAnimation(2, animationOverlayDecay, false);
            animationState.getCurrent(2).setTimeScale(0);
            spineImage.setTouchable(Touchable.disabled);
            container.setActor(spineImage);
            dialog.addListener(new InputListener() {
                float brushTotal;
                float brushGoal = 6000;
                Vector2 last = new Vector2();
                boolean hiding;
                
                @Override
                public boolean mouseMoved(InputEvent event, float x, float y) {
                    var distance = last.dst(x, y);
                    brushTotal += distance;
                    last.set(x, y);
                    
                    if (distance > 10) {
                        sfx_brush.play(sfx);
                        var particleEffect = new ParticleEffect(particleBurst);
                        particleEffect.reset(false);
                        var particleImage = new ParticleImage(particleEffect);
                        dialog.addActor(particleImage);
                        particleImage.setTouchable(Touchable.disabled);
                        particleImage.pack();
                        particleImage.setSize(200, 200);
                        temp.set(mouseX, mouseY);
                        dialog.stageToLocalCoordinates(temp);
                        particleImage.setPosition(temp.x, temp.y, Align.center);
                        particleImage.particleDrawable.particleEffect.setPosition(temp.x, temp.y);
                    }
                    
                    animationState.getCurrent(2).setTrackTime(brushTotal / brushGoal);
                    
                    if (!hiding && brushTotal > brushGoal) {
                        sfx_hmm.play(sfx);
                        addHeart();
                        hiding = true;
                        dialog.hide();
                        Gdx.input.setInputProcessor(inputMultiplexer);
                        EntityCharacter.this.updateNeed();
                        EntityCharacter.this.updateTableIndicator();
                        zebra.stopOperation();
                        
                        var particleEntity = new ParticleEntity(particleCheck);
                        entityController.add(particleEntity);
                        particleEntity.setPosition(EntityCharacter.this.x, EntityCharacter.this.y);
                        
                        mouseImage.remove();
                        Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
                        Gdx.graphics.setCursor(cursorArrow);
                        reward += REWARD;
                    }
                    return true;
                }
            });
            
            container = new Container();
            container.top();
            stack.add(container);
            
            var label = new Label("BRUSH!", lDefault);
            container.setActor(label);
            
            dialog.show(stage);
            Gdx.input.setInputProcessor(stage);
            
            stage.addActor(mouseImage);
        } else if (chair.need == Need.BLUE) {
            if (grabbedCharacter != null) grabbedCharacter.setDown(grabbedCharacter.chair, grabbedCharacter.chair.graphNode);
            Gdx.graphics.setSystemCursor(SystemCursor.None);
            
            var progressBar = new ProgressBar(0, 1000, 1, true, pDefaultVertical);
            var mouseImage = new SpineImage(skeletonRenderer, SpineHammer.skeletonData, SpineHammer.animationData) {
                @Override
                public void act(float delta) {
                    super.act(delta);
                    setPosition(mouseX, mouseY, Align.center);
                    toFront();
                    smashTotal -= smashDegrade;
                    if (smashTotal < 0) smashTotal = 0;
                    progressBar.setValue(smashTotal);
                }
            };
            mouseImage.setScaling(Scaling.none);
            mouseImage.setTouchable(Touchable.disabled);
            mouseImage.setCrop(-2,-2, 4, 4);
            mouseImage.pack();
            
            var dialog = new Dialog("", wDefault);
            
            var stack = new Stack();
            dialog.getContentTable().add(stack).size(400);
            
            var container = new Container();
            container.setClip(true);
            stack.add(container);
            
            var spineImage = new SpineImage(skeletonRenderer, skeletonData, animationData);
            spineImage.setScaling(Scaling.fit);
            var skin = new Skin("skin");
            skin.addSkin(skinTemplate);
            spineImage.spineDrawable.getSkeleton().setSkin(skin);
            spineImage.setTouchable(Touchable.enabled);
            var animationState = spineImage.spineDrawable.getAnimationState();
            animationState.setAnimation(0, animationZoomed, false);
            animationState.setAnimation(1, animationMouthSmile, false);
            container.setActor(spineImage);
            dialog.getContentTable().setTouchable(Touchable.enabled);
            dialog.getContentTable().addListener(new ClickListener() {
                float smashGoal = 1000;
                boolean hiding;
                
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    sfx_hammer.play(sfx * .5f);
                    smashTotal += 100;
                    progressBar.setValue(smashTotal);
                    mouseImage.spineDrawable.getAnimationState().setAnimation(0, SpineHammer.animationHam, false);
                    
                    var particleEffect = new ParticleEffect(particleStar);
                    particleEffect.reset(false);
                    var particleImage = new ParticleImage(particleEffect);
                    dialog.addActor(particleImage);
                    particleImage.setTouchable(Touchable.disabled);
                    particleImage.pack();
                    particleImage.setSize(200, 200);
                    temp.set(mouseX, mouseY);
                    dialog.stageToLocalCoordinates(temp);
                    particleImage.setPosition(temp.x, temp.y, Align.center);
                    particleImage.particleDrawable.particleEffect.setPosition(temp.x, temp.y);
                    
                    if (!hiding && smashTotal > smashGoal) {
                        addHeart();
                        hiding = true;
                        dialog.hide();
                        Gdx.input.setInputProcessor(inputMultiplexer);
                        EntityCharacter.this.updateNeed();
                        EntityCharacter.this.updateTableIndicator();
                        zebra.stopOperation();
                        
                        var particleEntity = new ParticleEntity(particleCheck);
                        entityController.add(particleEntity);
                        particleEntity.setPosition(EntityCharacter.this.x, EntityCharacter.this.y);
                        mouseImage.remove();
                        Gdx.graphics.setSystemCursor(SystemCursor.Arrow);
                        Gdx.graphics.setCursor(cursorArrow);
                        animationState.setAnimation(2, animationOverlayBlack, false);
                        reward += REWARD;
                    }
                }
            });
            
            container = new Container();
            container.top();
            stack.add(container);
            
            var label = new Label("CLICK TO EXTRACT THE TOOTH!", lDefault);
            container.setActor(label);
            
            dialog.getContentTable().add(progressBar).height(309);
            
            dialog.show(stage);
            Gdx.input.setInputProcessor(stage);
            
            stage.addActor(mouseImage);
        } else if (chair.need == Need.ORANGE) {
            if (grabbedCharacter != null) grabbedCharacter.setDown(grabbedCharacter.chair, grabbedCharacter.chair.graphNode);
            var dialog = new Dialog("", wDefault);
            
            var braces = new Array<>(new Skin[]{skinBraces1, skinBraces2, skinBraces3, skinBraces4, skinBraces5,
                    skinBraces6, skinBraces7, skinBraces8, skinBraces9, skinBraces10, skinBraces11, skinBraces12,
                    skinBraces13, skinBraces14, skinBraces15, skinBraces16});
            bracesIndex = MathUtils.random(braces.size - 1);
            var targetIndex = MathUtils.random(braces.size - 1);
            
            var button = new Button(bChangeLeft);
            dialog.getContentTable().add(button);
            
            
            var stack = new Stack();
            dialog.getContentTable().add(stack).size(400);
            
            var container = new Container();
            container.setClip(true);
            stack.add(container);
            
            var spineImage = new SpineImage(skeletonRenderer, skeletonData, animationData);
            spineImage.setScaling(Scaling.fit);
            bracesSkin = new Skin("skin");
            bracesSkin.addSkin(skinTemplate);
            bracesSkin.addSkin(braces.get(bracesIndex));
            spineImage.spineDrawable.getSkeleton().setSkin(bracesSkin);
            var animationState = spineImage.spineDrawable.getAnimationState();
            animationState.setAnimation(0, animationZoomedBraces, false);
            animationState.setAnimation(1, animationMouthSmile, false);
            animationState.setAnimation(2, bracesIndex == targetIndex ? animationThumbsUp : animationThumbsDown, false);
            container.setActor(spineImage);
            
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    sfx_braces.play(sfx);
                    bracesIndex--;
                    if (bracesIndex < 0) bracesIndex = braces.size - 1;
                    bracesSkin = new Skin("skin");
                    bracesSkin.addSkin(skinTemplate);
                    bracesSkin.addSkin(braces.get(bracesIndex));
                    spineImage.spineDrawable.getSkeleton().setSkin(bracesSkin);
                    animationState.setAnimation(2, bracesIndex == targetIndex ? animationThumbsUp : animationThumbsDown, false);
                }
            });
            
            container = new Container();
            container.top();
            stack.add(container);
            
            var label = new Label("CHOOSE THE RIGHT BRACES", lDefault);
            container.setActor(label);

            button = new Button(bChangeRight);
            dialog.getContentTable().add(button);
            button.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    sfx_braces.play(sfx);
                    bracesIndex++;
                    if (bracesIndex >= braces.size) bracesIndex = 0;
                    bracesSkin = new Skin("skin");
                    bracesSkin.addSkin(skinTemplate);
                    bracesSkin.addSkin(braces.get(bracesIndex));
                    spineImage.spineDrawable.getSkeleton().setSkin(bracesSkin);
                    animationState.setAnimation(2, bracesIndex == targetIndex ? animationThumbsUp : animationThumbsDown, false);
                }
            });

            dialog.getContentTable().row();
            var textButton = new TextButton("DONE", tbDefault);
            dialog.getContentTable().add(textButton).colspan(3);
            textButton.addListener(sndChangeListener);
            textButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    dialog.hide();
                    Gdx.input.setInputProcessor(inputMultiplexer);
                    EntityCharacter.this.updateNeed();
                    EntityCharacter.this.updateTableIndicator();
                    zebra.stopOperation();
                    
                    if (bracesIndex != targetIndex) subtractHeart();
                    else addHeart();
                    var particleEntity = new ParticleEntity(bracesIndex == targetIndex ? particleCheck : particleAngry);
                    entityController.add(particleEntity);
                    particleEntity.setPosition(EntityCharacter.this.x, EntityCharacter.this.y);
                    reward += REWARD;
                }
            });
            
            dialog.show(stage);
            Gdx.input.setInputProcessor(stage);
        }
    }
    
    public void subtractHeart() {
        sfx_grumpy.play(sfx);
        hearts--;
        if (hearts <= 0) {
            hearts = 0;
            mode = LEAVE;
            calculateNewPath(exitNode);
            if (chair != null) chair.character = null;
        }
        updateTableHearts();
    }
    
    public void addHeart() {
        heartTick = HEART_TICK_DELAY;
        hearts++;
        if (hearts > HEARTS_MAX) hearts = HEARTS_MAX;
        updateTableHearts();
    }
}
