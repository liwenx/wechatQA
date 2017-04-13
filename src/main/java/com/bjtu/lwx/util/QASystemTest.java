package com.bjtu.lwx.util;

import java.util.List;

import org.apdplat.qa.SharedQuestionAnsweringSystem;
import org.apdplat.qa.model.CandidateAnswer;
import org.apdplat.qa.model.Question;

public class QASystemTest {
	
	//需要JDK8
	public static void main(String[] args) {
		String questionStr = "APDPlat的作者是谁？";
		Question question = SharedQuestionAnsweringSystem.getInstance().answerQuestion(questionStr);
		if (question != null) {
		    List<CandidateAnswer> candidateAnswers = question.getAllCandidateAnswer();
		    int i=1;
		    for(CandidateAnswer candidateAnswer : candidateAnswers){
		        System.out.println((i++)+"、"+candidateAnswer.getAnswer()+":"+candidateAnswer.getScore());
		    }
		}
	}

}
