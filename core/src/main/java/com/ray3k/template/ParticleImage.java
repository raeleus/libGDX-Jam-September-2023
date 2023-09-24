package com.ray3k.template;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ParticleImage extends Image {
    public ParticleDrawable particleDrawable;
    
    public ParticleImage(ParticleEffect particleEffect) {
        this.particleDrawable = new ParticleDrawable(particleEffect);
        setDrawable(particleDrawable);
    }
    
    @Override
    public void act(float delta) {
        super.act(delta);
        particleDrawable.update(delta);
    }
}
