package com.revature.controllers;

import com.revature.models.Villain;
import com.revature.service.VillainService;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class VillainController {

    private static final VillainService villainService = new VillainService();

    private static final Logger logger = LoggerFactory.getLogger(VillainController.class);

    public static void handleGetAll(Context ctx){
        //Use Villain service to get all villains
        ArrayList<Villain> villains = villainService.getAllVillains();

        //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
        ctx.status(200);
        ctx.json(villains);
    }

    public static void handleGetOne(Context ctx){

        //empty integer to hold path parameter
        int id;
        try{
            //parse path parameter to integer
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in the path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }

        //Call Villain service to get villain
        Villain villain = villainService.getVillainById(id);

        if (villain != null){
            // Villain found
            //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(200);
            ctx.json(villain);
            logger.info("The following villain was found: " + villain);
        } else{
            //Villain not found, bad status
            ctx.status(404);
            logger.warn("Get one Villain failed");
        }
    }

    public static void handleCreate(Context ctx){

        //Context body will come in JSON, and must be converted to Java object
        Villain villain = ctx.bodyAsClass(Villain.class);

        //Call Villain service to create villain
        Villain returnedVillain = villainService.insertVillain(villain);

        if(returnedVillain != null){
            //Villain was created
            //Use the Context to send the status and the json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(201);
            ctx.json(returnedVillain);
            logger.info("The following villain was created: " + returnedVillain);
        } else {
            //Villain not created, bad status
            ctx.status(400);
            logger.warn("Villain creation failed");
        }
    }

    public static void handleDelete(Context ctx){

        //empty integer to hold path parameter
        int id;
        try{
            //parse path parameter to integer
            id = Integer.parseInt(ctx.pathParam("id"));
        }catch (NumberFormatException e){
            // This block running means they didn't have a valid integer in their path
            ctx.status(400);
            // Adding a return statement here because there's no point continuing with a bad int
            return;
        }


        if ( villainService.removeVillain(id) ){
            // This is good, it found the villain to be deleted
            //Use the Context to send the status response to http request
            ctx.status(200);
            logger.info("Villain was removed.");
        } else{
            //Villain not removed, bad status
            ctx.status(404);
            logger.warn("Villain removal failed");
        }

    }

    public static void handleUpdate(Context ctx){

        //Context body will come in JSON, and must be converted to Java object
        Villain villain = ctx.bodyAsClass(Villain.class);

        //Call Villain service to update villain
        Villain returnedVillain = villainService.updateVillain(villain);

        if(returnedVillain != null){
            //Villain was updated
            //Use the Context to send the status and json mapper to send the Java object as a JSON object as a response to http request
            ctx.status(201);
            ctx.json(returnedVillain);
            logger.info("The following villain was updated: " + returnedVillain);
        } else {
            //Villain not updated, bad status
            ctx.status(400);
            logger.warn("Villain update failed");
        }
    }
}
