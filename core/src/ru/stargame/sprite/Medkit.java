package ru.stargame.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.stargame.base.Sprite;
import ru.stargame.math.Rect;
import ru.stargame.math.Rnd;

public class Medkit  extends Sprite {
    private Vector2 v = new Vector2();

    private static final float SIZE = 0.05f;
    private static boolean isPrinted = false;
    private static int tempAmount =0;
    private static int amount =0;

    private Rect worldBounds;



    public void update(float delta, int frags) {
        if (frags%20 == 0 && amount == tempAmount) {
            if (frags / 20 != amount) {
                amount = frags / 20;
                isPrinted = true;
            }

        }
        if(isPrinted && amount !=0){
            pos.mulAdd(v, delta);
        }
    }

    public Medkit(Texture texture, Rect worldBounds) {super(new TextureRegion(texture));
    this.v.set(0f, -0.3f);
    this.worldBounds = worldBounds;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.pos.set(worldBounds.pos);
        setHeightProportion(worldBounds.getHeight()*SIZE);
        set();
    }

    public void heal (MainShip mainShip) {
        tempAmount = amount;
        mainShip.healHp(amount*10);
        destroy();
    }

   public void destroy(){
        tempAmount = amount;
       isPrinted=false;
       set();
   }

    public static boolean isIsPrinted() {
        return isPrinted;
    }

    public void set(){
        pos.x = Rnd.nextFloat(
                worldBounds.getLeft() + this.getHalfWidth(),
                worldBounds.getRight() - this.getHalfWidth()
        );
        this.setBottom(worldBounds.getTop());
    }
}
