package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.VideoGame;
@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, Long> {

    VideoGame findVideoGameById(Long id);
    VideoGame findVideoGameByName(String name);
    void deleteVideoGameById(Long id);
}
