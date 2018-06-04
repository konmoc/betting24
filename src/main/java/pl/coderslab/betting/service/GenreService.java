package pl.coderslab.betting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.betting.entity.Genre;
import pl.coderslab.betting.entity.Player;
import pl.coderslab.betting.repository.GenreRepository;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public void saveGenre(Genre genre){
        genreRepository.save(genre);
    }

    public Genre findGenreById(Long id){
        return genreRepository.findGenreById(id);
    }

    public Genre findGenreByName(String name){
        return genreRepository.findGenreByName(name);
    }

    public void deleteGenreById(Long id){
        genreRepository.deleteGenreById(id);
    }

}
