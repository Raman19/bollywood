package com.allstate.entities;

// lombock auto generate the getter and setter
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
@Data
public class Movie {
//  `id`        INT             NOT NULL    AUTO_INCREMENT,
//  `version`   INT             NOT NULL    DEFAULT 0,
//  `title`     VARCHAR(225)    NOT NULL,
//  `watched`   BOOLEAN         NULL        DEFAULT FALSE,
//  `rating`    VARCHAR(5)      NULL,
//  `released`  DATE            NULL,
//  `length`    INT             NULL,
//  `created`   TIMESTAMP       NULL        DEFAULT now(),
//  `modified`   TIMESTAMP       NULL        DEFAULT now(),

    private int id;
    private int version;
    private String title;
    private boolean watched;
    private String rating;
    private Date released;
    private int length;
    private Date created;
    private Date modified;

    @Id
    @GeneratedValue
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Version
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @CreationTimestamp
    public Date getCreated() {
        return created;
    }
    public void setCreated(Date created) {
        this.created = created;
    }

    @UpdateTimestamp
    public Date getModified() {
        return modified;
    }
    public void setModified(Date modified) {
        this.modified = modified;
    }
}
