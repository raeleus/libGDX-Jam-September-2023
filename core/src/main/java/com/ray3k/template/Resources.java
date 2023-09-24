package com.ray3k.template;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextTooltip;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.esotericsoftware.spine.Animation;
import com.esotericsoftware.spine.AnimationStateData;
import com.esotericsoftware.spine.BoneData;
import com.esotericsoftware.spine.EventData;
import com.esotericsoftware.spine.SkeletonData;
import com.esotericsoftware.spine.SlotData;
import java.lang.String;

public class Resources {
    public static TextureAtlas textures_textures;

    public static Skin skin_skin;

    public static Sound sfx_ahh;

    public static Sound sfx_blast;

    public static Sound sfx_blood;

    public static Sound sfx_braces;

    public static Sound sfx_brush;

    public static Sound sfx_carCrash;

    public static Sound sfx_click;

    public static Sound sfx_doorbell;

    public static Sound sfx_drill;

    public static Sound sfx_grumpy;

    public static Sound sfx_gumball;

    public static Sound sfx_hammer;

    public static Sound sfx_hmm;

    public static Sound sfx_libgdx;

    public static Sound sfx_mk3;

    public static Sound sfx_pleaseDontKillMe;

    public static Sound sfx_register;

    public static Sound sfx_rim;

    public static Sound sfx_shot;

    public static Sound sfx_slash;

    public static Sound sfx_spooky;

    public static Sound sfx_swoosh;

    public static Sound sfx_thanksZebraFemale01;

    public static Sound sfx_thanksZebraFemale02;

    public static Sound sfx_thanksZebraFemale03;

    public static Sound sfx_thanksZebraFemale04;

    public static Sound sfx_thanksZebraFemale05;

    public static Sound sfx_thanksZebraFemale06;

    public static Sound sfx_thanksZebraMale01;

    public static Sound sfx_thanksZebraMale02;

    public static Sound sfx_thanksZebraMale03;

    public static Sound sfx_thanksZebraMale04;

    public static Sound sfx_thanksZebraMale05;

    public static Sound sfx_thanksZebraMale06;

    public static Sound sfx_thanksZebraWacky01;

    public static Sound sfx_thanksZebraWacky02;

    public static Sound sfx_thanksZebraWacky03;

    public static Sound sfx_thanksZebraWacky04;

    public static Sound sfx_thanksZebraWacky05;

    public static Sound sfx_thanksZebraWacky06;

    public static Sound sfx_thanksZebraWacky07;

    public static Sound sfx_thanksZebraWacky08;

    public static Sound sfx_thanksZebraWacky09;

    public static Sound sfx_thanksZebraWacky10;

    public static Sound sfx_thud;

    public static Sound sfx_tv;

    public static Sound sfx_woosh;

    public static Sound sfx_zap;

    public static Sound sfx_zoom;

    public static Music bgm_audioSample;

    public static Music bgm_game;

    public static Music bgm_horror;

    public static Music bgm_menu;

    public static void loadResources(AssetManager assetManager) {
        textures_textures = assetManager.get("textures/textures.atlas");
        SpineChair.skeletonData = assetManager.get("spine/chair.json");
        SpineChair.animationData = assetManager.get("spine/chair.json-animation");
        SpineChair.animationAnimation = SpineChair.skeletonData.findAnimation("animation");
        SpineChair.animationHighlight = SpineChair.skeletonData.findAnimation("highlight");
        SpineChair.animationNoHighlight = SpineChair.skeletonData.findAnimation("no-highlight");
        SpineChair.boneRoot = SpineChair.skeletonData.findBone("root");
        SpineChair.slotGameChairWaiting = SpineChair.skeletonData.findSlot("game/chair-waiting");
        SpineChair.slotBbox = SpineChair.skeletonData.findSlot("bbox");
        SpineChair.skinDefault = SpineChair.skeletonData.findSkin("default");
        SpineChair.skinBlue = SpineChair.skeletonData.findSkin("blue");
        SpineChair.skinChair = SpineChair.skeletonData.findSkin("chair");
        SpineChair.skinOrange = SpineChair.skeletonData.findSkin("orange");
        SpineChair.skinWhite = SpineChair.skeletonData.findSkin("white");
        SpineChair.skinYellow = SpineChair.skeletonData.findSkin("yellow");
        SpineCharacter.skeletonData = assetManager.get("spine/character.json");
        SpineCharacter.animationData = assetManager.get("spine/character.json-animation");
        SpineCharacter.animationBleed = SpineCharacter.skeletonData.findAnimation("bleed");
        SpineCharacter.animationDead = SpineCharacter.skeletonData.findAnimation("dead");
        SpineCharacter.animationHighlight = SpineCharacter.skeletonData.findAnimation("highlight");
        SpineCharacter.animationMouthHappy = SpineCharacter.skeletonData.findAnimation("mouth-happy");
        SpineCharacter.animationMouthNeutral = SpineCharacter.skeletonData.findAnimation("mouth-neutral");
        SpineCharacter.animationMouthSad = SpineCharacter.skeletonData.findAnimation("mouth-sad");
        SpineCharacter.animationMouthSmile = SpineCharacter.skeletonData.findAnimation("mouth-smile");
        SpineCharacter.animationNoHighlight = SpineCharacter.skeletonData.findAnimation("no-highlight");
        SpineCharacter.animationOverlayBlack = SpineCharacter.skeletonData.findAnimation("overlay-black");
        SpineCharacter.animationOverlayDecay = SpineCharacter.skeletonData.findAnimation("overlay-decay");
        SpineCharacter.animationPickedup = SpineCharacter.skeletonData.findAnimation("pickedup");
        SpineCharacter.animationStand = SpineCharacter.skeletonData.findAnimation("stand");
        SpineCharacter.animationThumbsDown = SpineCharacter.skeletonData.findAnimation("thumbs-down");
        SpineCharacter.animationThumbsUp = SpineCharacter.skeletonData.findAnimation("thumbs-up");
        SpineCharacter.animationWalk = SpineCharacter.skeletonData.findAnimation("walk");
        SpineCharacter.animationXray = SpineCharacter.skeletonData.findAnimation("xray");
        SpineCharacter.animationZoomed = SpineCharacter.skeletonData.findAnimation("zoomed");
        SpineCharacter.animationZoomedBraces = SpineCharacter.skeletonData.findAnimation("zoomed-braces");
        SpineCharacter.boneRoot = SpineCharacter.skeletonData.findBone("root");
        SpineCharacter.boneBody = SpineCharacter.skeletonData.findBone("body");
        SpineCharacter.boneBody2 = SpineCharacter.skeletonData.findBone("body2");
        SpineCharacter.boneBody3 = SpineCharacter.skeletonData.findBone("body3");
        SpineCharacter.boneBody4 = SpineCharacter.skeletonData.findBone("body4");
        SpineCharacter.boneBody5 = SpineCharacter.skeletonData.findBone("body5");
        SpineCharacter.boneTargetRight = SpineCharacter.skeletonData.findBone("target-right");
        SpineCharacter.boneTargetLeft = SpineCharacter.skeletonData.findBone("target-left");
        SpineCharacter.boneBlood = SpineCharacter.skeletonData.findBone("blood");
        SpineCharacter.boneFace = SpineCharacter.skeletonData.findBone("face");
        SpineCharacter.boneIndicator = SpineCharacter.skeletonData.findBone("indicator");
        SpineCharacter.boneHearts = SpineCharacter.skeletonData.findBone("hearts");
        SpineCharacter.slotBody = SpineCharacter.skeletonData.findSlot("body");
        SpineCharacter.slotBlood = SpineCharacter.skeletonData.findSlot("blood");
        SpineCharacter.slotFace = SpineCharacter.skeletonData.findSlot("face");
        SpineCharacter.slotEyes = SpineCharacter.skeletonData.findSlot("eyes");
        SpineCharacter.slotHair = SpineCharacter.skeletonData.findSlot("hair");
        SpineCharacter.slotMouth = SpineCharacter.skeletonData.findSlot("mouth");
        SpineCharacter.slotHandLeft = SpineCharacter.skeletonData.findSlot("hand-left");
        SpineCharacter.slotHandRight = SpineCharacter.skeletonData.findSlot("hand-right");
        SpineCharacter.slotMouthOverlay = SpineCharacter.skeletonData.findSlot("mouth overlay");
        SpineCharacter.slotBbox = SpineCharacter.skeletonData.findSlot("bbox");
        SpineCharacter.slotClip = SpineCharacter.skeletonData.findSlot("clip");
        SpineCharacter.slotGameXray = SpineCharacter.skeletonData.findSlot("game/xray");
        SpineCharacter.skinDefault = SpineCharacter.skeletonData.findSkin("default");
        SpineCharacter.skinBody1 = SpineCharacter.skeletonData.findSkin("body1");
        SpineCharacter.skinBody2 = SpineCharacter.skeletonData.findSkin("body2");
        SpineCharacter.skinBody3 = SpineCharacter.skeletonData.findSkin("body3");
        SpineCharacter.skinBody4 = SpineCharacter.skeletonData.findSkin("body4");
        SpineCharacter.skinBody5 = SpineCharacter.skeletonData.findSkin("body5");
        SpineCharacter.skinBody6 = SpineCharacter.skeletonData.findSkin("body6");
        SpineCharacter.skinBody7 = SpineCharacter.skeletonData.findSkin("body7");
        SpineCharacter.skinBody8 = SpineCharacter.skeletonData.findSkin("body8");
        SpineCharacter.skinBraces1 = SpineCharacter.skeletonData.findSkin("braces1");
        SpineCharacter.skinBraces2 = SpineCharacter.skeletonData.findSkin("braces2");
        SpineCharacter.skinBraces3 = SpineCharacter.skeletonData.findSkin("braces3");
        SpineCharacter.skinBraces4 = SpineCharacter.skeletonData.findSkin("braces4");
        SpineCharacter.skinBraces5 = SpineCharacter.skeletonData.findSkin("braces5");
        SpineCharacter.skinBraces6 = SpineCharacter.skeletonData.findSkin("braces6");
        SpineCharacter.skinBraces7 = SpineCharacter.skeletonData.findSkin("braces7");
        SpineCharacter.skinBraces8 = SpineCharacter.skeletonData.findSkin("braces8");
        SpineCharacter.skinBraces9 = SpineCharacter.skeletonData.findSkin("braces9");
        SpineCharacter.skinBraces10 = SpineCharacter.skeletonData.findSkin("braces10");
        SpineCharacter.skinBraces11 = SpineCharacter.skeletonData.findSkin("braces11");
        SpineCharacter.skinBraces12 = SpineCharacter.skeletonData.findSkin("braces12");
        SpineCharacter.skinBraces13 = SpineCharacter.skeletonData.findSkin("braces13");
        SpineCharacter.skinBraces14 = SpineCharacter.skeletonData.findSkin("braces14");
        SpineCharacter.skinBraces15 = SpineCharacter.skeletonData.findSkin("braces15");
        SpineCharacter.skinBraces16 = SpineCharacter.skeletonData.findSkin("braces16");
        SpineCharacter.skinHairCop = SpineCharacter.skeletonData.findSkin("hair-cop");
        SpineCharacter.skinHairFemaleBlack1 = SpineCharacter.skeletonData.findSkin("hair-female-black1");
        SpineCharacter.skinHairFemaleBlack2 = SpineCharacter.skeletonData.findSkin("hair-female-black2");
        SpineCharacter.skinHairFemaleBlack3 = SpineCharacter.skeletonData.findSkin("hair-female-black3");
        SpineCharacter.skinHairFemaleBlack4 = SpineCharacter.skeletonData.findSkin("hair-female-black4");
        SpineCharacter.skinHairFemaleBlack5 = SpineCharacter.skeletonData.findSkin("hair-female-black5");
        SpineCharacter.skinHairFemaleBlack6 = SpineCharacter.skeletonData.findSkin("hair-female-black6");
        SpineCharacter.skinHairFemaleBlonde1 = SpineCharacter.skeletonData.findSkin("hair-female-blonde1");
        SpineCharacter.skinHairFemaleBlonde2 = SpineCharacter.skeletonData.findSkin("hair-female-blonde2");
        SpineCharacter.skinHairFemaleBlonde3 = SpineCharacter.skeletonData.findSkin("hair-female-blonde3");
        SpineCharacter.skinHairFemaleBlonde4 = SpineCharacter.skeletonData.findSkin("hair-female-blonde4");
        SpineCharacter.skinHairFemaleBlonde5 = SpineCharacter.skeletonData.findSkin("hair-female-blonde5");
        SpineCharacter.skinHairFemaleBlonde6 = SpineCharacter.skeletonData.findSkin("hair-female-blonde6");
        SpineCharacter.skinHairFemaleBrown1 = SpineCharacter.skeletonData.findSkin("hair-female-brown1");
        SpineCharacter.skinHairFemaleBrown2 = SpineCharacter.skeletonData.findSkin("hair-female-brown2");
        SpineCharacter.skinHairFemaleBrown3 = SpineCharacter.skeletonData.findSkin("hair-female-brown3");
        SpineCharacter.skinHairFemaleBrown4 = SpineCharacter.skeletonData.findSkin("hair-female-brown4");
        SpineCharacter.skinHairFemaleBrown5 = SpineCharacter.skeletonData.findSkin("hair-female-brown5");
        SpineCharacter.skinHairFemaleBrown6 = SpineCharacter.skeletonData.findSkin("hair-female-brown6");
        SpineCharacter.skinHairFemaleRed1 = SpineCharacter.skeletonData.findSkin("hair-female-red1");
        SpineCharacter.skinHairFemaleRed2 = SpineCharacter.skeletonData.findSkin("hair-female-red2");
        SpineCharacter.skinHairFemaleRed3 = SpineCharacter.skeletonData.findSkin("hair-female-red3");
        SpineCharacter.skinHairFemaleRed4 = SpineCharacter.skeletonData.findSkin("hair-female-red4");
        SpineCharacter.skinHairFemaleRed5 = SpineCharacter.skeletonData.findSkin("hair-female-red5");
        SpineCharacter.skinHairFemaleRed6 = SpineCharacter.skeletonData.findSkin("hair-female-red6");
        SpineCharacter.skinHairMaleBlack1 = SpineCharacter.skeletonData.findSkin("hair-male-black1");
        SpineCharacter.skinHairMaleBlack2 = SpineCharacter.skeletonData.findSkin("hair-male-black2");
        SpineCharacter.skinHairMaleBlack3 = SpineCharacter.skeletonData.findSkin("hair-male-black3");
        SpineCharacter.skinHairMaleBlack4 = SpineCharacter.skeletonData.findSkin("hair-male-black4");
        SpineCharacter.skinHairMaleBlack5 = SpineCharacter.skeletonData.findSkin("hair-male-black5");
        SpineCharacter.skinHairMaleBlack6 = SpineCharacter.skeletonData.findSkin("hair-male-black6");
        SpineCharacter.skinHairMaleBlonde1 = SpineCharacter.skeletonData.findSkin("hair-male-blonde1");
        SpineCharacter.skinHairMaleBlonde2 = SpineCharacter.skeletonData.findSkin("hair-male-blonde2");
        SpineCharacter.skinHairMaleBlonde3 = SpineCharacter.skeletonData.findSkin("hair-male-blonde3");
        SpineCharacter.skinHairMaleBlonde4 = SpineCharacter.skeletonData.findSkin("hair-male-blonde4");
        SpineCharacter.skinHairMaleBlonde5 = SpineCharacter.skeletonData.findSkin("hair-male-blonde5");
        SpineCharacter.skinHairMaleBlonde6 = SpineCharacter.skeletonData.findSkin("hair-male-blonde6");
        SpineCharacter.skinHairMaleBrown1 = SpineCharacter.skeletonData.findSkin("hair-male-brown1");
        SpineCharacter.skinHairMaleBrown2 = SpineCharacter.skeletonData.findSkin("hair-male-brown2");
        SpineCharacter.skinHairMaleBrown3 = SpineCharacter.skeletonData.findSkin("hair-male-brown3");
        SpineCharacter.skinHairMaleBrown4 = SpineCharacter.skeletonData.findSkin("hair-male-brown4");
        SpineCharacter.skinHairMaleBrown5 = SpineCharacter.skeletonData.findSkin("hair-male-brown5");
        SpineCharacter.skinHairMaleBrown6 = SpineCharacter.skeletonData.findSkin("hair-male-brown6");
        SpineCharacter.skinHairMaleRed1 = SpineCharacter.skeletonData.findSkin("hair-male-red1");
        SpineCharacter.skinHairMaleRed2 = SpineCharacter.skeletonData.findSkin("hair-male-red2");
        SpineCharacter.skinHairMaleRed3 = SpineCharacter.skeletonData.findSkin("hair-male-red3");
        SpineCharacter.skinHairMaleRed4 = SpineCharacter.skeletonData.findSkin("hair-male-red4");
        SpineCharacter.skinHairMaleRed5 = SpineCharacter.skeletonData.findSkin("hair-male-red5");
        SpineCharacter.skinHairMaleRed6 = SpineCharacter.skeletonData.findSkin("hair-male-red6");
        SpineGumballMachine.skeletonData = assetManager.get("spine/gumball-machine.json");
        SpineGumballMachine.animationData = assetManager.get("spine/gumball-machine.json-animation");
        SpineGumballMachine.animationAnimation = SpineGumballMachine.skeletonData.findAnimation("animation");
        SpineGumballMachine.animationHighlight = SpineGumballMachine.skeletonData.findAnimation("highlight");
        SpineGumballMachine.animationProcessing = SpineGumballMachine.skeletonData.findAnimation("processing");
        SpineGumballMachine.boneRoot = SpineGumballMachine.skeletonData.findBone("root");
        SpineGumballMachine.boneGameGumballMachine = SpineGumballMachine.skeletonData.findBone("game/gumball-machine");
        SpineGumballMachine.slotGameGumballMachine = SpineGumballMachine.skeletonData.findSlot("game/gumball-machine");
        SpineGumballMachine.slotBbox = SpineGumballMachine.skeletonData.findSlot("bbox");
        SpineGumballMachine.skinDefault = SpineGumballMachine.skeletonData.findSkin("default");
        SpineGumball.skeletonData = assetManager.get("spine/gumball.json");
        SpineGumball.animationData = assetManager.get("spine/gumball.json-animation");
        SpineGumball.animationAnimation = SpineGumball.skeletonData.findAnimation("animation");
        SpineGumball.animationHighlight = SpineGumball.skeletonData.findAnimation("highlight");
        SpineGumball.animationSpawn = SpineGumball.skeletonData.findAnimation("spawn");
        SpineGumball.boneRoot = SpineGumball.skeletonData.findBone("root");
        SpineGumball.boneGameGumball = SpineGumball.skeletonData.findBone("game/gumball");
        SpineGumball.slotGameGumball = SpineGumball.skeletonData.findSlot("game/gumball");
        SpineGumball.slotBbox = SpineGumball.skeletonData.findSlot("bbox");
        SpineGumball.skinDefault = SpineGumball.skeletonData.findSkin("default");
        SpineHammer.skeletonData = assetManager.get("spine/hammer.json");
        SpineHammer.animationData = assetManager.get("spine/hammer.json-animation");
        SpineHammer.animationAnimation = SpineHammer.skeletonData.findAnimation("animation");
        SpineHammer.animationHam = SpineHammer.skeletonData.findAnimation("ham");
        SpineHammer.boneRoot = SpineHammer.skeletonData.findBone("root");
        SpineHammer.boneGameHammer = SpineHammer.skeletonData.findBone("game/hammer");
        SpineHammer.slotGameHammer = SpineHammer.skeletonData.findSlot("game/hammer");
        SpineHammer.skinDefault = SpineHammer.skeletonData.findSkin("default");
        SpineLibgdx.skeletonData = assetManager.get("spine/libgdx.json");
        SpineLibgdx.animationData = assetManager.get("spine/libgdx.json-animation");
        SpineLibgdx.animationAnimation = SpineLibgdx.skeletonData.findAnimation("animation");
        SpineLibgdx.animationStand = SpineLibgdx.skeletonData.findAnimation("stand");
        SpineLibgdx.eventSfxBlast = SpineLibgdx.skeletonData.findEvent("sfx/blast");
        SpineLibgdx.eventSfxWoosh = SpineLibgdx.skeletonData.findEvent("sfx/woosh");
        SpineLibgdx.eventSfxZap = SpineLibgdx.skeletonData.findEvent("sfx/zap");
        SpineLibgdx.eventSfxZoom = SpineLibgdx.skeletonData.findEvent("sfx/zoom");
        SpineLibgdx.boneRoot = SpineLibgdx.skeletonData.findBone("root");
        SpineLibgdx.boneLogoLibgdxL = SpineLibgdx.skeletonData.findBone("logo/libgdx-l");
        SpineLibgdx.boneLogoLibgdxI = SpineLibgdx.skeletonData.findBone("logo/libgdx-i");
        SpineLibgdx.boneLogoLibgdxB = SpineLibgdx.skeletonData.findBone("logo/libgdx-b");
        SpineLibgdx.boneLogoLibgdxG = SpineLibgdx.skeletonData.findBone("logo/libgdx-g");
        SpineLibgdx.boneLogoLibgdxD = SpineLibgdx.skeletonData.findBone("logo/libgdx-d");
        SpineLibgdx.boneLogoLibgdxX = SpineLibgdx.skeletonData.findBone("logo/libgdx-x");
        SpineLibgdx.boneLogoLibgdxCloud = SpineLibgdx.skeletonData.findBone("logo/libgdx-cloud");
        SpineLibgdx.boneLogoLibgdxCloud2 = SpineLibgdx.skeletonData.findBone("logo/libgdx-cloud2");
        SpineLibgdx.boneLogoLibgdxEarth = SpineLibgdx.skeletonData.findBone("logo/libgdx-earth");
        SpineLibgdx.boneLogoLibgdxExplosion = SpineLibgdx.skeletonData.findBone("logo/libgdx-explosion");
        SpineLibgdx.boneLogoLibgdxSatellite = SpineLibgdx.skeletonData.findBone("logo/libgdx-satellite");
        SpineLibgdx.boneLogoLibgdxLaser = SpineLibgdx.skeletonData.findBone("logo/libgdx-laser");
        SpineLibgdx.slotLogoWhite = SpineLibgdx.skeletonData.findSlot("logo/white");
        SpineLibgdx.slotLogoLibgdxL = SpineLibgdx.skeletonData.findSlot("logo/libgdx-l");
        SpineLibgdx.slotLogoLibgdxI = SpineLibgdx.skeletonData.findSlot("logo/libgdx-i");
        SpineLibgdx.slotLogoLibgdxB = SpineLibgdx.skeletonData.findSlot("logo/libgdx-b");
        SpineLibgdx.slotLogoLibgdxG = SpineLibgdx.skeletonData.findSlot("logo/libgdx-g");
        SpineLibgdx.slotLogoLibgdxD = SpineLibgdx.skeletonData.findSlot("logo/libgdx-d");
        SpineLibgdx.slotLogoLibgdxX = SpineLibgdx.skeletonData.findSlot("logo/libgdx-x");
        SpineLibgdx.slotLogoLibgdxCloud = SpineLibgdx.skeletonData.findSlot("logo/libgdx-cloud");
        SpineLibgdx.slotLogoLibgdxCloud2 = SpineLibgdx.skeletonData.findSlot("logo/libgdx-cloud2");
        SpineLibgdx.slotLogoLibgdxEarth = SpineLibgdx.skeletonData.findSlot("logo/libgdx-earth");
        SpineLibgdx.slotLogoLibgdxExplosion = SpineLibgdx.skeletonData.findSlot("logo/libgdx-explosion");
        SpineLibgdx.slotLogoLibgdxLaser = SpineLibgdx.skeletonData.findSlot("logo/libgdx-laser");
        SpineLibgdx.slotLogoLibgdxSatellite = SpineLibgdx.skeletonData.findSlot("logo/libgdx-satellite");
        SpineLibgdx.skinDefault = SpineLibgdx.skeletonData.findSkin("default");
        SpineNight.skeletonData = assetManager.get("spine/night.json");
        SpineNight.animationData = assetManager.get("spine/night.json-animation");
        SpineNight.animationAnimation = SpineNight.skeletonData.findAnimation("animation");
        SpineNight.animationNightfall = SpineNight.skeletonData.findAnimation("nightfall");
        SpineNight.boneRoot = SpineNight.skeletonData.findBone("root");
        SpineNight.slotGameNight = SpineNight.skeletonData.findSlot("game/night");
        SpineNight.skinDefault = SpineNight.skeletonData.findSkin("default");
        SpineRay3k.skeletonData = assetManager.get("spine/ray3k.json");
        SpineRay3k.animationData = assetManager.get("spine/ray3k.json-animation");
        SpineRay3k.animationAnimation = SpineRay3k.skeletonData.findAnimation("animation");
        SpineRay3k.animationStand = SpineRay3k.skeletonData.findAnimation("stand");
        SpineRay3k.eventSfxMk3 = SpineRay3k.skeletonData.findEvent("sfx/mk3");
        SpineRay3k.eventSfxSpooky = SpineRay3k.skeletonData.findEvent("sfx/spooky");
        SpineRay3k.boneRoot = SpineRay3k.skeletonData.findBone("root");
        SpineRay3k.boneLogoRay3kUltimate = SpineRay3k.skeletonData.findBone("logo/ray3k-ultimate");
        SpineRay3k.boneLogoRay3kTitle = SpineRay3k.skeletonData.findBone("logo/ray3k-title");
        SpineRay3k.slotLogoWhite = SpineRay3k.skeletonData.findSlot("logo/white");
        SpineRay3k.slotLogoRay3kQuote = SpineRay3k.skeletonData.findSlot("logo/ray3k-quote");
        SpineRay3k.slotLogoRay3kBg = SpineRay3k.skeletonData.findSlot("logo/ray3k-bg");
        SpineRay3k.slotLogoRay3kTitle = SpineRay3k.skeletonData.findSlot("logo/ray3k-title");
        SpineRay3k.slotLogoRay3kUltimate = SpineRay3k.skeletonData.findSlot("logo/ray3k-ultimate");
        SpineRay3k.skinDefault = SpineRay3k.skeletonData.findSkin("default");
        SpineRegister.skeletonData = assetManager.get("spine/register.json");
        SpineRegister.animationData = assetManager.get("spine/register.json-animation");
        SpineRegister.animationAnimation = SpineRegister.skeletonData.findAnimation("animation");
        SpineRegister.animationHighlight = SpineRegister.skeletonData.findAnimation("highlight");
        SpineRegister.boneRoot = SpineRegister.skeletonData.findBone("root");
        SpineRegister.slotGameRegister = SpineRegister.skeletonData.findSlot("game/register");
        SpineRegister.slotBbox = SpineRegister.skeletonData.findSlot("bbox");
        SpineRegister.skinDefault = SpineRegister.skeletonData.findSkin("default");
        SpineScalpel.skeletonData = assetManager.get("spine/scalpel.json");
        SpineScalpel.animationData = assetManager.get("spine/scalpel.json-animation");
        SpineScalpel.animationAnimation = SpineScalpel.skeletonData.findAnimation("animation");
        SpineScalpel.boneRoot = SpineScalpel.skeletonData.findBone("root");
        SpineScalpel.slotGameScalpel = SpineScalpel.skeletonData.findSlot("game/scalpel");
        SpineScalpel.skinDefault = SpineScalpel.skeletonData.findSkin("default");
        SpineToothbrush.skeletonData = assetManager.get("spine/toothbrush.json");
        SpineToothbrush.animationData = assetManager.get("spine/toothbrush.json-animation");
        SpineToothbrush.animationAnimation = SpineToothbrush.skeletonData.findAnimation("animation");
        SpineToothbrush.boneRoot = SpineToothbrush.skeletonData.findBone("root");
        SpineToothbrush.slotGameToothbrush = SpineToothbrush.skeletonData.findSlot("game/toothbrush");
        SpineToothbrush.skinDefault = SpineToothbrush.skeletonData.findSkin("default");
        SpineTrash.skeletonData = assetManager.get("spine/trash.json");
        SpineTrash.animationData = assetManager.get("spine/trash.json-animation");
        SpineTrash.animationAnimation = SpineTrash.skeletonData.findAnimation("animation");
        SpineTrash.animationHighlight = SpineTrash.skeletonData.findAnimation("highlight");
        SpineTrash.animationShake = SpineTrash.skeletonData.findAnimation("shake");
        SpineTrash.boneRoot = SpineTrash.skeletonData.findBone("root");
        SpineTrash.boneGameTrash = SpineTrash.skeletonData.findBone("game/trash");
        SpineTrash.slotGameTrash = SpineTrash.skeletonData.findSlot("game/trash");
        SpineTrash.slotBbox = SpineTrash.skeletonData.findSlot("bbox");
        SpineTrash.skinDefault = SpineTrash.skeletonData.findSkin("default");
        SpineZebra.skeletonData = assetManager.get("spine/zebra.json");
        SpineZebra.animationData = assetManager.get("spine/zebra.json-animation");
        SpineZebra.animationCelebrate = SpineZebra.skeletonData.findAnimation("celebrate");
        SpineZebra.animationOperation = SpineZebra.skeletonData.findAnimation("operation");
        SpineZebra.animationOperationDone = SpineZebra.skeletonData.findAnimation("operationDone");
        SpineZebra.animationStand = SpineZebra.skeletonData.findAnimation("stand");
        SpineZebra.animationWalk = SpineZebra.skeletonData.findAnimation("walk");
        SpineZebra.boneRoot = SpineZebra.skeletonData.findBone("root");
        SpineZebra.boneGameZebraBody = SpineZebra.skeletonData.findBone("game/zebra-body");
        SpineZebra.boneGameZebraBody2 = SpineZebra.skeletonData.findBone("game/zebra-body2");
        SpineZebra.boneGameZebraBody3 = SpineZebra.skeletonData.findBone("game/zebra-body3");
        SpineZebra.boneGameZebraBody4 = SpineZebra.skeletonData.findBone("game/zebra-body4");
        SpineZebra.boneGameZebraBody5 = SpineZebra.skeletonData.findBone("game/zebra-body5");
        SpineZebra.boneGameZebraHead = SpineZebra.skeletonData.findBone("game/zebra-head");
        SpineZebra.boneRightTarget = SpineZebra.skeletonData.findBone("right-target");
        SpineZebra.boneLeftTarget = SpineZebra.skeletonData.findBone("left-target");
        SpineZebra.slotGameZebraBody = SpineZebra.skeletonData.findSlot("game/zebra-body");
        SpineZebra.slotGameZebraHead = SpineZebra.skeletonData.findSlot("game/zebra-head");
        SpineZebra.slotGameZebraHand = SpineZebra.skeletonData.findSlot("game/zebra-hand");
        SpineZebra.slotGameZebraHand2 = SpineZebra.skeletonData.findSlot("game/zebra-hand2");
        SpineZebra.skinDefault = SpineZebra.skeletonData.findSkin("default");
        skin_skin = assetManager.get("skin/skin.json");
        SkinSkinStyles.bDefault = skin_skin.get("default", Button.ButtonStyle.class);
        SkinSkinStyles.bChangeLeft = skin_skin.get("change-left", Button.ButtonStyle.class);
        SkinSkinStyles.bChangeRight = skin_skin.get("change-right", Button.ButtonStyle.class);
        SkinSkinStyles.lTime = skin_skin.get("time", Label.LabelStyle.class);
        SkinSkinStyles.lDefault = skin_skin.get("default", Label.LabelStyle.class);
        SkinSkinStyles.lTitle = skin_skin.get("title", Label.LabelStyle.class);
        SkinSkinStyles.lButton = skin_skin.get("button", Label.LabelStyle.class);
        SkinSkinStyles.lMoney = skin_skin.get("money", Label.LabelStyle.class);
        SkinSkinStyles.pDefaultVertical = skin_skin.get("default-vertical", ProgressBar.ProgressBarStyle.class);
        SkinSkinStyles.pDefaultHorizontal = skin_skin.get("default-horizontal", ProgressBar.ProgressBarStyle.class);
        SkinSkinStyles.spDefault = skin_skin.get("default", ScrollPane.ScrollPaneStyle.class);
        SkinSkinStyles.sDefaultHorizontal = skin_skin.get("default-horizontal", Slider.SliderStyle.class);
        SkinSkinStyles.tbDefault = skin_skin.get("default", TextButton.TextButtonStyle.class);
        SkinSkinStyles.ttDefault = skin_skin.get("default", TextTooltip.TextTooltipStyle.class);
        SkinSkinStyles.wDefault = skin_skin.get("default", Window.WindowStyle.class);
        sfx_ahh = assetManager.get("sfx/ahh.mp3");
        sfx_blast = assetManager.get("sfx/blast.mp3");
        sfx_blood = assetManager.get("sfx/blood.mp3");
        sfx_braces = assetManager.get("sfx/braces.mp3");
        sfx_brush = assetManager.get("sfx/brush.mp3");
        sfx_carCrash = assetManager.get("sfx/car-crash.mp3");
        sfx_click = assetManager.get("sfx/click.mp3");
        sfx_doorbell = assetManager.get("sfx/doorbell.mp3");
        sfx_drill = assetManager.get("sfx/drill.mp3");
        sfx_grumpy = assetManager.get("sfx/grumpy.mp3");
        sfx_gumball = assetManager.get("sfx/gumball.mp3");
        sfx_hammer = assetManager.get("sfx/hammer.mp3");
        sfx_hmm = assetManager.get("sfx/hmm.mp3");
        sfx_libgdx = assetManager.get("sfx/libgdx.mp3");
        sfx_mk3 = assetManager.get("sfx/mk3.mp3");
        sfx_pleaseDontKillMe = assetManager.get("sfx/please don't kill me.mp3");
        sfx_register = assetManager.get("sfx/register.mp3");
        sfx_rim = assetManager.get("sfx/rim.mp3");
        sfx_shot = assetManager.get("sfx/shot.mp3");
        sfx_slash = assetManager.get("sfx/slash.mp3");
        sfx_spooky = assetManager.get("sfx/spooky.mp3");
        sfx_swoosh = assetManager.get("sfx/swoosh.mp3");
        sfx_thanksZebraFemale01 = assetManager.get("sfx/thanks-zebra-female-01.mp3");
        sfx_thanksZebraFemale02 = assetManager.get("sfx/thanks-zebra-female-02.mp3");
        sfx_thanksZebraFemale03 = assetManager.get("sfx/thanks-zebra-female-03.mp3");
        sfx_thanksZebraFemale04 = assetManager.get("sfx/thanks-zebra-female-04.mp3");
        sfx_thanksZebraFemale05 = assetManager.get("sfx/thanks-zebra-female-05.mp3");
        sfx_thanksZebraFemale06 = assetManager.get("sfx/thanks-zebra-female-06.mp3");
        sfx_thanksZebraMale01 = assetManager.get("sfx/thanks-zebra-male-01.mp3");
        sfx_thanksZebraMale02 = assetManager.get("sfx/thanks-zebra-male-02.mp3");
        sfx_thanksZebraMale03 = assetManager.get("sfx/thanks-zebra-male-03.mp3");
        sfx_thanksZebraMale04 = assetManager.get("sfx/thanks-zebra-male-04.mp3");
        sfx_thanksZebraMale05 = assetManager.get("sfx/thanks-zebra-male-05.mp3");
        sfx_thanksZebraMale06 = assetManager.get("sfx/thanks-zebra-male-06.mp3");
        sfx_thanksZebraWacky01 = assetManager.get("sfx/thanks-zebra-wacky-01.mp3");
        sfx_thanksZebraWacky02 = assetManager.get("sfx/thanks-zebra-wacky-02.mp3");
        sfx_thanksZebraWacky03 = assetManager.get("sfx/thanks-zebra-wacky-03.mp3");
        sfx_thanksZebraWacky04 = assetManager.get("sfx/thanks-zebra-wacky-04.mp3");
        sfx_thanksZebraWacky05 = assetManager.get("sfx/thanks-zebra-wacky-05.mp3");
        sfx_thanksZebraWacky06 = assetManager.get("sfx/thanks-zebra-wacky-06.mp3");
        sfx_thanksZebraWacky07 = assetManager.get("sfx/thanks-zebra-wacky-07.mp3");
        sfx_thanksZebraWacky08 = assetManager.get("sfx/thanks-zebra-wacky-08.mp3");
        sfx_thanksZebraWacky09 = assetManager.get("sfx/thanks-zebra-wacky-09.mp3");
        sfx_thanksZebraWacky10 = assetManager.get("sfx/thanks-zebra-wacky-10.mp3");
        sfx_thud = assetManager.get("sfx/thud.mp3");
        sfx_tv = assetManager.get("sfx/tv.mp3");
        sfx_woosh = assetManager.get("sfx/woosh.mp3");
        sfx_zap = assetManager.get("sfx/zap.mp3");
        sfx_zoom = assetManager.get("sfx/zoom.mp3");
        bgm_audioSample = assetManager.get("bgm/audio-sample.mp3");
        bgm_game = assetManager.get("bgm/game.ogg");
        bgm_horror = assetManager.get("bgm/horror.ogg");
        bgm_menu = assetManager.get("bgm/menu.ogg");
    }

    public static class SpineChair {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHighlight;

        public static Animation animationNoHighlight;

        public static BoneData boneRoot;

        public static SlotData slotGameChairWaiting;

        public static SlotData slotBbox;

        public static com.esotericsoftware.spine.Skin skinDefault;

        public static com.esotericsoftware.spine.Skin skinBlue;

        public static com.esotericsoftware.spine.Skin skinChair;

        public static com.esotericsoftware.spine.Skin skinOrange;

        public static com.esotericsoftware.spine.Skin skinWhite;

        public static com.esotericsoftware.spine.Skin skinYellow;
    }

    public static class SpineCharacter {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationBleed;

        public static Animation animationDead;

        public static Animation animationHighlight;

        public static Animation animationMouthHappy;

        public static Animation animationMouthNeutral;

        public static Animation animationMouthSad;

        public static Animation animationMouthSmile;

        public static Animation animationNoHighlight;

        public static Animation animationOverlayBlack;

        public static Animation animationOverlayDecay;

        public static Animation animationPickedup;

        public static Animation animationStand;

        public static Animation animationThumbsDown;

        public static Animation animationThumbsUp;

        public static Animation animationWalk;

        public static Animation animationXray;

        public static Animation animationZoomed;

        public static Animation animationZoomedBraces;

        public static BoneData boneRoot;

        public static BoneData boneBody;

        public static BoneData boneBody2;

        public static BoneData boneBody3;

        public static BoneData boneBody4;

        public static BoneData boneBody5;

        public static BoneData boneTargetRight;

        public static BoneData boneTargetLeft;

        public static BoneData boneBlood;

        public static BoneData boneFace;

        public static BoneData boneIndicator;

        public static BoneData boneHearts;

        public static SlotData slotBody;

        public static SlotData slotBlood;

        public static SlotData slotFace;

        public static SlotData slotEyes;

        public static SlotData slotHair;

        public static SlotData slotMouth;

        public static SlotData slotHandLeft;

        public static SlotData slotHandRight;

        public static SlotData slotMouthOverlay;

        public static SlotData slotBbox;

        public static SlotData slotClip;

        public static SlotData slotGameXray;

        public static com.esotericsoftware.spine.Skin skinDefault;

        public static com.esotericsoftware.spine.Skin skinBody1;

        public static com.esotericsoftware.spine.Skin skinBody2;

        public static com.esotericsoftware.spine.Skin skinBody3;

        public static com.esotericsoftware.spine.Skin skinBody4;

        public static com.esotericsoftware.spine.Skin skinBody5;

        public static com.esotericsoftware.spine.Skin skinBody6;

        public static com.esotericsoftware.spine.Skin skinBody7;

        public static com.esotericsoftware.spine.Skin skinBody8;

        public static com.esotericsoftware.spine.Skin skinBraces1;

        public static com.esotericsoftware.spine.Skin skinBraces2;

        public static com.esotericsoftware.spine.Skin skinBraces3;

        public static com.esotericsoftware.spine.Skin skinBraces4;

        public static com.esotericsoftware.spine.Skin skinBraces5;

        public static com.esotericsoftware.spine.Skin skinBraces6;

        public static com.esotericsoftware.spine.Skin skinBraces7;

        public static com.esotericsoftware.spine.Skin skinBraces8;

        public static com.esotericsoftware.spine.Skin skinBraces9;

        public static com.esotericsoftware.spine.Skin skinBraces10;

        public static com.esotericsoftware.spine.Skin skinBraces11;

        public static com.esotericsoftware.spine.Skin skinBraces12;

        public static com.esotericsoftware.spine.Skin skinBraces13;

        public static com.esotericsoftware.spine.Skin skinBraces14;

        public static com.esotericsoftware.spine.Skin skinBraces15;

        public static com.esotericsoftware.spine.Skin skinBraces16;

        public static com.esotericsoftware.spine.Skin skinHairCop;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlack1;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlack2;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlack3;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlack4;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlack5;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlack6;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlonde1;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlonde2;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlonde3;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlonde4;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlonde5;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBlonde6;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBrown1;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBrown2;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBrown3;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBrown4;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBrown5;

        public static com.esotericsoftware.spine.Skin skinHairFemaleBrown6;

        public static com.esotericsoftware.spine.Skin skinHairFemaleRed1;

        public static com.esotericsoftware.spine.Skin skinHairFemaleRed2;

        public static com.esotericsoftware.spine.Skin skinHairFemaleRed3;

        public static com.esotericsoftware.spine.Skin skinHairFemaleRed4;

        public static com.esotericsoftware.spine.Skin skinHairFemaleRed5;

        public static com.esotericsoftware.spine.Skin skinHairFemaleRed6;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlack1;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlack2;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlack3;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlack4;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlack5;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlack6;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlonde1;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlonde2;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlonde3;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlonde4;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlonde5;

        public static com.esotericsoftware.spine.Skin skinHairMaleBlonde6;

        public static com.esotericsoftware.spine.Skin skinHairMaleBrown1;

        public static com.esotericsoftware.spine.Skin skinHairMaleBrown2;

        public static com.esotericsoftware.spine.Skin skinHairMaleBrown3;

        public static com.esotericsoftware.spine.Skin skinHairMaleBrown4;

        public static com.esotericsoftware.spine.Skin skinHairMaleBrown5;

        public static com.esotericsoftware.spine.Skin skinHairMaleBrown6;

        public static com.esotericsoftware.spine.Skin skinHairMaleRed1;

        public static com.esotericsoftware.spine.Skin skinHairMaleRed2;

        public static com.esotericsoftware.spine.Skin skinHairMaleRed3;

        public static com.esotericsoftware.spine.Skin skinHairMaleRed4;

        public static com.esotericsoftware.spine.Skin skinHairMaleRed5;

        public static com.esotericsoftware.spine.Skin skinHairMaleRed6;
    }

    public static class SpineGumballMachine {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHighlight;

        public static Animation animationProcessing;

        public static BoneData boneRoot;

        public static BoneData boneGameGumballMachine;

        public static SlotData slotGameGumballMachine;

        public static SlotData slotBbox;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineGumball {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHighlight;

        public static Animation animationSpawn;

        public static BoneData boneRoot;

        public static BoneData boneGameGumball;

        public static SlotData slotGameGumball;

        public static SlotData slotBbox;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineHammer {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHam;

        public static BoneData boneRoot;

        public static BoneData boneGameHammer;

        public static SlotData slotGameHammer;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineLibgdx {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static EventData eventSfxBlast;

        public static EventData eventSfxWoosh;

        public static EventData eventSfxZap;

        public static EventData eventSfxZoom;

        public static BoneData boneRoot;

        public static BoneData boneLogoLibgdxL;

        public static BoneData boneLogoLibgdxI;

        public static BoneData boneLogoLibgdxB;

        public static BoneData boneLogoLibgdxG;

        public static BoneData boneLogoLibgdxD;

        public static BoneData boneLogoLibgdxX;

        public static BoneData boneLogoLibgdxCloud;

        public static BoneData boneLogoLibgdxCloud2;

        public static BoneData boneLogoLibgdxEarth;

        public static BoneData boneLogoLibgdxExplosion;

        public static BoneData boneLogoLibgdxSatellite;

        public static BoneData boneLogoLibgdxLaser;

        public static SlotData slotLogoWhite;

        public static SlotData slotLogoLibgdxL;

        public static SlotData slotLogoLibgdxI;

        public static SlotData slotLogoLibgdxB;

        public static SlotData slotLogoLibgdxG;

        public static SlotData slotLogoLibgdxD;

        public static SlotData slotLogoLibgdxX;

        public static SlotData slotLogoLibgdxCloud;

        public static SlotData slotLogoLibgdxCloud2;

        public static SlotData slotLogoLibgdxEarth;

        public static SlotData slotLogoLibgdxExplosion;

        public static SlotData slotLogoLibgdxLaser;

        public static SlotData slotLogoLibgdxSatellite;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineNight {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationNightfall;

        public static BoneData boneRoot;

        public static SlotData slotGameNight;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRay3k {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationStand;

        public static EventData eventSfxMk3;

        public static EventData eventSfxSpooky;

        public static BoneData boneRoot;

        public static BoneData boneLogoRay3kUltimate;

        public static BoneData boneLogoRay3kTitle;

        public static SlotData slotLogoWhite;

        public static SlotData slotLogoRay3kQuote;

        public static SlotData slotLogoRay3kBg;

        public static SlotData slotLogoRay3kTitle;

        public static SlotData slotLogoRay3kUltimate;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineRegister {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHighlight;

        public static BoneData boneRoot;

        public static SlotData slotGameRegister;

        public static SlotData slotBbox;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineScalpel {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameScalpel;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineToothbrush {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static BoneData boneRoot;

        public static SlotData slotGameToothbrush;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineTrash {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationAnimation;

        public static Animation animationHighlight;

        public static Animation animationShake;

        public static BoneData boneRoot;

        public static BoneData boneGameTrash;

        public static SlotData slotGameTrash;

        public static SlotData slotBbox;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SpineZebra {
        public static SkeletonData skeletonData;

        public static AnimationStateData animationData;

        public static Animation animationCelebrate;

        public static Animation animationOperation;

        public static Animation animationOperationDone;

        public static Animation animationStand;

        public static Animation animationWalk;

        public static BoneData boneRoot;

        public static BoneData boneGameZebraBody;

        public static BoneData boneGameZebraBody2;

        public static BoneData boneGameZebraBody3;

        public static BoneData boneGameZebraBody4;

        public static BoneData boneGameZebraBody5;

        public static BoneData boneGameZebraHead;

        public static BoneData boneRightTarget;

        public static BoneData boneLeftTarget;

        public static SlotData slotGameZebraBody;

        public static SlotData slotGameZebraHead;

        public static SlotData slotGameZebraHand;

        public static SlotData slotGameZebraHand2;

        public static com.esotericsoftware.spine.Skin skinDefault;
    }

    public static class SkinSkinStyles {
        public static Button.ButtonStyle bDefault;

        public static Button.ButtonStyle bChangeLeft;

        public static Button.ButtonStyle bChangeRight;

        public static Label.LabelStyle lTime;

        public static Label.LabelStyle lDefault;

        public static Label.LabelStyle lTitle;

        public static Label.LabelStyle lButton;

        public static Label.LabelStyle lMoney;

        public static ProgressBar.ProgressBarStyle pDefaultVertical;

        public static ProgressBar.ProgressBarStyle pDefaultHorizontal;

        public static ScrollPane.ScrollPaneStyle spDefault;

        public static Slider.SliderStyle sDefaultHorizontal;

        public static TextButton.TextButtonStyle tbDefault;

        public static TextTooltip.TextTooltipStyle ttDefault;

        public static Window.WindowStyle wDefault;
    }

    public static class Values {
        public static float jumpVelocity = 10.0f;

        public static String name = "Raeleus";

        public static boolean godMode = true;

        public static int id = 10;
    }
}
