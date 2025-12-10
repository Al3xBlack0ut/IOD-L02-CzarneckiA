package pl.put.poznan.BuildingInfo.logic;
import pl.put.poznan.BuildingInfo.data.structure.Location;
import java.util.ArrayList;
/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class BuildingInfo {

    //private final String[] transforms;
    private ArrayList<Location> lokacje=new ArrayList<Location>();
    private int id;
    public BuildingInfo(){
        //this.id = id;
    }
    public void insert(Location [] locations)
    {
        for(Location i:locations)
        {
            lokacje.add(i);
        }
    }
    public float transform(int id,String polecenie){
        float wynik;
        Location przetwarzany=new Location();
        for(Location i:lokacje)
        {
            if(i.getId()==id)
            {
                przetwarzany=i;
                break;
            }
        }

            if(polecenie=="getArea") {
                wynik = przetwarzany.getArea();
            }
            else if(polecenie=="getCube"){
                wynik = przetwarzany.getCube();
            }
            else if(polecenie=="getLight"){
                wynik = przetwarzany.getLight();
            }
            else{
                wynik = przetwarzany.getHeating();
            }
        return wynik;
    }
}
