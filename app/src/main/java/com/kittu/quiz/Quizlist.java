package com.kittu.quiz;


public class Quizlist {

    private String quizId;
    private String quizQuestionsNo;

    /**
     * No args constructor for use in serialization
     *
     */
    public Quizlist() {
    }

    public Quizlist(String quizId, String quizQuestionsNo) {
        super();
        this.quizId = quizId;
        this.quizQuestionsNo = quizQuestionsNo;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuizQuestionsNo() {
        return quizQuestionsNo;
    }

    public void setQuizQuestionsNo(String quizQuestionsNo) {
        this.quizQuestionsNo = quizQuestionsNo;
    }

}