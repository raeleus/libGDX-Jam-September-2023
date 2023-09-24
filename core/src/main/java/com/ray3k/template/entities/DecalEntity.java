package com.ray3k.template.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ray3k.template.*;

import static com.ray3k.template.Core.*;

public class DecalEntity extends Entity {
    public Sprite sprite;
    public boolean panning = true;
    
    public DecalEntity(Sprite sprite, int centerX, int centerY) {
        this.sprite = sprite;
        x = centerX - sprite.getWidth() / 2f;
        y = centerY - sprite.getHeight() / 2f;
    }
    
    @Override
    public void create() {
    
    }
    
    @Override
    public void actBefore(float delta) {
    
    }
    
    @Override
    public void act(float delta) {
    
    }
    
    @Override
    public void draw(float delta) {
        if (panning) {
            sprite.setPosition(x, y);
            sprite.draw(batch);
        } else {
            var camera = Core.camera;
            var viewport = Core.viewport;
            sprite.setPosition(camera.position.x - viewport.getWorldWidth() / 2 + x * camera.zoom, camera.position.y - viewport.getWorldHeight() / 2 + y * camera.zoom);
            sprite.setScale(camera.zoom);
            sprite.draw(batch);
        }
    }
    
    @Override
    public void destroy() {
    
    }
}
