package game.system;

import game.exceptions.MissingGameFilesException;

import java.io.File;

public class SystemManager {
    public SystemManager(){

    }
    public void checkSystem(){
        try {
            checkGameFiles();
        }
        catch (MissingGameFilesException e){
            exit(e.getMessage());
        }
    }
    public void checkGameFiles() throws MissingGameFilesException {
        String[] filePaths={"src/main/resources/config/Game.properties",
                "src/main/resources/config/EmberfallDominionRewards.properties",
                "src/main/resources/config/MysticalSkyRewards.properties",
                "src/main/resources/config/RadiantSvannaRewards.properties",
                "src/main/resources/config/RoundsRewards.properties",
                "src/main/resources/config/TerrasHeartlandRewards.properties",
                "src/main/resources/config/TideAbyssRewards.properties"


        };
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new MissingGameFilesException(String.format("File %s is not found", filePath));
            }
        }
        
    }
    public void performSystemChecks(){
        try{
            checkSystem();
        }
        catch (Exception e){
            exit(e.getMessage());
        }
    }
    public void exit(){
        System.out.println("Exiting Game...");
        System.exit(0);
    }
    public void exit(String error){
        System.err.println("System Error: "+error);
        System.out.println("Exiting Game...");
        System.exit(1);
    }
}
