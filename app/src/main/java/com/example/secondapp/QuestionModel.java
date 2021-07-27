package com.example.secondapp;

public class QuestionModel {

    private int id;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private int rightAnswer;
    private boolean isMedia;
    private String pathMedia;


    // Constructors

    public QuestionModel(int id, String question, String answer1, String answer2, String answer3, String answer4, int rightAnswer, boolean isMedia, String pathMedia) {
        this.id = id;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.rightAnswer = rightAnswer;
        this.isMedia = isMedia;
        this.pathMedia = pathMedia;
    }

    // None parameter constructor just in case could be use sometimes.

    public QuestionModel() {
    }

    // toString is necessary for printing the contents of a class object

    @Override
    public String toString() {
        return "QuestionModel{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answer1='" + answer1 + '\'' +
                ", answer2='" + answer2 + '\'' +
                ", answer3='" + answer3 + '\'' +
                ", answer4='" + answer4 + '\'' +
                ", rightAnswer=" + rightAnswer +
                ", isMedia=" + isMedia +
                ", pathMedia='" + pathMedia + '\'' +
                '}';
    }


    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }

    public String getAnswer4() {
        return answer4;
    }

    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public boolean isMedia() {
        return isMedia;
    }

    public void setMedia(boolean media) {
        isMedia = media;
    }

    public String getPathMedia() {
        return pathMedia;
    }

    public void setPathMedia(String pathMedia) {
        this.pathMedia = pathMedia;
    }
}
