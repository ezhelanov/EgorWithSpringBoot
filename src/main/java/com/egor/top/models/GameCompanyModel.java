package com.egor.top.models;

import com.egor.top.mappedsuperclasses.AbstractEntertainmentModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class GameCompanyModel extends AbstractEntertainmentModel {

    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private Set<GameModel> games = new HashSet<>();


    public GameCompanyModel(String name) {
        super(name);
    }
}