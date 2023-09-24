package com.ray3k.template.entities;

import com.ray3k.template.*;

public class EntityWalls extends DecalEntity {
    public EntityWalls() {
        super(Resources.textures_textures.createSprite("game/walls"), 512, 288);
        sprite.setScale(.5f);
    }
}