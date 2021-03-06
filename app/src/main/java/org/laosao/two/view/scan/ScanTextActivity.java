package org.laosao.two.view.scan;

import android.os.Bundle;
import android.widget.TextView;

import org.laosao.two.R;
import org.laosao.two.model.Config;
import org.laosao.two.present.scan.ScanTextPresent;
import org.laosao.two.view.base.BaseActivity;
import org.laosao.two.view.iview.scan.IScanTextView;

import material.view.fab.FloatingActionButton;

/**
 * Created by Scout.Z on 2015/8/15.
 */
public class ScanTextActivity extends BaseActivity<ScanTextPresent> implements
		IScanTextView {
	private TextView mTvText;
	private FloatingActionButton mFabCopy;
	private String mResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

    @Override
    public ScanTextPresent createPersent() {
        return new ScanTextPresent(this, this);
    }

    @Override
    public int getContentView() {
        return R.layout.scan_activity_text;
    }

    @Override
	public void initView() {
		mTvText = (TextView) findViewById(R.id.tvResultContent);
		mFabCopy = (FloatingActionButton) findViewById(R.id.fabCopy);
	}

    @Override
    public void loadData() {
        mResult = getIntent().getStringExtra(Config.KEY_RESULT);
    }

    @Override
	public void setListener() {
		mFabCopy.setOnClickListener(this);
	}

	@Override
	public String getContent() {
		return mResult;
	}

	@Override
	public void setScanResult(String text) {
		mTvText.setText(text);
	}

	/**
	 * 0为文本，1为名片和Wifi
	 */
	@Override
	public void changeFabState(int state) {
		switch (state) {
			case 0:

				break;

			case 1:
				mFabCopy.setIconDrawable(getResources().getDrawable(R.mipmap.ic_add));
				break;

			case 2:
				mFabCopy.setIconDrawable(getResources().getDrawable(R.mipmap.ic_send));
				break;
		}
	}
}
