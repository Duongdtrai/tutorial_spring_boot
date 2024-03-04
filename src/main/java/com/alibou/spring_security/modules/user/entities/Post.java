package com.alibou.spring_security.modules.user.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @JsonGetter(value="author")
    public String getUserName() {
        return user.getEmail();
    }

    public Post(String title) {
        this.title = title;
    }
}
