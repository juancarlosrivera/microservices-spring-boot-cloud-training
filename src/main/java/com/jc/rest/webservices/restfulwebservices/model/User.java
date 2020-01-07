package com.jc.rest.webservices.restfulwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(description = "This is my user model")
@Entity
public class User {

    @Id
    private int id;
    @Size(min = 2,
          message = "Name should have at least 2 characters")
    @ApiModelProperty(notes = "Name should have at least 2 characters")
    private String name;
    @Past
    @ApiModelProperty(notes = "Birthday should be in the past")
    private Date birthDate;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    //In old Spring boot version it was necessary
    protected User() {

    }

    public User(int id, String name, Date birthDate) {

        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Date getBirthDate() {

        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {

        return posts;
    }

    public void setPosts(List<Post> posts) {

        this.posts = posts;
    }

    @Override
    public String toString() {

        return "User{" + "id=" + id + ", name='" + name + '\'' + ", birthDate=" + birthDate + '}';
    }
}
