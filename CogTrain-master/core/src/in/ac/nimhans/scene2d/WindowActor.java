package in.ac.nimhans.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;

import in.ac.nimhans.manager.WindowAssetManager;
import in.ac.nimhans.model.Window;

public class WindowActor extends Image {

	private Window window;
	private WindowAssetManager windowAssetManager;
	private float scaleFactor = 1.0f;
	private WindowSelectionListener windowSelectionListener;


	public WindowActor(final Window window, WindowAssetManager windowAssetManager) {
		this.window = window;
		this.windowAssetManager = windowAssetManager;
		setScaling(Scaling.fit);
		setBounds(this.window.getXPos(), this.window.getYPos(), windowAssetManager.getWindowWidth(), windowAssetManager.getWindowHeight());
		addListener(new InputListener() {

			@Override public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}


			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				window.setLightOn(!window.isLightOn());
				if (windowSelectionListener != null)
					windowSelectionListener.onWindowTouched(window.getId(), window.isLightOn());
				fireWindowLit();
				validate();
			}
		});
	}


	public WindowActor setWindowAnimation(float startAnimation, float animDelay) {
		addAction(Actions.sequence(Actions.delay(startAnimation), Actions.run(new Runnable() {
			public void run() {
				fireWindowLit();
				window.setLightOn(!window.isLightOn());
				validate();
			}
		}), Actions.delay(animDelay), Actions.run(new Runnable() {
			public void run() {
				window.setLightOn(!window.isLightOn());
				validate();
			}
		})));
		return this;
	}


	private void fireWindowLit() {
		if (windowSelectionListener != null)
			windowSelectionListener.onWindowLit(window.getId(), window.isLightOn());
	}


	public WindowActor setWindowSelectionListener(WindowSelectionListener windowSelectionListener) {
		this.windowSelectionListener = windowSelectionListener;
		return this;
	}


	@Override public void draw(Batch batch, float parentAlpha) {
		Sprite windowSprite = this.windowAssetManager.getWindowSprite(window.isLightOn());
		windowSprite.setScale(scaleFactor);
		batch.draw(windowSprite, window.getXPos(), window.getYPos(), (windowSprite.getWidth() * scaleFactor), (windowSprite.getHeight() * scaleFactor));
	}


	public Window getWindow() {
		return window;
	}


	public WindowActor setScaleFactor(float scaleFactor) {
		this.scaleFactor = scaleFactor;
		return this;
	}


	public interface WindowSelectionListener {
		void onWindowTouched(int windowId, boolean isWindowLit);

		void onWindowLit(int windowId, boolean isWindowLit);
	}
}
