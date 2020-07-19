package com.upgrad.mtb.beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
public class Movie {
    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "movie_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private int id;
    @Column( nullable = false)
    private String name;
    @Column( nullable = false)
    private String description;
    @Column( nullable = false)
    private Date releaseDate;
    @Column( nullable = false)
    private int duration;
    @Column( nullable = false)
    private String coverPhotoURL;
    @Column( nullable = false)
    private String trailerURL;

    @ManyToOne
    @JsonBackReference("language_movie")
    private Language language;

    @ManyToOne
    @JsonBackReference("status_movie")
    private Status status;

    @ManyToMany
    @JsonManagedReference("movie_theatre")
    List<Theatre> theatres;
/*
    @PreRemove
    public void onPreRemove(){
        this.setLanguage(null);
        this.setStatus(null);
    }*/

    public Movie(){}

    public Movie(String name, String description, Date releaseDate, int duration, String coverPhotoURL, String trailerURL, Language language, Status status, List<Theatre> theatres) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
        this.language = language;
        this.status = status;
        this.theatres = theatres;
    }

    public Movie(String name, String description, Date releaseDate, int duration, String coverPhotoURL, String trailerURL, Language language, Status status) {
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.coverPhotoURL = coverPhotoURL;
        this.trailerURL = trailerURL;
        this.language = language;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return duration == movie.duration &&
                name.equals(movie.name) &&
                description.equals(movie.description) &&
                releaseDate.equals(movie.releaseDate) &&
                coverPhotoURL.equals(movie.coverPhotoURL) &&
                trailerURL.equals(movie.trailerURL) &&
                language.equals(movie.language) &&
                status.equals(movie.status) &&
                theatres.equals(movie.theatres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, releaseDate, duration, coverPhotoURL, trailerURL, language, status, theatres);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                ", coverPhotoURL='" + coverPhotoURL + '\'' +
                ", trailerURL='" + trailerURL + '\'' +
                ", language=" + language +
                ", status=" + status +
                ", theatres=" + theatres +
                '}';
    }
}
