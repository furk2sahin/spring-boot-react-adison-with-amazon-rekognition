package com.furkannsahin.adison.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

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
    private String image;
    private String description;
}
