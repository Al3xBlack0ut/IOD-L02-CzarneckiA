package pl.put.poznan.BuildingInfo.logic;
import pl.put.poznan.BuildingInfo.data.structure.LocationController;
import java.util.ArrayList;
/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class BuildingInfo {

    //private final String[] transforms;
    private LocationController lokacje=new LocationController();
    private int id;
    public BuildingInfo(){
        //this.id = id;
    }
    public void insert(int id,int ParentId,LocationController location)
    {
        if(ParentId!=-1)
            lokacje.addLocation(id,location.getName(id),ParentId,location.getArea(id),location.getCube(id),location.getLight(id),location.getHeating(id));
        else
        {
            lokacje.addLocation(id,location.getName(id));
            lokacje.setArea(id, location.getArea(id));
            lokacje.setCube(id, location.getCube(id));
            lokacje.setLight(id, location.getLight(id));
            lokacje.setHeating(id, location.getHeating(id));
        }

    }
    public float transform(int id,String polecenie){
        float wynik;


            if(polecenie=="getArea") {
                wynik = lokacje.getArea(id);
            }
            else if(polecenie=="getCube"){
                wynik = lokacje.getCube(id);
            }
            else if(polecenie=="getLight"){
                wynik = lokacje.getLight(id);
            }
            else{
                wynik = lokacje.getHeating(id);
            }
        return wynik;
    }
}
