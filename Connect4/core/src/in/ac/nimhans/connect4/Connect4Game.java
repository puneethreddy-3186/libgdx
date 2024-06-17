package in.ac.nimhans.connect4;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import in.ac.nimhans.connect4.screens.MainScreen;

/**
 * The {@link ApplicationListener} for this project, create(), resize() and render() are the only methods that are
 * relevant
 *
 * @author Richard Taylor, Kevin Chen
 */
public class Connect4Game extends Game {

  private Screen screen;
  public static Connect4Game app;

  @Override
  public void create() {
    app = this;
    //Assets.load();
    //Assets.manager.finishLoading();
    this.screen = new MainScreen();
    setScreen(this.screen);
  }

  @Override
  public void dispose() {
    this.screen.hide();
    this.screen.dispose();
    Assets.dispose();
  }

  @Override
  public void pause() {
    this.screen.pause();
  }

  @Override
  public void render() {
    clearWhite();
    super.render();
    // screen.render(Gdx.graphics.getDeltaTime());
  }

  /** Clears the screen with a white color */
  private void clearWhite() {
    Gdx.gl.glClearColor(1, 1, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  @Override
  public void resize(final int width, final int height) {
    this.screen.resize(width, height);
  }

  @Override
  public void resume() {
    this.screen.resume();
  }
}
