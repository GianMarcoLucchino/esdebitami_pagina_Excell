package it.esdebitamiretake.retake_ticket.repo;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import it.esdebitamiretake.retake_ticket.collection.Survey;
import it.esdebitamiretake.retake_ticket.collection.Survey.Status;

public abstract interface SurveyRepository extends SurveyCustomRepository, MongoRepository<Survey, String> {
	
	public abstract Survey findBySurveyId(String paramString);

	public abstract List<Survey> findByTemplateId(String paramString);

	@Query("{templateId:'?0', feedback: {$ne: null} }")
	public abstract List<Survey> findFeedbacksByTemplate(String paramString, Pageable paramPageable);

	@Query("{templateId:'?0', feedback: {$ne: null}, surveyDate:{$gte: ?1, $lt: ?2}  }")
	public abstract List<Survey> findFeedbacksByTemplateAndInterval(String paramString, Date paramDate1,
			Date paramDate2, Pageable paramPageable);

	@Query("{templateId:'?0', feedback: {$ne: null}, surveyDate:{$gte: ?1}  }")
	public abstract List<Survey> findFeedbacksByTemplateGteDate(String paramString, Date paramDate,
			Pageable paramPageable);

	@Query("{templateId:'?0', feedback: {$ne: null}, surveyDate:{$lt: ?1}  }")
	public abstract List<Survey> findFeedbacksByTemplateLtDate(String paramString, Date paramDate,
			Pageable paramPageable);

	@Query("{templateId:'?0', userId:'?1', feedback: {$ne: null} }")
	public abstract List<Survey> findFeedbacksByTemplateAndUser(String paramString1, String paramString2,
			Pageable paramPageable);

	@Query("{templateId:'?0', userId:'?1', feedback: {$ne: null}, surveyDate:{$gte: ?2, $lt: ?3}  }")
	public abstract List<Survey> findFeedbacksByTemplateAndUserAndInterval(String paramString1, String paramString2,
			Date paramDate1, Date paramDate2, Pageable paramPageable);

	@Query("{templateId:'?0', userId:'?1', feedback: {$ne: null}, surveyDate:{$gte: ?2}  }")
	public abstract List<Survey> findFeedbacksByTemplateAndUserGteDate(String paramString1, String paramString2,
			Date paramDate, Pageable paramPageable);

	@Query("{templateId:'?0', userId:'?1', feedback: {$ne: null}, surveyDate:{$lt: ?2}  }")
	public abstract List<Survey> findFeedbacksByTemplateAndUserLtDate(String paramString1, String paramString2,
			Date paramDate, Pageable paramPageable);
	
	@Query("{templateId:'?0'}")
	public abstract List<Survey> findSurveyByTemplateId(String theTemplateId);
	
	@Query("{ $and: [ {'templateId': { $in: ?0 } }, { 'surveyDate' : { $gte: ?1 }  }, { 'status' : ?2 } ] }")
	public abstract List<Survey> findSurveysHome(List<String> idsList, LocalDate nowDate, Status status, Pageable pageParam);
	
}