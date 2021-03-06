/*    */ package it.esdebitamiretake.retake_ticket.fe;
/*    */ 
/*    */ import com.fasterxml.jackson.annotation.JsonProperty;

import it.esdebitamiretake.retake_ticket.collection.Reply;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReplyInfo
/*    */ {
/*    */   @JsonProperty("surveyId")
/*    */   String surveyId;
/*    */   @JsonProperty("reply")
/*    */   Reply reply;
/*    */   
/*    */   public ReplyInfo() {}
/*    */   
/*    */   public ReplyInfo(String userId, String surveyId, Reply reply)
/*    */   {
/* 21 */     this.surveyId = surveyId;
/* 22 */     this.reply = reply;
/*    */   }
/*    */   
/*    */   public String getSurveyId() {
/* 26 */     return this.surveyId;
/*    */   }
/*    */   
/*    */   public void setSurveyId(String surveyId) {
/* 30 */     this.surveyId = surveyId;
/*    */   }
/*    */   
/*    */   public Reply getReply() {
/* 34 */     return this.reply;
/*    */   }
/*    */   
/*    */   public void setReply(Reply reply) {
/* 38 */     this.reply = reply;
/*    */   }
/*    */ }


/* Location:              C:\Users\dercolano\Desktop\VVA\vas-template\vas-template-1.0.0.jar!\BOOT-INF\classes\it\isspa\vas\template\fe\ReplyInfo.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */