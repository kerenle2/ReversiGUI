//package reversiapp;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.management.RuntimeErrorException;
//
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//public class SceneManager {
//	private ReversiBoardController board_controller;
//	private ReversiGameController game_controller;
//	private static Stage primaryStage;
//	private static Map<String, Scene> scenes = new HashMap<String,Scene>();
//	Scene menu;
//	Scene start;
//	Scene settings;
//	
//	public SceneManager(Stage primaryStage, Scene menu_scene) {
//		//get all scenes from boardController when here:
//		// ...
//		// ...
//		// ...
//		///////
//	//	this.start = game_controller.getStartScene();
//	//	this.menu = game_controller.getMenuscene();
//	//	this.settings = game_controller.getSettingsScene();
////		this.start = game_controller.getStartScene();
////		this.settings = game_controller.getSettingsScene();
//		this.menu = menu_scene;
//		this.primaryStage = primaryStage;
//		mapTheScenes();
//	}
//	
//	private void mapTheScenes() {
//		scenes.put("menu", this.menu);
//		scenes.put("start", this.start);
//		scenes.put("settings", this.settings);
//	}
//	
//	public static void changeScene(String scene) {
//		if (!scene.equals("menu") || !scene.equals("settings") ||!scene.equals("start")) {
//			throw new RuntimeException("in valid scene string");
//		}
//		Scene s = scenes.get(scene);
//		primaryStage.setScene(s);
//	}
//
//}
