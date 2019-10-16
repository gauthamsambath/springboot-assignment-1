package com.stackroute.MuzixApplication.TrackService;

import com.stackroute.MuzixApplication.CustomEcxeptions.TrackNotFoundEcxeption;
import com.stackroute.MuzixApplication.CustomEcxeptions.UserAlreadyExistsEcxeption;
import com.stackroute.MuzixApplication.domain.Track;
import com.stackroute.MuzixApplication.repository.TrackRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TrackService
    {
        List<Track> trackList=new ArrayList<Track>(Arrays.asList(new Track("123","Billy jean","album by michael jackson")));
        public List<Track> getTrackList() {
            return trackRepository.findAll();
        }
        TrackRepository trackRepository;

        public TrackService(TrackRepository trackRepository) {
            this.trackRepository = trackRepository;
        }

        public Optional<Track> getTrackById(String id) throws TrackNotFoundEcxeption
            {
//                return trackList.stream().filter(t->t.getTrackId().equals(id)).findFirst().get();
                if(!trackRepository.existsById(id))
                    {
                        throw new TrackNotFoundEcxeption("The track you are trying to get is not present in the repository");
                    }
                return trackRepository.findById(id);
            }
        public Track addTrack(Track track) throws UserAlreadyExistsEcxeption {

//                trackList.add(track);
                if(trackRepository.existsById(track.getTrackId()))
                {
                    throw new UserAlreadyExistsEcxeption("User already exists");
                }

                Track savedTRack=trackRepository.save(track);
                if(savedTRack==null)
                {
                    throw new UserAlreadyExistsEcxeption("User already exists");
                }
                return track;

            }
        public void updateById(Track track,String id) throws TrackNotFoundEcxeption
            {
//                boolean b=false;
//                for (int i=0;i<trackList.size();i++)
//                    {
//                        Track track1=trackList.get(i);
//                        if(track1.getTrackId().equals(id))
//                            {
//
//                                trackList.set(i,track);
//                            }
//                    }
                if(!trackRepository.existsById(id))
                    {
                        throw new TrackNotFoundEcxeption("The track you are trying to Update is not present in the repository");
                    }

                        trackRepository.deleteById(id);
                        trackRepository.save(track);
            }
        public void deleteById(String id) throws TrackNotFoundEcxeption
            {
                if(!trackRepository.existsById(id))
                    {
                        throw new TrackNotFoundEcxeption("The track you are trying to Delete is not present in the repository");
                    }

                trackRepository.deleteById(id);


//                trackList.removeIf(t->t.getTrackId().equals(id));
            }
        public void completeDelete()
            {
                trackRepository.deleteAll();
                trackList.clear();
            }
        public List<Track> getTrackByComments(String comments)
            {
                return trackRepository.getTrackByComments(comments);
            }



    }
