package com.techyourchance.journeytodependencyinjection.screens.questionslist;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.techyourchance.journeytodependencyinjection.common.dependencyinjection.Service;
import com.techyourchance.journeytodependencyinjection.questions.FetchQuestionsListUseCase;
import com.techyourchance.journeytodependencyinjection.questions.Question;
import com.techyourchance.journeytodependencyinjection.screens.common.activities.BaseActivity;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.DialogsManager;
import com.techyourchance.journeytodependencyinjection.screens.common.dialogs.ServerErrorDialogFragment;
import com.techyourchance.journeytodependencyinjection.screens.common.mvcviews.ViewMvcFactory;
import com.techyourchance.journeytodependencyinjection.screens.questiondetails.QuestionDetailsActivity;

import java.util.List;

public class QuestionsListActivity extends BaseActivity implements
        FetchQuestionsListUseCase.Listener, QuestionsListViewMvc.Listener {

    private QuestionsListViewMvc mViewMvc;

    // private DialogsManager mDialogManager;
    @Service
    private FetchQuestionsListUseCase mFetchQuestionsListUseCase;
    @Service
    private DialogsManager mDialogsManager;
    @Service
    private ViewMvcFactory mViewMvcFactory;

    private static final int NUM_OF_QUESTIONS_TO_FETCH = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInjector().inject(this);

        mViewMvc = mViewMvcFactory.newInstance(QuestionsListViewMvc.class, null);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected void onStart() {
        super.onStart();
        mViewMvc.registerListener(this);
        mFetchQuestionsListUseCase.registerListener(this);
        mFetchQuestionsListUseCase.fetchLastActiveQuestionsAndNotify(NUM_OF_QUESTIONS_TO_FETCH);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mViewMvc.unregisterListener(this);
        mFetchQuestionsListUseCase.unregisterListener(this);
    }

    @Override
    public void onQuestionClicked(Question question) {
        QuestionDetailsActivity.start(QuestionsListActivity.this, question.getId());
    }

    @Override
    public void onFetchOfQuestionsSucceeded(List<Question> questions) {
        mViewMvc.bindQuestions(questions);
    }

    @Override
    public void onFetchOfQuestionsFailed() {
        mDialogsManager.showRetainedDialogWithId(ServerErrorDialogFragment.newInstance(), "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(ServerErrorDialogFragment.newInstance(), null)
                .commitAllowingStateLoss();
    }
}
