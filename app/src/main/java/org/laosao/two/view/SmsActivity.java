package org.laosao.two.view;

import android.os.Bundle;
import android.text.TextUtils;

import com.rengwuxian.materialedittext.MaterialEditText;

import org.laosao.two.R;
import org.laosao.two.model.Config;
import org.laosao.two.present.SmsPresent;
import org.laosao.two.view.base.BaseActivity;
import org.laosao.two.view.iview.ISmsView;

import material.view.fab.FloatingActionButton;

/**
 * Created by Scout.Z on 2015/8/12.
 */
public class SmsActivity extends BaseActivity<SmsPresent>
		implements ISmsView {
	private MaterialEditText mEtPhoneNumber;
	private MaterialEditText mEtContent;
	private FloatingActionButton mFabCreate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    @Override
    public SmsPresent createPersent() {
        return new SmsPresent(this, this);
    }

    @Override
    public int getContentView() {
        return R.layout.activity_sms;
    }

    @Override
	public void initView() {
		mEtPhoneNumber = (MaterialEditText) findViewById(R.id.etSmsPhone);
		mEtContent = (MaterialEditText) findViewById(R.id.etSmsContent);
		mFabCreate = (FloatingActionButton) findViewById(R.id.fabCreate);
	}

	@Override
	public void setListener() {
		mFabCreate.setOnClickListener(this);
	}

	@Override
	public String getContent() {
		String phone = mEtPhoneNumber.getText().toString();
		String content = mEtContent.getText().toString();
		if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(content)) {
			return null;
		}
		return "PhoneNumber：" + phone +
				Config.NEW_LINE +
				"Content：" + content;
	}

	@Override
	public void reset() {
		mEtContent.setText(Config.EMPTY_STR);
		mEtPhoneNumber.setText(Config.EMPTY_STR);
	}
}
