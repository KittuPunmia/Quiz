package com.kittu.quiz;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quiz implements Parcelable {

    @SerializedName("quiz_details_id")
    @Expose
    private String quizDetailsId;
    @SerializedName("quiz_id")
    @Expose
    private String quizId;
    @SerializedName("quizquestionno")
    @Expose
    private String quizquestionno;
    @SerializedName("quizquestion")
    @Expose
    private String quizquestion;
    @SerializedName("quizoption1")
    @Expose
    private String quizoption1;
    @SerializedName("quizoption2")
    @Expose
    private String quizoption2;
    @SerializedName("quizoption3")
    @Expose
    private String quizoption3;
    @SerializedName("quizoption4")
    @Expose
    private String quizoption4;
    @SerializedName("quizans")
    @Expose
    private String quizans;

    /**
     * No args constructor for use in serialization
     *
     */
    public Quiz() {
    }

    /**
     *
     * @param quizoption4
     * @param quizoption3
     * @param quizans
     * @param quizoption2
     * @param quizquestion
     * @param quizDetailsId
     * @param quizoption1
     * @param quizId
     * @param quizquestionno
     */
    public Quiz(String quizDetailsId, String quizId, String quizquestionno, String quizquestion, String quizoption1, String quizoption2, String quizoption3, String quizoption4, String quizans) {
        super();
        this.quizDetailsId = quizDetailsId;
        this.quizId = quizId;
        this.quizquestionno = quizquestionno;
        this.quizquestion = quizquestion;
        this.quizoption1 = quizoption1;
        this.quizoption2 = quizoption2;
        this.quizoption3 = quizoption3;
        this.quizoption4 = quizoption4;
        this.quizans = quizans;
    }

    public String getQuizDetailsId() {
        return quizDetailsId;
    }

    public void setQuizDetailsId(String quizDetailsId) {
        this.quizDetailsId = quizDetailsId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getQuizquestionno() {
        return quizquestionno;
    }

    public void setQuizquestionno(String quizquestionno) {
        this.quizquestionno = quizquestionno;
    }

    public String getQuizquestion() {
        return quizquestion;
    }

    public void setQuizquestion(String quizquestion) {
        this.quizquestion = quizquestion;
    }

    public String getQuizoption1() {
        return quizoption1;
    }

    public void setQuizoption1(String quizoption1) {
        this.quizoption1 = quizoption1;
    }

    public String getQuizoption2() {
        return quizoption2;
    }

    public void setQuizoption2(String quizoption2) {
        this.quizoption2 = quizoption2;
    }

    public String getQuizoption3() {
        return quizoption3;
    }

    public void setQuizoption3(String quizoption3) {
        this.quizoption3 = quizoption3;
    }

    public String getQuizoption4() {
        return quizoption4;
    }

    public void setQuizoption4(String quizoption4) {
        this.quizoption4 = quizoption4;
    }

    public String getQuizans() {
        return quizans;
    }

    public void setQuizans(String quizans) {
        this.quizans = quizans;
    }


    protected Quiz(Parcel in) {
        quizDetailsId = in.readString();
        quizId = in.readString();
        quizquestionno = in.readString();
        quizquestion = in.readString();
        quizoption1 = in.readString();
        quizoption2 = in.readString();
        quizoption3 = in.readString();
        quizoption4 = in.readString();
        quizans = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quizDetailsId);
        dest.writeString(quizId);
        dest.writeString(quizquestionno);
        dest.writeString(quizquestion);
        dest.writeString(quizoption1);
        dest.writeString(quizoption2);
        dest.writeString(quizoption3);
        dest.writeString(quizoption4);
        dest.writeString(quizans);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Quiz> CREATOR = new Parcelable.Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };
}