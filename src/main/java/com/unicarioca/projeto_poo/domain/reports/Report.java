package com.unicarioca.projeto_poo.domain.reports;

import com.unicarioca.projeto_poo.domain.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue
    private UUID id;

    private Integer month;
    private Integer year;
    private String file_url;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client manager;
}
