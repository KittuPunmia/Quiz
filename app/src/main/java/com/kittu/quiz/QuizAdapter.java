package com.kittu.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizViewHolder>{
 ArrayList<Quizlist> items=new ArrayList<>();
private Context context;
public QuizAdapter(Context context, ArrayList<Quizlist> items){
    this.context = context;
    this.items = items;

}
    @Override
    public QuizViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View v=inflater.inflate(R.layout.quiz_row,parent,false);

    return new QuizViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final QuizViewHolder holder, final int position) {
String a= "Quiz ".concat(items.get(position).getQuizId());
        String b= "Total Marks ".concat(items.get(position).getQuizQuestionsNo());

        holder.tvQuiz.setText(a);
        holder.tvQuizMarks.setText(b);
holder.takeQuiz.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(context,QuizActivity.class);
i.putExtra("Pos",position);
        QuizAdapter.this.context.startActivity(i);

    }
});
        //holder.tvQuizMarks.setText(item.getQuizquestion());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class QuizViewHolder extends RecyclerView.ViewHolder {
    TextView tvQuiz;
    TextView tvQuizMarks;
    Button takeQuiz;
        public QuizViewHolder(View itemView) {
            super(itemView);
            tvQuiz=itemView.findViewById(R.id.tvQuiz);
            tvQuizMarks=itemView.findViewById(R.id.tvQuizMarks);
            takeQuiz=itemView.findViewById(R.id.takeQuiz);

        }
    }
}
