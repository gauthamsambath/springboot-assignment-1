package com.stackroute.MuzixApplication.trackcontroller;

import com.stackroute.MuzixApplication.CustomEcxeptions.TrackNotFoundEcxeption;
import com.stackroute.MuzixApplication.CustomEcxeptions.UserAlreadyExistsEcxeption;
import com.stackroute.MuzixApplication.TrackService.TrackService;
import com.stackroute.MuzixApplication.domain.Track;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController(value = "/api")
public class TrackController
{
    TrackService trackService;

    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @RequestMapping("/track")
    public List<Track> getAllTracks()
        {
            return trackService.getTrackList();
        }
    @RequestMapping("/track/{id}")
    public ResponseEntity<?> getTrackById(@PathVariable String id)
         {
                    ResponseEntity responseEntity;
                    try
                        {
                            trackService.getTrackById(id);
                            responseEntity=new ResponseEntity<Optional<Track>>(trackService.getTrackById(id),HttpStatus.CREATED);
                        }
                    catch (Exception ex)
                        {
                            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
                        }
                    return responseEntity;
        }
    @RequestMapping(value = "/track",method = RequestMethod.POST)
    public ResponseEntity<?> addTrack(@RequestBody Track track)
        {
//            trackService.addTrack(track);
            ResponseEntity responseEntity;
            try
            {
                trackService.addTrack(track);
                responseEntity=new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);
            }
            catch (UserAlreadyExistsEcxeption ex)
            {
                responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            }
            return responseEntity;
        }
    @RequestMapping(value = "/track/{id}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateByID(@RequestBody Track track,@PathVariable String id)
        {
            ResponseEntity responseEntity;
            try
            {
                trackService.updateById(track,id);
                responseEntity=new ResponseEntity<String>("Successfully Updated",HttpStatus.CREATED);
            }
            catch (Exception ex)
            {
                responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
            }
            return responseEntity;
        }
    @RequestMapping(value="/track/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable String id) {

        ResponseEntity responseEntity;
        try
        {
            trackService.deleteById(id);
            responseEntity=new ResponseEntity<String>("Successfully deleted",HttpStatus.CREATED);
        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
        }
    @RequestMapping(value = "/track/allclear",method = RequestMethod.DELETE)
    public void allClear()
        {
            trackService.completeDelete();
        }
    @RequestMapping(value = "/track/comments/{comments}",method = RequestMethod.GET)
    public List<Track> getTrackByComments(@PathVariable String comments)
        {
            return trackService.getTrackByComments(comments);
        }



}
