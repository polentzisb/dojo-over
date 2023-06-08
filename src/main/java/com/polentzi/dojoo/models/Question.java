package com.polentzi.dojoo.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="questions")
public class Question {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    @OneToMany(mappedBy="question", fetch = FetchType.LAZY)
    private List<Answer> answers;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "questions_tags", 
            joinColumns = @JoinColumn(name = "question_id"), 
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    
    private List<Tag> tags;
    
    @Transient
	@Pattern(regexp="^(([a-zA-Z\\\\s])+$|([a-zA-Z\\\\s]+,)[a-zA-Z\\\\s]+){1,2}$",message="Tags must be separated by commas, max 3")
	private String tagName;
    
    public Question () {
    	
    }
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	public Question(Long id, String questionText, List<Tag> tags) {
		this.id = id;
		this.questionText = questionText;
		this.tags = tags;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String question) {
		this.questionText = question;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String[] splitTags() {
		return this.tagName.split("\\s*,\\s*");
	}
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.setUpdatedAt(new Date());
    }
	public List<Answer> getAnswers() {
		
		return answers;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

    
}