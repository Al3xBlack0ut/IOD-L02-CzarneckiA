package pl.put.poznan.BuildingInfo.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.BuildingInfo.logic.BuildingInfo;
import pl.put.poznan.BuildingInfo.data.structure.Location;
import java.util.Arrays;
import java.util.ArrayList;


@RestController
@RequestMapping("/building/{id}")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private BuildingInfo baza=new BuildingInfo();
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public float get(@PathVariable int id,
                              @RequestParam(value="operacje") String transform) {

        // log the parameters
        logger.debug(Integer.toString(id));
        logger.debug(transform);

        // perform the transformation, you should run your logic here, below is just a silly example
        //BuildingInfo transformer = new BuildingInfo(id);
        return baza.transform(id,transform);
        //return transformer.transform(transforms);
    }

    @RequestMapping(method = RequestMethod.POST, consumes="application/json",produces = "application/json")
    public void post(@PathVariable int id,
                      @RequestBody Location [] locations) {

        // log the parameters
        logger.debug(Integer.toString(id));
        //logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        //BuildingInfo transformer = new BuildingInfo(id);
        //return transformer.transform("Asd");
        baza.insert(locations);
    }



}


