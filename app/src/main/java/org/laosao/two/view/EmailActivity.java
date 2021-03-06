package org.laosao.two.view;

import android.os.Bundle;
import android.text.TextUtils;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.laosao.two.R;
import org.laosao.two.model.Config;
import org.laosao.two.present.EmailPresent;
import org.laosao.two.view.base.BaseActivity;
import org.laosao.two.view.iview.IEmailView;

import material.view.fab.FloatingActionButton;

/**
 * Created by Scout.Z on 2015/8/12.
 */
public class EmailActivity extends BaseActivity<EmailPresent>
		implements IEmailView {
	private MaterialEditText mEtAddress;
	private MaterialEditText mEtProject;
	private MaterialEditText mEtContent;
	private FloatingActionButton mFabCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    @Override
    public EmailPresent createPersent() {
        return new EmailPresent(this, this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_email;
    }

    @Override
	public void reset() {
		mEtAddress.setText(Config.EMPTY_STR);
		mEtProject.setText(Config.EMPTY_STR);
		mEtContent.setText(Config.EMPTY_STR);
	}

	@Override
	public void initView() {
		mEtAddress = (MaterialEditText) findViewById(R.id.etAddress);
		mEtProject = (MaterialEditText) findViewById(R.id.etProject);
		mEtContent = (MaterialEditText) findViewById(R.id.etCont);
		mFabCreate = (FloatingActionButton) findViewById(R.id.fabCreate);
	}


    @Override
	public void setListener() {
		mFabCreate.setOnClickListener(this);
	}

	@Override
	public String getContent() {
		String address = mEtAddress.getText().toString();
		String subject = mEtProject.getText().toString();
		String content = mEtContent.getText().toString();
		if (TextUtils.isEmpty(address) || TextUtils.isEmpty(subject)
				|| TextUtils.isEmpty(content)) {
			return null;
		}
		return "EmailAddress：" + address +
				Config.NEW_LINE +
				"Subject：" + subject
				+ Config.NEW_LINE +
				"Content：" + content;
	}
}
