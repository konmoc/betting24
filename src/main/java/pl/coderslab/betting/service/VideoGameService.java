
package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.VideoGame;
import pl.coderslab.betting.repository.VideoGameRepository;

@Service
public class VideoGameService {

    @Autowired
    VideoGameRepository videoGameRepository;

    public void saveVideoGame(VideoGame videoGame){
        videoGameRepository.save(videoGame);
    }

    public VideoGame findVideoGameById(Long id){
        return videoGameRepository.findVideoGameById(id);
    }

    public void deleteVideoGameWithId(Long id){
        videoGameRepository.deleteVideoGameById(id);
    }

}
