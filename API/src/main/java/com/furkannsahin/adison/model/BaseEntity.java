package com.furkannsahin.adison.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @CreationTimestamp
    private Date createDate;

    @Lob
    @Type(type = "text")
    private String image;
    private String description;
}
