import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;


/**
 * <p>The Main Game
 * <p> Now introducing our version of “Plants vs. Zombies”, Veggie Raid! 
 * <p> Still including your favourite characters you know and love like Peashooter and Wallnut, 
 * our grid-base version includes a wide variety of plants and zombies, which are two of our main superclasses, 
 * giving the player access to all the amazing characters the original game may not have.
 * <p> The main story of the game is that zombies have taken over the world, and grandma is the only person left alive. 
 * Since she takes care of her plants so well everyday, her plants have decided to protect her from these evil zombies.
 * <p> Other superclasses in our game include the PlantCard, which is the visual shown on our conveyor belt within the game, 
 * and lets you click and use the plant that is linked to that card, 
 * and Projectile, the superclass used for our projectiles such as Boomerang and SnowPea.
 * <p> Our Plant superclass consists of different types of plants such as the CherryBomb, Bloomerrang, Peashooter, Torchwood, Walnut, FrostPea, and FirePea. 
 * You can also access all the different plants’ information by clicking the information button located on the top right in the menu, 
 * which will take you to “Plant Information”.
 * <p> Our zombie class consists of different zombies that have unique characteristics such as the Zomboni, which can wipe out plants, 
 * the Newspaper zombie, which runs faster after losing its paper, and many more.
 * <p> To add on, our version of the iconic game includes mini games! Available to play after completing the main game, 
 * our mini games consist of different variations of the original with little twists such as “Spikeweeds and Wallnuts versus zombies”.
 * <p> Although it is only available after completing the game, we created a special hidden feature just for Mr.Cohen for when you play a regular game, 
 * you can press the “w” key to immediately win the game, which then allows you access to the minigames.
 * <p> In conclusion, we hope you are able to enjoy our Veggie Raid by having the nostalgic feeling of “Plants vs Zombies”.
 * <p> Important Features:
 * <ul>
 * <li> "Plant Information” page is given in the menu
 * <li> Press “w” key while playing a regular game to automatically win and gain access to minigames
 * <li> Projectile acceleration from Boomerang
 * </ul>
 * <p> Credits:
 * <p> StoryBoard:
 * <ul>
 * <li> Among us: https://imgur.com/gallery/51txNFh
 * <li> Desert: https://www.vecteezy.com/vector-art/224422-vector-desert-landscape-illustration
 * <li> Queen: https://www.lifesizecustomcutouts.com/Queen-Elizabeth-II-Yellow-Dress-with-Sun-Hat-Cardboard-Cutout
 * <li> Dance flower:  https://twitter.com/zippyartz/status/1277079849706741760 
 * </ul>
 * <p> Sprite:
 * <ul>
 * <li> Fire: https://www.freepik.com/premium-vector/fire-flame-pixel-art-animation-sprite-frames-8bit_20821166.htm
 * <li> Zombie:
 * <ul> 
 * <li> bucket: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/138650/
 * <li> cone: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/138662/?source=genre
 * <li> basic: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/138563/
 * <li> flag: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/155191/
 * <li> zomboni: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/139834/
 * <li> newspaper: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/138823/
 * </ul>
 * </ul>
 * <p> Plants:
 * <ul>
 * <li> Bloomerang: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/138552/
 * <li> Torch Wood: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/138518/ 
 * <li> Potato Mine: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/84688/
 * <li> Spike Weed: https://www.spriters-resource.com/fullview/120366/
 * <li> Wallnut: https://www.spriters-resource.com/ds_dsi/pvszds/sheet/84684/ 
 * <li> Peashooter: https://www.spriters-resource.com/custom_edited/plantsvszombiescustoms/sheet/163790/
 * <li> Double Peashooter, Frost Peashooter, Fire Peashooter: https://www.spriters-resource.com/custom_edited/plantsvszombiescustoms/sheet/161605/ 
 * <li> Cherry Bomb:  https://www.spriters-resource.com/ds_dsi/pvszds/sheet/84683/
 * </ul>
 * <p> Sounds: https://www.youtube.com/
 * <p> Code: SuperSmoothMover class borrowed from Mr. Cohen's Vehicle simulation project



 * @author (Michael Chen & Daniel Chung) 
 * @version (June 22, 2022)
 */
public class MyWorld extends World
{
    private boolean isPlantSelected; // is a plant card plantSelected from the convoyerbelt or not
    private int placeX, placeY; // x and y coordinate to place the plantSelected plant
    private String currentPlantSelected; // String representing the name of the plant
    private PlantCard currentCard; // current plant card plantSelected 
    private Queue<PlantCard> plantHolder;
    private boolean isShovelSelected; // is the shovel selected
    // spawn plant purposes
    private ArrayList<Integer> plantSelect = new ArrayList<Integer>();
    private int plantSpawnCounter = 200;
    private int[] plantRatios = new int[10]; // change this when new plant is added
    // spawn zombie purposes
    private ArrayList<Integer> zombieSelect = new ArrayList<Integer>();
    private int zombieSpawnCounter = 200, zombieSpawnRate = 300; // spawn rate 
    private int[] zombieRatios = new int[5]; // change this when new zombie is added
    private int numWaves = 5, currentWave = 0; // number of waves in total and current wave number
    private int[] zombieNums = new int[numWaves]; // number of zombies per wave
    private int zombieSpawned = 0; // track of how many zombies spawned per wave
    
    private int mode; // 0 easy, 1 mediocre, 2 hard
    private int miniGame = -1; // which mini game
    
    private Grid grid[][] = new Grid[10][5]; // 2d array representing grid
    
    // sounds
    private int pCounter = 0;
    private GreenfootSound backgroundS;
    private GreenfootSound plantings[] = new GreenfootSound[2];
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     * @param mode - difficulty 0/1/2
     * @param miniGame - miniGame 0/1
     */
    public MyWorld(int mode, int miniGame)
    {    
        super(1000, 600, 1, false); 
        this.miniGame = miniGame;
        int dx = 78, dy = 89;
        // initialize 2d array representing grid
        for (int i=0; i<10; i++){
            for (int j=0; j<5; j++){
                grid[i][j] = new Grid();
                addObject(grid[i][j], 236 + i * dx, 140 + j * dy);
            }
        }
        // plant spawn ratios 
        if (miniGame == -1 || miniGame == -2){
            plantRatios[0] = 3; // peashooter ratio
            plantRatios[1] = 2; // doublepea ratio
            plantRatios[2] = 1; // walnut ratio
            plantRatios[3] = 1; // snowpea ratio
            plantRatios[4] = 2; // firepea ratio
            plantRatios[5] = 2; // mine ratio
            plantRatios[6] = 2; // bloomerrang ratio
            plantRatios[7] = 2; // spike ratio
            plantRatios[8] = 1; // cherrybomb ratio
            plantRatios[9] = 1; // torchwood ratio
        }
        else if (miniGame == 0){
            plantRatios[0] = 0; // peashooter ratio
            plantRatios[1] = 0; // doublepea ratio
            plantRatios[2] = 2; // walnut ratio
            plantRatios[3] = 0; // snowpea ratio
            plantRatios[4] = 0; // firepea ratio
            plantRatios[5] = 0; // mine ratio
            plantRatios[6] = 0; // bloomerrang ratio
            plantRatios[7] = 4; // spike ratio
            plantRatios[8] = 0; // cherrybomb ratio
            plantRatios[9] = 0; // torchwood ratio
        }
        else{
            plantRatios[0] = 0; // peashooter ratio
            plantRatios[1] = 0; // doublepea ratio
            plantRatios[2] = 2; // walnut ratio
            plantRatios[3] = 0; // snowpea ratio
            plantRatios[4] = 0; // firepea ratio
            plantRatios[5] = 4; // mine ratio
            plantRatios[6] = 0; // bloomerrang ratio
            plantRatios[7] = 0; // spike ratio
            plantRatios[8] = 0; // cherrybomb ratio
            plantRatios[9] = 0; // torchwood ratio
        }
        // zombie spawn ratios
        zombieRatios[0] = 1; // normal zombie ratio
        zombieRatios[1] = 0; // cone zombie ratio
        zombieRatios[2] = 0; // bucket zombie ratio
        zombieRatios[3] = 0; // newspaper zombie ratio
        zombieRatios[4] = 0; // zomboni ratio
        
        this.mode = mode;
        
        // total zombie spawn per wave
        if(mode == 0) {
            zombieNums[0] = 10; // 1st wave
            zombieNums[1] = 10; // 2nd wave
            zombieNums[2] = 25; // 3rd wave
            zombieNums[3] = 25; // 4th wave
            zombieNums[4] = 25; // 5th wave
        } else if(mode == 1) {
            zombieNums[0] = 10;
            zombieNums[1] = 10;
            zombieNums[2] = 25;
            zombieNums[3] = 35;
            zombieNums[4] = 35;
        } else if(mode == 2) {
            zombieNums[0] = 10;
            zombieNums[1] = 20;
            zombieNums[2] = 30;
            zombieNums[3] = 40;
            zombieNums[4] = 45;
        }
        // initialize plantSelect arraylist
        for (int i=0; i<plantRatios.length; i++){
            for (int j=0; j<plantRatios[i]; j++){
                plantSelect.add(i);
            }
        }
        
        // initialize zombieSelect arraylist
        for (int i=0; i<zombieRatios.length; i++){
            for (int j=0; j<zombieRatios[i]; j++){
                zombieSelect.add(i);
            }
        }
        plantHolder = new LinkedList<PlantCard>(); // queue holding plant cards
        // where to place the selected plant
        placeX = -1;
        placeY = -1;
        currentCard = null;
        addObject(new Conveyorbelt(), 100, 295);
        addObject(new Shovel(), 950, 566);
        setPaintOrder(Grid.class, Zombie.class, Projectile.class, Plant.class, PlantCard.class, Conveyorbelt.class);
        backgroundS = new GreenfootSound("background ambience.mp3");
        backgroundS.setVolume(30);
        plantings[0] = new GreenfootSound("planting.wav");
        plantings[1] = new GreenfootSound("planting.wav");
    }
    
    /**
     * Another constructor
     * 
     * @param mode - regular game 0/1/2 difficulty
     */
    public MyWorld(int mode){
        this(mode, -1);
    }
    
    public void started(){
        backgroundS.playLoop();
    }
    
    public void stopped(){
        backgroundS.stop();
    }
    
    /**
     * set the current selected card
     * 
     * @param card - a plant card
     */
    public void setSelectedCard(PlantCard card){
        currentCard = card;
    }
    
    /**
     * set is a plant selected
     * 
     * @param b - set to true/false
     */
    public void setisPlantSelected(boolean b){
        isPlantSelected = b;
    }
    
    /**
     * Add a new card to plant holder
     */
    public void addNewCard(){
        PlantCard c = chooseCard(plantSelect.get(Greenfoot.getRandomNumber(plantSelect.size())));
        plantHolder.add(c);
    }
    
    /**
     * Returns the plant card associated with the number given
     * 
     * @param n - number given
     * 
     * @return - Plant card associated with the number n
     */
    public PlantCard chooseCard(int n){
        if (n == 0){
            return new PeashooterCard(true);
        }
        else if (n == 1){
            return new DoublePeaCard(true);
        }
        else if (n == 2){
            return new WalnutCard(true);
        }
        else if (n == 3){
            return new FrostPeaCard(true);
        }
        else if (n == 4){
            return new FirePeaCard(true);
        }
        else if (n == 5){
            return new MineCard(true);
        }
        else if (n == 6){
            return new BloomerrangCard(true);
        }
        else if (n == 7){
            return new SpikeCard(true);
        }
        else if (n == 8){
            return new CherryBombCard(true);
        }
        else if (n == 9){
            return new TorchwoodCard(true);
        }
        return null;
    }
    
    /**
     * When a new plant is added make sure to modify this funtion
     * Adds a specific plant to the grid location
     * 
     * @param i - row number
     * @param j - column number
     */
    public void addActorFromString(int i, int j){
        // currentPlantSelected determines what specific plant to add
        if (currentPlantSelected == "Peashooter"){
            Peashooter p = new Peashooter();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "DoublePea"){
            DoublePea p = new DoublePea();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "Walnut"){
            Walnut p = new Walnut();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "FrostPea"){
            FrostPea p = new FrostPea();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "FirePea"){
            FirePea p = new FirePea();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "Mine"){
            Mine p = new Mine();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "Bloomerrang"){
            Bloomerrang p = new Bloomerrang();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "Spike"){
            Spike p = new Spike();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "CherryBomb"){
            CherryBomb p = new CherryBomb();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        else if (currentPlantSelected == "Torchwood"){
            Torchwood p = new Torchwood();
            addObject(p, placeX, placeY);
            grid[i][j].setPlant(p);
        }
        plantings[pCounter++].play();
        pCounter %= 2;
    }
    /**
     *  Set the x and y coordinate for the plant to be placed
     *  
     *  @param x - x coordinate
     *  @param y - y coordinate
     */
    public void setXY(int x, int y){
        placeX = x;
        placeY = y;
    }
    
    /**
     * set the current selected plant
     * 
     * @param plant - string representing the plant
     */
    public void setCurrentPlant(String plant){
        currentPlantSelected = plant;
    }
    
    /**
     * Returns the zombie associated with the given number
     * 
     * @param n - given number
     * 
     * @return the type of zombie associated with n
     */
    public Zombie getZombie(int n){
        Zombie z = null;
        if (n == 0){
            z = new Normal();
        }
        else if (n == 1){
            z = new Cone();
        }
        else if (n == 2){
            z = new Bucket();
        }
        else if (n == 3){
            z = new Newspaper();
        }
        else if (n == 4){
            z = new Zomboni();
        }
        return z;
    }
    
    /**
     * Spawns a zombie at given lane
     * 
     * @param lane - lane number to spawn zombie in
     * @param z - zombie to spawn
     */
    public void spawnZombieAtLane(int lane, Zombie z){
        if (lane == 0){
            addObject(z, 1000, 140);
        }
        else if (lane == 1){
            addObject(z, 1000, 225);
        }
        else if (lane == 2){
            addObject(z, 1000, 315);
        }
        else if (lane == 3){
            addObject(z, 1000, 405);
        }
        else{
            addObject(z, 1000, 500);
        }
    }
    
    /**
     * Set the ratio of zombies for each wave
     */
    public void setZombieRatios(){
        // IF EASY
        if(mode == 0) {
            if (currentWave==0){
                zombieRatios[0] = 1;
                zombieRatios[1] = 0;
                zombieRatios[2] = 0;
                zombieRatios[3] = 0;
                zombieRatios[4] = 0;
            }
            else if (currentWave==1){
                zombieRatios[0] = 2;
                zombieRatios[1] = 2;
                zombieRatios[2] = 1;
                zombieRatios[3] = 1;
                zombieRatios[4] = 0;
            }
            else if (currentWave==2){
                zombieRatios[0] = 1;
                zombieRatios[1] = 2;
                zombieRatios[2] = 0;
                zombieRatios[3] = 4;
                zombieRatios[4] = 0;
            } else if (currentWave==3) {
                zombieRatios[0] = 1;
                zombieRatios[1] = 2;
                zombieRatios[2] = 2;
                zombieRatios[3] = 5;
                zombieRatios[4] = 1;
            } else if (currentWave==4) {
                zombieRatios[0] = 1;
                zombieRatios[1] = 1;
                zombieRatios[2] = 1;
                zombieRatios[3] = 1;
                zombieRatios[4] = 2;
            }
            //IF MEDIOCRE
        } else if (mode==1) {
            if (currentWave==0){
                zombieRatios[0] = 1;
                zombieRatios[1] = 0;
                zombieRatios[2] = 0;
                zombieRatios[3] = 0;
                zombieRatios[4] = 0;
            }
            else if (currentWave==1){
                zombieRatios[0] = 2;
                zombieRatios[1] = 2;
                zombieRatios[2] = 1;
                zombieRatios[3] = 1;
                zombieRatios[4] = 0;
            } else if (currentWave==2){
                zombieRatios[0] = 0;
                zombieRatios[1] = 2;
                zombieRatios[2] = 1;
                zombieRatios[3] = 1;
                zombieRatios[4] = 1;
            } else if (currentWave==3) {
                zombieRatios[0] = 3;
                zombieRatios[1] = 0;
                zombieRatios[2] = 0;
                zombieRatios[3] = 2;
                zombieRatios[4] = 3;
            } else if (currentWave==4) {
                zombieRatios[0] = 0;
                zombieRatios[1] = 0;
                zombieRatios[2] = 3;
                zombieRatios[3] = 4;
                zombieRatios[4] = 5;
            }
            // IF HARD
        } else if (mode==2) {
            if (currentWave==0){
                zombieRatios[0] = 1;
                zombieRatios[1] = 0;
                zombieRatios[2] = 0;
                zombieRatios[3] = 0;
                zombieRatios[4] = 0;
            }
            else if (currentWave==1){
                zombieRatios[0] = 3;
                zombieRatios[1] = 3;
                zombieRatios[2] = 1;
                zombieRatios[3] = 1;
                zombieRatios[4] = 0;
            }
            else if (currentWave==2){
                zombieRatios[0] = 0;
                zombieRatios[1] = 0;
                zombieRatios[2] = 2;
                zombieRatios[3] = 3;
                zombieRatios[4] = 1;
            } else if (currentWave==3) {
                zombieRatios[0] = 2;
                zombieRatios[1] = 0;
                zombieRatios[2] = 0;
                zombieRatios[3] = 5;
                zombieRatios[4] = 2;
            } else if (currentWave==4) {
                zombieRatios[0] = 0;
                zombieRatios[1] = 0;
                zombieRatios[2] = 0;
                zombieRatios[3] = 2;
                zombieRatios[4] = 3;
            }
        }
        
        // reset the ratios
        for (int i=0; i<zombieRatios.length; i++){
            for (int j=0; j<zombieRatios[i]; j++){
                zombieSelect.add(i);
            }
        }
    }
    
    /**
     * The zombie spawner
     */
    public void zombieSpawner(){
        // if reached max waves
        if (currentWave >= numWaves - 1){
            // if all zombies are dead
            if (getObjects(Zombie.class).size() == 0){
                backgroundS.stop();
                Greenfoot.setWorld(new WinScreen()); // go to win screen
            }
            return;
        }
        else{
            // if reached max zombie spawned for this wave
            if (zombieSpawned == zombieNums[currentWave]){
                // go to the next wave
                zombieSpawned = 0;
                currentWave++;
                setZombieRatios();
                spawnZombieAtLane(Greenfoot.getRandomNumber(5), new Flag());
            }
            // spawn a zombie
            if (zombieSpawnCounter == zombieSpawnRate){
                int lane = Greenfoot.getRandomNumber(5);
                Zombie z = getZombie(zombieSelect.get(Greenfoot.getRandomNumber(zombieSelect.size())));
                spawnZombieAtLane(lane, z);
                zombieSpawnCounter = 0;
                zombieSpawned++;
                // determining when the next zombie comes
                // IF EASY
                if(mode==0) {
                    if(currentWave==0 || currentWave==1) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(200) + 300; // 300 to 500
                    } else if(currentWave==2 || currentWave==3) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(150) + 150; // 150 to 300
                    } else if(currentWave==4) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(100) + 80; // 80 to 180
                    }
                    
                } else if(mode==1) {
                    // IF MEDIOCRE
                    if(currentWave==0 || currentWave==1) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(150) + 200; // 200 to 450
                    } else if(currentWave==2 || currentWave==3) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(100) + 100; // 100 to 200
                    } else if(currentWave==4) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(100) + 50; // 50 to 150
                    }
                } else if(mode==2) {
                    // IF HARD
                    if(currentWave==0 || currentWave==1) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(150) + 100; // 100 to 250
                    } else if(currentWave==2 || currentWave==3) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(100) + 50; //  50 to 150
                    } else if(currentWave==4) {
                        zombieSpawnRate = Greenfoot.getRandomNumber(40);// 0 to 40
                    }
                }
            }
        }
        zombieSpawnCounter++;
    }
    
    /**
     * return true if the shovel is selected and false otherwise
     * 
     * @return - true/false
     */
    public boolean getShovelSelected(){
        return isShovelSelected;
    }
    
    /**
     * set if the shovel is selected to true/false
     * 
     * @param b - boolean to set to true/false
     */
    public void setShovelSelected(boolean b){
        isShovelSelected = b;
    }
    
    /**
     * Game Over go to lose screen
     */
    public void endGame(){
        backgroundS.stop();
        Greenfoot.setWorld(new LoseScreen(mode, miniGame));
    }
    
    public void act(){
        showText("Wave " + (currentWave+1), 600, 50);
        String key = Greenfoot.getKey();
        if (key != null && key.equals("w")){
            if (backgroundS.isPlaying()){
                backgroundS.stop();
            }
            Greenfoot.setWorld(new WinScreen());
            return;
        }
        // main code for grid managing
        for (int i=0; i<10; i++){
            for (int j=0; j<5; j++){
                // loop through each grid and check if it is clicked
                if (Greenfoot.mouseClicked(grid[i][j])){
                    // if a plant is selected
                    if (isPlantSelected){
                        // if the grid being clicked is free
                        if (!grid[i][j].getOccupied()){
                            // add the selected plant
                            setXY(grid[i][j].getX(), grid[i][j].getY());
                            addActorFromString(i, j);
                            placeX = -1;
                            placeY = -1;
                            isPlantSelected = false;
                            currentCard.setRemoved(true);
                            grid[i][j].setOccupied(true);
                        }
                        // not free
                        else{
                            // unselect the plant card
                            setXY(-1, -1);
                            if (isPlantSelected){
                                isPlantSelected = false;
                                currentCard.setSelected(false);
                            }
                        }
                    }
                    // shovel is selected
                    else if (isShovelSelected){
                        // if there is a plant remove it
                        if (grid[i][j].getOccupied()){
                            Plant p = grid[i][j].getPlant();
                            if (p != null){
                                p.damageMe(1000);
                            }
                        }
                        setShovelSelected(false);
                    }
                }
            }
        }
        // play music
        if (!backgroundS.isPlaying()){
            backgroundS.playLoop();
        }
        // add new plant card to the conveyorbelt
        if (plantSpawnCounter == 250){
            if (plantHolder.size() < 30){
                addNewCard();
            }
            if (getObjects(PlantCard.class).size() < 12){
                addObject(plantHolder.poll(), 100, 550);
            }
            plantSpawnCounter = 0;
        }
        plantSpawnCounter++;
        zombieSpawner();
    }
}