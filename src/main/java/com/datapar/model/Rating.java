package com.datapar.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Rating")
@Table(name = "rating", schema = "rating")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Email
    @NotBlank
    @Column(name = "email", unique = true)
    private String email;

    @Min(1)
    @Max(5)
    @Column(name = "score")
    private Integer score;

    @Column(name = "comments")
    private String comments;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "contact_time")
    private String contactTime;

    @Column(name = "contact_request")
    private Boolean contactRequest;
}

