package com.test.task.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user_sectors")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class UserSector {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sectors_sequence")
    @SequenceGenerator(name = "sectors_sequence", allocationSize = 1)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Long id;

    @Column(name = "username", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private String username;

    @Column(name = "agreed_to_terms", nullable = false)
    @EqualsAndHashCode.Include
    @ToString.Include
    private Boolean agreedToTerms;

    @OneToOne
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Session session;

    @ManyToMany
    @JoinTable(
            name = "user_sectors_to_sectors",
            joinColumns = @JoinColumn(name = "user_sector_id"),
            inverseJoinColumns = @JoinColumn(name = "sector_id"))
    private Set<Sector> sectors = new HashSet<>();
}
