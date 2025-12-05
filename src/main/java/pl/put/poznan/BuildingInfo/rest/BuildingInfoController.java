package pl.put.poznan.BuildingInfo.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.BuildingInfo.logic.BuildingInfo;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String object,
                              @RequestParam(value="operacje") String[] transforms) {

        // log the parameters
        logger.debug(object);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        BuildingInfo transformer = new BuildingInfo(transforms);
        return transformer.transform(object);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] transforms) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(transforms));

        // perform the transformation, you should run your logic here, below is just a silly example
        BuildingInfo transformer = new BuildingInfo(transforms);
        return transformer.transform(text);
    }



}


