package dd.app.computall.objects;


public class Desktop
{
    //declaring attributes for the desktop
    public long id;
    public String make;
    public String model;
    public int ram;
    public int storage;
    public double screen;
    public double price;
    public String cpu;
    public String towerType;
    public String coolingSystem;


    // creating object desktop
    public Desktop(String make, String model, int ram, int storage, double screen, double price, String cpu, String towerType, String coolingSystem) {
        this.model = model;
        this.make = make;

        this.ram = ram;
        this.storage = storage;
        this.screen = screen;
        this.price = price;
        this.cpu = cpu;
        this.towerType = towerType;
        this.coolingSystem = coolingSystem;

    }
    public Desktop(){
        this.make = "";
        this.model="";
        this.ram = 0;
        this.storage = 0;
        this.screen = 0;
        this.price = 0;
        this.cpu = "";
        this.towerType= "";
        this.coolingSystem= "";

    }

       @Override
    public String toString() {
        return "Desktop \n" +
                "ID: " + id + "\n"+
                "Make: " + make  + "\n"+
                "Model: " + model  + "\n"+
                "Ram: " + ram + "\n"+
                "Storage: " + storage + "\n"+
                "Screen: " + screen + "\n"+
                "Price: " + price + "\n"+
                "CPU: " + cpu  + "\n"+
                "Tower Type: " + towerType + "\n"+
                "Cooling System: " + coolingSystem;
    }
}
