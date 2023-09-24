package com.ray3k.template.entities;

import com.ray3k.template.*;

public class EntityFloor extends DecalEntity {
    public EntityFloor() {
        super(Resources.textures_textures.createSprite("game/floor"), 512, 288);
        sprite.setScale(.5f);
    }
}
