package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.crashinvaders.vfx.effects.ChainVfxEffect;
import com.crashinvaders.vfx.effects.WaterDistortionEffect;
import com.esotericsoftware.spine.Skin;
import com.ray3k.template.*;
import com.ray3k.template.OgmoReader.*;
import com.ray3k.template.entities.*;
import com.ray3k.template.entities.EntityCharacter.*;
import space.earlygrey.shapedrawer.ShapeDrawer;
import space.earlygrey.simplegraphs.UndirectedGraph;
import text.formic.Stringf;

import java.util.logging.Level;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;
import static com.ray3k.template.Resources.SkinSkinStyles.*;
import static com.ray3k.template.screens.GameScreen.Need.*;

public class GameScreen extends JamScreen {
    public static GameScreen gameScreen;
    public static final Color BG_COLOR = new Color();
    public static Stage stage;
    private static WaterDistortionEffect vfxEffect;
    public static Array<GraphNode> graphNodes;
    public static Array<GraphNodeLine> graphNodeLines;
    public static UndirectedGraph<GraphNode> graph;
    public static GraphNodeSpawn spawnNode;
    public static GraphNodeRally rallyNode;
    public static GraphNodeExit exitNode;
    public static EntityZebra zebra;
    public static EntityGumballMachine gumballMachine;
    public static Array<EntityChairWaiting>waitingChairs;
    public static EntityCharacter grabbedCharacter;
    private static Cursor cursor;
    public static Cursor newCursor;
    public static Label moneyLabel;
    public static Label goalLabel;
    public static Label timeLabel;
    public static float time;
    public static int money;
    public static int goal;
    public static String levelText;
    public static Array<Need> availableNeeds;
    public static InputMultiplexer inputMultiplexer;
    public static Array<GumballSpawn> gumballSpawns;
    public static EntityGumball holdingGumball;
    private float customerTimer;
    private float customerDelay;
    public static Array<EntityCharacter> characters;
    public static EntityNight night;
    public static boolean nightPhase;
    public static boolean finale;
    public static int level;
    public static Array<Sound> thanksSounds;
    
    public enum Need {
        ORANGE, BLUE, WHITE, YELLOW
    }
    
    @Override
    public void show() {
        super.show();
        thanksSounds = new Array<>(new Sound[] {sfx_thanksZebraFemale01, sfx_thanksZebraFemale02,
                sfx_thanksZebraFemale03, sfx_thanksZebraFemale04, sfx_thanksZebraFemale05, sfx_thanksZebraFemale06,
                sfx_thanksZebraMale01, sfx_thanksZebraMale02, sfx_thanksZebraMale03, sfx_thanksZebraMale04,
                sfx_thanksZebraMale05, sfx_thanksZebraMale06, sfx_thanksZebraWacky01, sfx_thanksZebraWacky02,
                sfx_thanksZebraWacky03, sfx_thanksZebraWacky04, sfx_thanksZebraWacky05, sfx_thanksZebraWacky06,
                sfx_thanksZebraWacky07, sfx_thanksZebraWacky08, sfx_thanksZebraWacky09, sfx_thanksZebraWacky10});
        
        bgm_game.setLooping(true);
        bgm_game.setVolume(bgm);
        bgm_game.play();
        
        holdingGumball = null;
        grabbedCharacter = null;
        time = 60 * 3;
        nightPhase = false;
        finale = false;
        
        customerTimer = 2.5f;
        
        availableNeeds = new Array<>();
    
        gameScreen = this;
        vfxEffect = new WaterDistortionEffect(0, 0);

        vfxManager.addEffect(vfxEffect);
        BG_COLOR.set(Color.WHITE);
    
        stage = new Stage(new FitViewport(1024, 576), batch);
        
        var root = new Table();
        root.setFillParent(true);
        root.align(Align.topLeft);
        root.pad(10);
        stage.addActor(root);
        
        moneyLabel = new Label("$100", lMoney);
        root.add(moneyLabel);
        
        goalLabel = new Label("Goal: $500", lDefault);
        root.add(goalLabel).padLeft(50);
        
        timeLabel = new Label("", lDefault);
        root.add(timeLabel).expandX().right();
    
        shapeDrawer = new ShapeDrawer(batch, textures_textures.findRegion("game/white"));
        shapeDrawer.setPixelSize(.5f);
    
        inputMultiplexer = new InputMultiplexer(stage, this);
        Gdx.input.setInputProcessor(inputMultiplexer);
    
        camera = new OrthographicCamera();
        viewport = new FitViewport(1024, 576, camera);
        camera.position.set(512, 288, 0);
    
        entityController.clear();
        
        graphNodes = new Array<>();
        graphNodeLines = new Array<>();
        waitingChairs = new Array<>();
        gumballSpawns = new Array<>();
        characters = new Array<>();
        graph = new UndirectedGraph<>();
        loadLevel();
    }
    
    @Override
    public void act(float delta) {
        moneyLabel.setText("$" + money);
        goalLabel.setText("Goal: $" + goal);
        
        time -= delta;
        if (time > 0) {
            var timeRounded = MathUtils.round(time);
            var minutes = timeRounded / 60;
            var seconds = timeRounded % 60;
            timeLabel.setText(minutes + ":" + Stringf.format("%02d", seconds));
        } else {
            timeLabel.setText("NIGHT PHASE");
            if (!nightPhase) {
                nightPhase = true;
                night.showNight();
            }
            if (!finale && characters.size == 0) {
                finale = true;
                if (money >= goal) showBeerDialog();
                else showQuotaDialog();
                
                stage.addAction(Actions.sequence(new TemporalAction(1f) {
                    @Override
                    protected void update(float percent) {
                        bgm_game.setVolume((1 - percent) * bgm);
                    }
                }, Actions.run(() -> bgm_game.stop())));
            }
        }
        
        customerTimer -= delta;
        if (time > 0 && customerTimer < 0) {
            customerTimer = customerDelay;
            if (getFreeWaitingChair() != null) spawnCustomer();
        }
        
        newCursor = cursorArrow;
        entityController.act(delta);
        vfxManager.update(delta);
        stage.act(delta);
        if (newCursor != cursor) {
            cursor = newCursor;
            Gdx.graphics.setCursor(newCursor);
        }
    }
    
    @Override
    public void draw(float delta) {
        batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
        batch.setColor(Color.WHITE);
        vfxManager.cleanUpBuffers();
        vfxManager.beginInputCapture();
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        viewport.apply();
        batch.setProjectionMatrix(camera.combined);
        entityController.draw(delta);
        batch.end();
        
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
        
        vfxManager.endInputCapture();
        vfxManager.applyEffects();
        vfxManager.renderToScreen();
    }
    
    @Override
    public void resize(int width, int height) {
        if (width + height != 0) {
            vfxManager.resize(width, height);
            viewport.update(width, height);
        
            stage.getViewport().update(width, height, true);
        }
    }
    
    @Override
    public void dispose() {
        vfxEffect.dispose();
    }
    
    @Override
    public void hide() {
        super.hide();
        vfxManager.removeAllEffects();
        vfxEffect.dispose();
        entityController.dispose();
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    private void loadLevel() {
        var reader = new OgmoReader();
        
        var registers = new Array<EntityRegister>();
        var gumballMachines = new Array<EntityGumballMachine>();
        var chairs = new Array<EntityChairWaiting>();
        
        reader.addListener(new OgmoAdapter() {
            @Override
            public void level(String ogmoVersion, int width, int height, int offsetX, int offsetY,
                              ObjectMap<String, OgmoValue> valuesMap) {
                goal = valuesMap.get("goal").asInt();
                time = valuesMap.get("time").asInt();
                levelText = valuesMap.get("text").asString();
                customerDelay = valuesMap.get("customer-delay").asFloat();
                
                var needsString = valuesMap.get("needs").asString();
                if (needsString.contains("white")) availableNeeds.add(WHITE);
                if (needsString.contains("yellow")) availableNeeds.add(YELLOW);
                if (needsString.contains("blue")) availableNeeds.add(BLUE);
                if (needsString.contains("orange")) availableNeeds.add(ORANGE);
            }
            
            @Override
            public void decal(int x, int y, float originX, float originY, float scaleX, float scaleY, int rotation,
                              String texture, String folder, ObjectMap<String, OgmoValue> valuesMap) {
                var sprite = textures_textures.createSprite("game/" + Utils.filePathNoExtension(texture));
                sprite.setScale(scaleX, scaleY);
                var decalEntity = new DecalEntity(sprite, x, y);
                entityController.add(decalEntity);
                var depth = valuesMap.get("depth").asInt();
                if (depth == 0) decalEntity.depth = DEPTH_ENTITIES + y;
                else decalEntity.depth = DEPTH_ENTITIES + depth;
            }
            
            @Override
            public void entity(String name, int id, int x, int y, int width, int height, boolean flippedX,
                               boolean flippedY, int originX, int originY, int rotation, Array<EntityNode> nodes,
                               ObjectMap<String, OgmoValue> valuesMap) {
                Entity entity = null;
                
                switch (name) {
                    case "chair-waiting":
                        entity = new EntityChairWaiting();
                        entity.teleport(x, y);
                        chairs.add((EntityChairWaiting) entity);
                        waitingChairs.add((EntityChairWaiting) entity);
                        break;
                    case "register":
                        entity = new EntityRegister();
                        entity.teleport(x, y);
                        registers.add((EntityRegister) entity);
                        break;
                    case "trash":
                        entity = new EntityTrash();
                        entity.teleport(x, y);
                        break;
                    case "gumball-machine":
                        entity = new EntityGumballMachine();
                        entity.teleport(x, y);
                        gumballMachines.add((EntityGumballMachine) entity);
                        gumballMachine = (EntityGumballMachine) entity;
                        break;
                    case "chair-blue":
                        entity = new EntityChairBlue();
                        entity.teleport(x, y);
                        chairs.add((EntityChairWaiting) entity);
                        break;
                    case "chair-orange":
                        entity = new EntityChairOrange();
                        entity.teleport(x, y);
                        chairs.add((EntityChairWaiting) entity);
                        break;
                    case "chair-white":
                        entity = new EntityChairWhite();
                        entity.teleport(x, y);
                        chairs.add((EntityChairWaiting) entity);
                        break;
                    case "chair-yellow":
                        entity = new EntityChairYellow();
                        entity.teleport(x, y);
                        chairs.add((EntityChairWaiting) entity);
                        break;
                    case "walk-node":
                        var walkNode = new GraphNode(x, y);
                        walkNode.connectionsTemp = nodes;
                        graphNodes.add(walkNode);
                        graph.addVertex(walkNode);
                        break;
                    case "spawn-node":
                        spawnNode = new GraphNodeSpawn(x, y);
                        spawnNode.connectionsTemp = nodes;
                        graphNodes.add(spawnNode);
                        
                        graph.addVertex(spawnNode);
                        break;
                    case "line-node":
                        var lineNode = new GraphNodeLine(x, y);
                        lineNode.connectionsTemp = nodes;
                        graphNodes.add(lineNode);
                        graphNodeLines.add(lineNode);
                        graph.addVertex(lineNode);
                        break;
                    case "chair-node":
                        var chairNode = new GraphNodeChair(x, y);
                        chairNode.connectionsTemp = nodes;
                        graphNodes.add(chairNode);
                        graph.addVertex(chairNode);
                        break;
                    case "exit-node":
                        exitNode = new GraphNodeExit(x, y);
                        exitNode.connectionsTemp = nodes;
                        graphNodes.add(exitNode);
                        graph.addVertex(exitNode);
                        break;
                    case "register-node":
                        var registerNode = new GraphNodeRegister(x, y);
                        registerNode.connectionsTemp = nodes;
                        graphNodes.add(registerNode);
                        graph.addVertex(registerNode);
                        break;
                    case "gumball-node":
                        var gumballNode = new GraphNodeGumBall(x, y);
                        gumballNode.connectionsTemp = nodes;
                        graphNodes.add(gumballNode);
                        graph.addVertex(gumballNode);
                        break;
                    case "rally-node":
                        rallyNode = new GraphNodeRally(x, y);
                        rallyNode.connectionsTemp = nodes;
                        graphNodes.add(rallyNode);
                        graph.addVertex(rallyNode);
                        break;
                    case "gumball-nodes":
                        var gumballSpawn = new GumballSpawn();
                        entityController.add(gumballSpawn);
                        gumballSpawn.setPosition(x, y);
                        gumballSpawns.add(gumballSpawn);
                        break;
                    case "night":
                        night = new EntityNight();
                        entityController.add(night);
                        night.setPosition(x, y);
                        night.depth = 5480;
                        break;
                }
                
                if (entity != null) {
                    entity.depth = DEPTH_ENTITIES + y;
                    entityController.add(entity);
                }
            }
        });
        reader.readFile(Gdx.files.internal("levels/level" + level + ".json"));
        
        stage.addAction(delay(.5f, run(() -> {
            zebra = new EntityZebra();
            zebra.setPosition(spawnNode.coord.x, spawnNode.coord.y);
            zebra.depth = DEPTH_ENTITIES + spawnNode.coord.y;
            zebra.currentPoint = spawnNode;
            entityController.add(zebra);
            
            var label = new Label(levelText, lTitle);
            label.pack();
            label.setAlignment(Align.center);
            
            var container = new Container<>(label);
            container.setPosition(512, 288, Align.center);
            container.setTransform(true);
            container.setOrigin(Align.center);
            stage.addActor(container);
            container.addAction(sequence(parallel(scaleTo(1.5f, 1.5f, 2f), fadeOut(2f)), removeActor()));
        })));
        
        for (var node : graphNodes) {
            for (var connectionTemp : node.connectionsTemp) {
                for (int i = 0; i < graphNodes.size; i++) {
                    var compareNode = graphNodes.get(i);
                    if (MathUtils.isEqual(compareNode.coord.x, connectionTemp.x) && MathUtils.isEqual(compareNode.coord.y, connectionTemp.y)) {
                        graph.addEdge(node, compareNode);
                        break;
                    }
                }
            }
        }
        
        for (var register : registers) {
            register.graphNode = findNearestNode(GraphNodeRegister.class, register.x, register.y);
        }
        
        for (var gumballMachine : gumballMachines) {
            gumballMachine.graphNode = findNearestNode(GraphNodeGumBall.class, gumballMachine.x, gumballMachine.y);
        }
        
        for (var chair : chairs) {
            chair.graphNode = findNearestNode(GraphNodeChair.class, chair.x, chair.y);
            ((GraphNodeChair)chair.graphNode).chair = chair;
        }
    }
    
    public static GraphNode findNearestNode(float x, float y) {
        return findNearestNode(null, x, y);
    }
    
    public static GraphNode findNearestNode(Class clazz, float x, float y) {
        float nearestDistance = Float.MAX_VALUE;
        GraphNode nearest = null;
        
        for (int i = 0; i < graphNodes.size; i++) {
            var compareNode = graphNodes.get(i);
            if (clazz != null && compareNode.getClass() != clazz) continue;
            
            var distance = Utils.pointDistance(compareNode.coord.x, compareNode.coord.y, x, y);
            if (distance < nearestDistance) {
                nearest = compareNode;
                nearestDistance = distance;
            }
        }
        
        return nearest;
    }
    
    public static EntityChairWaiting getFreeWaitingChair() {
        for (var chair : waitingChairs) {
            if (chair.character == null) return chair;
        }
        return null;
    }
    
    public static GraphNodeLine getFreeLineNode() {
        for (var node : graphNodeLines) {
            if (node.character == null) return node;
        }
        return null;
    }
    
    public static void clearCustomerInLine() {
        var removed = false;
        if (graphNodeLines.size > 0 && graphNodeLines.first().character != null) {
            sfx_register.play(sfx);
            removed = true;
            var node = graphNodeLines.first();
            money += MathUtils.floor(node.character.reward * node.character.hearts / EntityCharacter.HEARTS_MAX);
            node.character.mode = Mode.LEAVE;
            node.character.calculateNewPath(exitNode);
            node.character.updateTableHearts();
            node.character = null;
            
            var particleEntity = new ParticleEntity(particleMoney);
            entityController.add(particleEntity);
            particleEntity.setPosition(node.coord.x, node.coord.y);
        }
        
        for (int i = 1; i < graphNodeLines.size; i++) {
            var node = graphNodeLines.get(i);
            
            if (node.character != null) {
                var previousNode = graphNodeLines.get(i - 1);
                node.character.calculateNewPath(previousNode);
                node.character = null;
            } else return;
        }
    }
    
    public static void makeGumballs() {
        sfx_gumball.play(sfx);
        for (var spawn : gumballSpawns) {
            spawn.spawn();
        }
    }
    
    public static void spawnCustomer() {
        sfx_doorbell.play(sfx);
        var character = new EntityCharacter();
        character.setPosition(spawnNode.coord.x, spawnNode.coord.y);
        character.depth = DEPTH_ENTITIES + spawnNode.coord.y;
        character.currentPoint = spawnNode;
        entityController.add(character);
    }
    
    public static void spawnNightCustomer() {
        sfx_doorbell.play(sfx);
        var character = new EntityCharacter();
        character.killMe = true;
        character.setPosition(spawnNode.coord.x, spawnNode.coord.y);
        character.depth = DEPTH_ENTITIES + spawnNode.coord.y;
        character.currentPoint = spawnNode;
        entityController.add(character);
    }
    
    public static void spawnCop() {
        sfx_doorbell.play(sfx);
        var character = new EntityCharacter();
        character.killMe = true;
        character.setPosition(spawnNode.coord.x, spawnNode.coord.y);
        character.depth = DEPTH_ENTITIES + spawnNode.coord.y;
        character.currentPoint = spawnNode;
        entityController.add(character);
        var newSkin = new Skin("fucker");
        newSkin.addSkin(character.skeleton.getSkin());
        newSkin.addSkin(SpineCharacter.skinHairCop);
        character.skeleton.setSkin(newSkin);
    }
    
    public static void showQuotaDialog() {
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        table.pad(50);
        var label = new Label("You failed!\nYou made $" + MathUtils.round(money) + "\nbut you needed to make $" + MathUtils.round(goal), lDefault);
        table.add(label);
        
        table.row();
        var textButton = new TextButton("Try Again", tbDefault);
        table.add(textButton);
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Core.core.transition(new GameScreen());
            }
        });
        dialog.show(stage);
    }
    
    public static void showBeerDialog() {
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        var label = new Label("Good job!\n You made $" + MathUtils.round(money), lDefault);
        table.add(label);
        
        table.row();
        var image = new Image(skin, "beer");
        image.setScaling(Scaling.fit);
        table.add(image).size(400);
        
        table.row();
        var textButton = new TextButton("Enjoy some \"apple juice\"", tbDefault);
        table.add(textButton);
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                vfxEffect.setSpeed(.5f);
                vfxEffect.setAmount(2f);
                dialog.hide();
                
                bgm_horror.setVolume(0);
                bgm_horror.setLooping(true);
                bgm_horror.play();
                stage.addAction(new TemporalAction(1f) {
                    @Override
                    protected void update(float percent) {
                        bgm_horror.setVolume((percent) * bgm);
                    }
                });
                
                stage.addAction(Actions.delay(5f, Actions.run(() -> {
                    if (level == 1) showGoHomeDialog();
                    else if (level == 2) showOhTheresACustomer();
                    else if (level == 3) showFuckItsACop();
                    else if (level == 4) showICantStop();
                })));
            }
        });
        dialog.show(stage);
    }
    
    public static void showGoHomeDialog() {
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        table.pad(50);
        var label = new Label("Okay, that's enough\nTime to go home...", lDefault);
        table.add(label);
        
        table.row();
        var textButton = new TextButton("Go Home", tbDefault);
        textButton.addListener(sndChangeListener);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.hide();
                zebra.calculateNewPath(exitNode);
                
                stage.addAction(Actions.delay(3f, Actions.run(() -> {
                    level++;
                    if (level <= 4) {
                        Core.core.transition(new GameScreen());
                    } else {
                        sfx_carCrash.play(sfx);
                        core.transition(new CreditsScreen());
                    }
                    bgm_horror.stop();
                })));
            }
        });
        dialog.show(stage);
    }
    
    public static void showOhTheresACustomer() {
        spawnNightCustomer();
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        table.pad(50);
        var label = new Label("Oh shit, there's another customer.", lDefault);
        table.add(label);
        
        table.row();
        var textButton = new TextButton("Gawdammit", tbDefault);
        textButton.addListener(sndChangeListener);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.hide();
            }
        });
        dialog.show(stage);
    }
    
    public static void showOhShit() {
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        table.pad(50);
        var label = new Label("Oh fuck fuck fuck.", lDefault);
        table.add(label);
        
        table.row();
        var textButton = new TextButton("Oh shit shit shit!", tbDefault);
        textButton.addListener(sndChangeListener);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.hide();
            }
        });
        dialog.show(stage);
    }
    
    public static void showFuckItsACop() {
        spawnCop();
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        table.pad(50);
        var label = new Label("Fuck! It's a cop!\nDoes he know?", lDefault);
        table.add(label);
        
        table.row();
        var textButton = new TextButton("Play it cool...", tbDefault);
        textButton.addListener(sndChangeListener);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                dialog.hide();
            }
        });
        dialog.show(stage);
    }
    
    public static void showICantStop() {
        
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        var label = new Label("I can't stop murdering people.\nOh gawd, someone help me.", lDefault);
        table.add(label);
        
        table.row();
        var image = new Image(skin, "beer");
        image.setScaling(Scaling.fit);
        table.add(image).size(400);
        
        table.row();
        var textButton = new TextButton("Drink the pain away...", tbDefault);
        table.add(textButton);
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                vfxEffect.setSpeed(.5f);
                vfxEffect.setAmount(4f);
                dialog.hide();
                
                stage.addAction(Actions.delay(5f, Actions.run(() -> {
                    showICantStop2();
                })));
            }
        });
        dialog.show(stage);
    }
    
    public static void showICantStop2() {
        
        var dialog = new Dialog("", wDefault);
        var table = dialog.getContentTable();
        
        var label = new Label("Ofgh iiI floubhout iiiI hhhaabbb taa grrribe hooom.", lDefault);
        table.add(label);
        
        table.row();
        var image = new Image(skin, "beer");
        image.setScaling(Scaling.fit);
        table.add(image).size(400);
        
        table.row();
        var textButton = new TextButton("Waaaaaahttssss daaa wurrst ah uh habben?", tbDefault);
        table.add(textButton);
        textButton.addListener(sndChangeListener);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                vfxEffect.setSpeed(.5f);
                vfxEffect.setAmount(16f);
                dialog.hide();
                
                stage.addAction(Actions.delay(5f, Actions.run(() -> {
                    showGoHomeDialog();
                })));
            }
        });
        dialog.show(stage);
    }
}