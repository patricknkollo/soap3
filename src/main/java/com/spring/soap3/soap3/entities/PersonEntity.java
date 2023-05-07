package com.spring.soap3.soap3.entities;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="Person") //je fais correspondre cette entite ci-dessous a la table person dans la base de donnees
// sans cela la liste que je demande de la BD sera vide vu qu'aucun tableau du nom de PersonEntity n'existe dans DB (voir  H2-console ou db.migration)
//Attention cette entite ci-presente est crée pour permettre a la classe Person (generée automatiquement par 'mvn compile' à partir de Person.xsd) de pouvoir correspondre avec
//la BD. Ainsi on a un code hybride SOAP(Person)/REST(PersonEntity) et les deux classes se mappent dans le service PersonRepository.
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personid;
    private String nom;
    private String prenom;
    private int age;
    private String ville;
}
