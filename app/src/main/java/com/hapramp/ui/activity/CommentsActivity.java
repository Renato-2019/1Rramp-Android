package com.hapramp.ui.activity;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hapramp.R;
import com.hapramp.analytics.AnalyticsParams;
import com.hapramp.analytics.AnalyticsUtil;
import com.hapramp.datamodels.CommentModel;
import com.hapramp.preferences.HaprampPreferenceManager;
import com.hapramp.steem.SteemCommentCreator;
import com.hapramp.steem.SteemReplyFetcher;
import com.hapramp.ui.adapters.CommentsAdapter;
import com.hapramp.utils.Constants;
import com.hapramp.utils.FontManager;
import com.hapramp.utils.ImageHandler;
import com.hapramp.utils.ViewItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ankit on 2/9/2018.
 */

public class CommentsActivity extends AppCompatActivity implements SteemCommentCreator.SteemCommentCreateCallback, SteemReplyFetcher.SteemReplyFetchCallback {
  @BindView(R.id.backBtn)
  TextView backBtn;
  @BindView(R.id.toolbar_container)
  RelativeLayout toolbarContainer;
  @BindView(R.id.noCommentsCaption)
  TextView noCommentsCaption;
  @BindView(R.id.commentsRecyclerView)
  RecyclerView commentsRecyclerView;
  @BindView(R.id.commentCreaterAvatar)
  ImageView commentCreaterAvatar;
  @BindView(R.id.commentInputBox)
  EditText commentInputBox;
  @BindView(R.id.sendButton)
  TextView sendButton;
  @BindView(R.id.commentInputContainer)
  RelativeLayout commentInputContainer;
  @BindView(R.id.shadow)
  ImageView shadow;
  @BindView(R.id.commentLoadingProgressBar)
  ProgressBar commentLoadingProgressBar;
  @BindView(R.id.commentLoadingProgressMessage)
  TextView commentLoadingProgressMessage;
  private CommentsAdapter commentsAdapter;
  private ViewItemDecoration viewItemDecoration;
  private ProgressDialog progressDialog;
  private Typeface typeface;
  private String postAuthor;
  private String postPermlink;
  private SteemCommentCreator steemCommentCreator;
  private SteemReplyFetcher steemReplyFetcher;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_comments_list);
    ButterKnife.bind(this);
    init();
    attachListeners();
    AnalyticsUtil.getInstance(this).setCurrentScreen(this, AnalyticsParams.SCREEN_COMMENT_PAGE, null);
  }

  private void init() {
    steemReplyFetcher = new SteemReplyFetcher(this);
    steemReplyFetcher.setSteemReplyFetchCallback(this);
    steemCommentCreator = new SteemCommentCreator();
    steemCommentCreator.setSteemCommentCreateCallback(this);
    //commentsList = getIntent().getExtras().getParcelableArrayList(Constants.EXTRAA_KEY_COMMENTS);
    postAuthor = getIntent().getExtras().getString(Constants.EXTRAA_KEY_POST_AUTHOR, "");
    postPermlink = getIntent().getExtras().getString(Constants.EXTRAA_KEY_POST_PERMLINK, "");
    steemReplyFetcher.requestReplyForPost(postAuthor, postPermlink);
    progressDialog = new ProgressDialog(this);
    typeface = FontManager.getInstance().getTypeFace(FontManager.FONT_MATERIAL);
    backBtn.setTypeface(typeface);
    sendButton.setTypeface(typeface);
    ImageHandler.loadCircularImage(this, commentCreaterAvatar,
      String.format(getResources().getString(R.string.steem_user_profile_pic_format),
        HaprampPreferenceManager.getInstance().getCurrentSteemUsername()));
    commentsAdapter = new CommentsAdapter(this);
    commentsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    Drawable drawable = ContextCompat.getDrawable(this, R.drawable.comment_item_divider_view);
    viewItemDecoration = new ViewItemDecoration(drawable);
    viewItemDecoration.setWantTopOffset(false, 0);
    commentsRecyclerView.addItemDecoration(viewItemDecoration);
    commentsRecyclerView.setAdapter(commentsAdapter);
  }

  private void attachListeners() {
    sendButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        postComment();
      }
    });
    backBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }

  @Override
  public void onCommentCreateProcessing() {
  }

  @Override
  public void onCommentCreated() {
    hideProgress();
  }

  @Override
  public void onCommentCreateFailed() {
    hideProgress();
  }

  private void hideProgress() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }

  private void postComment() {
    String cmnt = commentInputBox.getText().toString().trim();
    commentInputBox.setText("");
    if (cmnt.length() > 2) {
      steemCommentCreator.createComment(cmnt, postAuthor, postPermlink);
    } else {
      Toast.makeText(this, "Comment Too Short!!", Toast.LENGTH_LONG).show();
      return;
    }
//    SteemCommentModel steemCommentModel = new SteemCommentModel(
//      HaprampPreferenceManager.getInstance().getCurrentSteemUsername(),
//      cmnt, MomentsUtils.getCurrentTime(), 0,
//      String.format(getResources().getString(R.string.steem_user_profile_pic_format),
//        HaprampPreferenceManager.getInstance().getCurrentSteemUsername()));
//    AnalyticsUtil.logEvent(AnalyticsParams.EVENT_CREATE_COMMENT);
    //commentsViewModel.addComments(steemCommentModel, postPermlink);
  }

  @Override
  public void onReplyFetched(List<CommentModel> replies) {
    commentsAdapter.addComments((ArrayList<CommentModel>) replies);
    hideProgressInfo();
  }

  private void hideProgressInfo() {
    if (commentLoadingProgressBar != null) {
      commentLoadingProgressBar.setVisibility(View.GONE);
      commentLoadingProgressMessage.setVisibility(View.GONE);
    }
  }

  @Override
  public void onReplyFetchError() {

  }
}
