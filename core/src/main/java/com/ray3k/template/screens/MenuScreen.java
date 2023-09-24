package com.ray3k.template.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;
import static com.ray3k.template.Resources.*;

public class MenuScreen extends JamScreen {
    private Stage stage;
    private final static Color BG_COLOR = new Color(Color.BLACK);
    
    @Override
    public void show() {
        super.show();
        
        Gdx.graphics.setCursor(cursorArrow);
    
        final Music bgm = bgm_menu;
        if (!bgm.isPlaying()) {
            bgm.play();
            bgm.setVolume(core.bgm);
            bgm.setLooping(true);
        }
        
        stage = new Stage(new ExtendViewport(1024, 576), batch);
        Gdx.input.setInputProcessor(stage);
        
        var root = new Table();
        root.setFillParent(true);
        root.setBackground(skin.getDrawable("background-tile-10"));
        stage.addActor(root);
        
        var image = new Image(skin, "logo");
        image.setScaling(Scaling.fit);
        root.add(image).colspan(2).padLeft(50).padRight(50).padTop(20);
        
        root.row().padBottom(50);
        var table = new Table();
        root.add(table).padLeft(200);
        
        table.defaults().space(10);
        var textButton = new TextButton("Play", skin);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                bgm.stop();
                Gdx.input.setInputProcessor(null);
                GameScreen.level = 1;
                core.transition(new GameScreen());
            }
        });
        textButton.addListener(sndChangeListener);
        
        table.row();
        textButton = new TextButton("Options", skin);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                core.transition(new OptionsScreen());
            }
        });
        textButton.addListener(sndChangeListener);
        
        table.row();
        textButton = new TextButton("Credits", skin);
        table.add(textButton);
        textButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.input.setInputProcessor(null);
                core.transition(new CreditsScreen());
            }
        });
        textButton.addListener(sndChangeListener);
        
        var stack = new Stack();
        root.add(stack).expandX().left().size(200).space(50);
        
        var particleImage = new ParticleImage(particleTooth);
        stack.add(particleImage);
        
        var spineImage = new SpineImage(skeletonRenderer, SpineZebra.skeletonData, SpineZebra.animationData);
        spineImage.setCrop(-50, -50, 100, 100);
        spineImage.spineDrawable.getAnimationState().setAnimation(0, SpineZebra.animationCelebrate, true);
        spineImage.setScaling(Scaling.fit);
        stack.add(spineImage);
    }
    
    @Override
    public void act(float delta) {
        stage.act(delta);
    }
    
    @Override
    public void draw(float delta) {
        Gdx.gl.glClearColor(BG_COLOR.r, BG_COLOR.g, BG_COLOR.b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
        batch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        stage.draw();
    }
    
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    
    @Override
    public void pause() {
    
    }
    
    @Override
    public void resume() {
    
    }
    
    @Override
    public void dispose() {
    
    }
}
