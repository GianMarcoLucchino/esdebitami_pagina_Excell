/*    */ package it.esdebitamiretake.retake_ticket.fe;

import it.esdebitamiretake.retake_ticket.collection.Survey;

/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SurveyExt
/*    */ {
/*    */   private String templateName;
/*    */   private Survey survey;
/*    */   
/*    */   public SurveyExt() {}
/*    */   
/*    */   public SurveyExt(String templateName, Survey survey)
/*    */   {
/* 16 */     this.templateName = templateName;
/* 17 */     this.survey = survey;
/*    */   }
/*    */   
/*    */   public String getTemplateName() {
/* 21 */     return this.templateName;
/*    */   }
/*    */   
/*    */   public void setTemplateName(String templateName) {
/* 25 */     this.templateName = templateName;
/*    */   }
/*    */   
/*    */   public Survey getSurvey() {
/* 29 */     return this.survey;
/*    */   }
/*    */   
/*    */   public void setSurvey(Survey survey) {
/* 33 */     this.survey = survey;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\SurveyExt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */