package com.ray3k.template;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;

public class ParticleDrawable extends BaseDrawable {
    public ParticleEffect particleEffect;
    public float offsetX;
    public float offsetY;
    
    public ParticleDrawable(ParticleEffect particleEffect) {
        this.particleEffect = particleEffect;
    }
    
    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        particleEffect.setPosition(x + offsetX + width / 2, y + offsetY + height / 2);
        particleEffect.draw(batch);
    }
    
    public void update(float delta) {
        particleEffect.update(delta);
    }
    
    public float getOffsetX() {
        return offsetX;
    }
    
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }
    
    public float getOffsetY() {
        return offsetY;
    }
    
    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }
    
    public void setOffset(float offsetX, float offsetY) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }
    
    public void setOffset(float offset) {
        this.offsetX = offset;
        this.offsetY = offset;
    }
}