package com.kittu.quiz;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizAttemptedAdapter extends RecyclerView.Adapter<QuizAttemptedAdapter.QuizAttemptedViewHolder> {
    List<StudentAttemptedquiz> items=new ArrayList<>();
    private Context context;
    public QuizAttemptedAdapter(Context context, List<StudentAttemptedquiz> items){
        this.context = context;
        this.items = items;

    }

    @NonNull
    @Override
    public QuizAttemptedAdapter.QuizAttemptedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.quiz_row,parent,false);

        return new QuizAttemptedAdapter.QuizAttemptedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizAttemptedAdapter.QuizAttemptedViewHolder holder, int position) {
        StudentAttemptedquiz studentAttemptedquiz=items.get(position);
        String marks="Your Score is "+studentAttemptedquiz.getQuizMarks()+" Out of"+studentAttemptedquiz.getQuizOutOf();
        String quiz="Quiz No "+studentAttemptedquiz.getQuizId();
        holder.takeQuiz.setVisibility(View.INVISIBLE);
holder.tvQuiz.setText(quiz);
holder.tvQuizMarks.setText(marks);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class QuizAttemptedViewHolder extends RecyclerView.ViewHolder {
        TextView tvQuiz;
        TextView tvQuizMarks;
        Button takeQuiz;

        public QuizAttemptedViewHolder(View itemView) {
            super(itemView);
            tvQuiz=itemView.findViewById(R.id.tvQuiz);
            tvQuizMarks=itemView.findViewById(R.id.tvQuizMarks);
            takeQuiz=itemView.findViewById(R.id.takeQuiz);

        }
    }
}
