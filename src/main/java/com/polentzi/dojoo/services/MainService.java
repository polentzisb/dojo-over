package com.polentzi.dojoo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.polentzi.dojoo.models.Answer;
import com.polentzi.dojoo.models.Question;
import com.polentzi.dojoo.models.Tag;
import com.polentzi.dojoo.repositories.AnswerRepository;
import com.polentzi.dojoo.repositories.QuestionRepository;
import com.polentzi.dojoo.repositories.TagRepository;

@Service
public class MainService {
	private final AnswerRepository answerRepository;
	private final QuestionRepository questionRepository;
	private final TagRepository tagRepository;
	public MainService(AnswerRepository answerRepository,QuestionRepository questionRepository,TagRepository tagRepository) {
		this.answerRepository=answerRepository;
		this.questionRepository=questionRepository;
		this.tagRepository=tagRepository;
	}
	
	public List<Question> allQuestions() {
        return questionRepository.findAll();
    }
	public List<Tag> allTags() {
        return tagRepository.findAll();
    }
	public List<Answer> allAnswers() {
        return answerRepository.findAll();
    }
	public Question createQuestion(Question b) {
		ArrayList<Tag> questionTags = new ArrayList<Tag>();
		for (String subject : b.splitTags()) {
			Tag tagToBeAdded = this.tagRepository.findBySubject(subject).orElse(null);
			if (tagToBeAdded == null) {
				tagToBeAdded = new Tag(subject);
				this.tagRepository.save(tagToBeAdded);
			}
			if (!questionTags.contains(tagToBeAdded)) {
				questionTags.add(tagToBeAdded);
			}
		}
		b.setTags(questionTags);
        return questionRepository.save(b);
    }
	public Tag createTag(Tag b) {
        return tagRepository.save(b);
    }
	public Answer createAnswer(Answer b) {
        return answerRepository.save(b);
    }
	public Question findQuestion(Long id) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if(optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            return null;
        }
    }
	public List<Answer> findQuestionAnswers(Long id){
		Question question=questionRepository.findById(id).orElse(null);
		return question.getAnswers();
	}
	
	public List<Tag> findQuestionTags(Long id){
		Question question=questionRepository.findById(id).orElse(null);
		return question.getTags();
	}
	public Answer addAnswerToQuestion(Answer answer,Question question) {
		answer.setQuestion(question);
		return answerRepository.save(answer);
	}

}
