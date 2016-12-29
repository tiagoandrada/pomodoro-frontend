package opendoors.app.desktop.pomodoro;

import javafx.application.Platform;
import javafx.scene.control.ProgressIndicator;

public class PomodorTimerProgress implements Runnable {

	private ProgressIndicator indicator;
	private int timeOfTimerInMinutes;
	static float increaseValue;
	static boolean timeToWork = true;
	
	public PomodorTimerProgress(int timeOfTimerInMinutes){
		this.setTimeOfTimerInMinutes(timeOfTimerInMinutes);
		initComponents();
	}
	
	public void initComponents(){
		indicator = new ProgressIndicator(0.0);
		indicator.setStyle("-fx-accent: blue;");
	}
	
	public void setTimeOfTimerInMinutes(int timeInMinutes){
		this.timeOfTimerInMinutes = timeInMinutes;
	}
	
	public ProgressIndicator getIndicator(){
		return indicator;
	}

	@Override
	public void run() {
		float valueToIncreaseProgress = (1f / ((timeOfTimerInMinutes * 60) / 5));
		float valueToIncreaseProgressRelax = (1f / ((5 * 60) / 5));
		
		increaseValue = valueToIncreaseProgress;

		while(true){
			if(timeToWork){
				Platform.runLater(() -> indicator.setProgress(increaseValue));
				increaseValue += valueToIncreaseProgress;
				if(increaseValue > 1.1){
					timeToWork = false;
					increaseValue = 0.0f;
					Platform.runLater(() -> indicator.setStyle("-fx-accent: green;"));
				}
			}else{

				Platform.runLater(() -> indicator.setProgress(increaseValue));
				increaseValue += valueToIncreaseProgressRelax;
				
				if(increaseValue > 1.1){
					timeToWork = true;
					increaseValue = 0.0f;
					Platform.runLater(() -> indicator.setStyle("-fx-accent: blue;"));
					
				}
			}
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}				
	}
	
}
