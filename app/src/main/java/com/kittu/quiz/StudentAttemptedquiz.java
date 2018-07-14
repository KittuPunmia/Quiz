package com.kittu.quiz;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentAttemptedquiz implements Parcelable {

    @SerializedName("quiz_marks_id")
    @Expose
    private String quizMarksId;
    @SerializedName("Quiz_id")
    @Expose
    private String quizId;
    @SerializedName("Student_id")
    @Expose
    private String studentId;
    @SerializedName("quiz_marks")
    @Expose
    private String quizMarks;
    @SerializedName("quiz_out_of")
    @Expose
    private String quizOutOf;

    /**
     * No args constructor for use in serialization
     *
     */
    public StudentAttemptedquiz() {
    }

    /**
     *
     * @param quizMarksId
     * @param studentId
     * @param quizMarks
     * @param quizOutOf
     * @param quizId
     */
    public StudentAttemptedquiz(String quizMarksId, String quizId, String studentId, String quizMarks, String quizOutOf) {
        super();
        this.quizMarksId = quizMarksId;
        this.quizId = quizId;
        this.studentId = studentId;
        this.quizMarks = quizMarks;
        this.quizOutOf = quizOutOf;
    }

    public String getQuizMarksId() {
        return quizMarksId;
    }

    public void setQuizMarksId(String quizMarksId) {
        this.quizMarksId = quizMarksId;
    }

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getQuizMarks() {
        return quizMarks;
    }

    public void setQuizMarks(String quizMarks) {
        this.quizMarks = quizMarks;
    }

    public String getQuizOutOf() {
        return quizOutOf;
    }

    public void setQuizOutOf(String quizOutOf) {
        this.quizOutOf = quizOutOf;
    }


    protected StudentAttemptedquiz(Parcel in) {
        quizMarksId = in.readString();
        quizId = in.readString();
        studentId = in.readString();
        quizMarks = in.readString();
        quizOutOf = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(quizMarksId);
        dest.writeString(quizId);
        dest.writeString(studentId);
        dest.writeString(quizMarks);
        dest.writeString(quizOutOf);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<StudentAttemptedquiz> CREATOR = new Parcelable.Creator<StudentAttemptedquiz>() {
        @Override
        public StudentAttemptedquiz createFromParcel(Parcel in) {
            return new StudentAttemptedquiz(in);
        }

        @Override
        public StudentAttemptedquiz[] newArray(int size) {
            return new StudentAttemptedquiz[size];
        }
    };
}