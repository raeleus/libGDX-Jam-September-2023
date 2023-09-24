package com.ray3k.template.entities;

import com.ray3k.template.Resources.*;
import com.ray3k.template.screens.GameScreen.*;

import static com.ray3k.template.Core.*;

public class EntityChairBlue extends EntityChairWaiting {
    @Override
    public void create() {
        super.create();
        skeleton.setSkin(SpineChair.skinBlue);
        setCollisionBox(skeleton.findSlot("bbox"), skeletonBounds);
        need = Need.BLUE;
    }
}
