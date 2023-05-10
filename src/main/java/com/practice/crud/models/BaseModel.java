package com.practice.crud.models;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

public class BaseModel {
	   @Id
	   private String id;
	   private boolean deleted;
	   @CreatedBy
	   protected String createdBy;
	   @CreatedDate
	   @Temporal(TemporalType.TIMESTAMP)
	   protected Date createdAt;
	   @LastModifiedBy
	   protected String updatedBy;
	   @LastModifiedDate
	   @Temporal(TemporalType.TIMESTAMP)
	   protected Date updatedAt;
	   @Version
	   protected Long version;

	   public String getId() {
	      return this.id;
	   }

	   public void setId(String id) {
	      this.id = id;
	   }

	   public String getCreatedBy() {
	      return this.createdBy;
	   }

	   public void setCreatedBy(String createdBy) {
	      this.createdBy = createdBy;
	   }

	   public Date getCreatedAt() {
	      return this.createdAt;
	   }

	   public void setCreatedAt(Date createdAt) {
	      this.createdAt = createdAt;
	   }

	   public String getUpdatedBy() {
	      return this.updatedBy;
	   }

	   public void setUpdatedBy(String updatedBy) {
	      this.updatedBy = updatedBy;
	   }

	   public Date getUpdatedAt() {
	      return this.updatedAt;
	   }

	   public void setUpdatedAt(Date updatedAt) {
	      this.updatedAt = updatedAt;
	   }

	   public Long getVersion() {
	      return this.version;
	   }

	   public void setVersion(Long version) {
	      this.version = version;
	   }

	   public boolean isDeleted() {
	      return this.deleted;
	   }

	   public void setDeleted(boolean deleted) {
	      this.deleted = deleted;
	   }
	}
