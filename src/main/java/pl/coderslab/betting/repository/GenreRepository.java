package pl.coderslab.betting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.betting.entity.Genre;


@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findGenreById(Long id);
    Genre findGenreByName(String name);
    void deleteGenreById(Long id);
}
