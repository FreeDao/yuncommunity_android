package com.yuncommunity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.yuncommunity.base.BaseMapActivity;
import com.yuncommunity.util.ETUtil;

/**
 * 选择地址
 * 
 * @author oldfeel
 * 
 */
public class SelectAddress extends BaseMapActivity implements OnClickListener {
	private EditText etContent;
	private Button btnUp, btnDown, btnRight, btnLeft;
	protected Point clickedPoint;
	private GeoCoder mSearch;
	private LatLng clickedLatLng;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentLayout(R.layout.select_address);
		initView();
		initListener();
	}

	private void initListener() {
		btnDown.setOnClickListener(this);
		btnLeft.setOnClickListener(this);
		btnRight.setOnClickListener(this);
		btnUp.setOnClickListener(this);
		mBaiduMap.setOnMapClickListener(new OnMapClickListener() {

			@Override
			public boolean onMapPoiClick(MapPoi arg0) {
				return false;
			}

			@Override
			public void onMapClick(LatLng ll) {
				clickedPoint = mBaiduMap.getProjection().toScreenLocation(ll);
				drawClicked();
			}
		});
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

			@Override
			public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
				if (result == null
						|| result.error != SearchResult.ERRORNO.NO_ERROR) {
					showToast("抱歉,未能找到结果");
				} else {
					showToast(result.getAddress());
					etContent.setText(result.getAddress());
				}
			}

			@Override
			public void onGetGeoCodeResult(GeoCodeResult arg0) {
			}
		});
	}

	/**
	 * 绘制被点击的点
	 */
	protected void drawClicked() {
		mBaiduMap.clear();
		clickedLatLng = mBaiduMap.getProjection().fromScreenLocation(
				clickedPoint);
		BitmapDescriptor bitmap = BitmapDescriptorFactory
				.fromResource(R.drawable.icon_gcoding);
		OverlayOptions options = new MarkerOptions().position(clickedLatLng)
				.icon(bitmap);
		mBaiduMap.addOverlay(options);
		mSearch.reverseGeoCode(new ReverseGeoCodeOption()
				.location(clickedLatLng));
	}

	private void initView() {
		etContent = getEditText(R.id.select_address_content);
		btnUp = (Button) findViewById(R.id.select_address_up);
		btnDown = getButton(R.id.select_address_down);
		btnRight = getButton(R.id.select_address_right);
		btnLeft = getButton(R.id.select_address_left);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.select_address, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_help:
			showHelp();
			break;
		case R.id.action_complete:
			complete();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void complete() {
		if (clickedLatLng == null) {
			showToast("请先在地图上选择地址");
			return;
		}
		onBackPressed();
	}

	/**
	 * 显示帮助
	 */
	private void showHelp() {
		new AlertDialog.Builder(SelectAddress.this).setTitle("帮助")
				.setMessage("点击地图选择位置,点击上,下,左,右做个按钮进行微调.")
				.setPositiveButton("确定", null).show();
	}

	@Override
	public void onClick(View v) {
		if (clickedPoint == null) {
			showToast("请先点击地图选择位置");
			return;
		}
		switch (v.getId()) {
		case R.id.select_address_down:
			clickedPoint.y++;
			break;
		case R.id.select_address_left:
			clickedPoint.x--;
			break;
		case R.id.select_address_right:
			clickedPoint.x++;
			break;
		case R.id.select_address_up:
			clickedPoint.y--;
			break;
		default:
			break;
		}
		drawClicked();
	}

	@Override
	public void onBackPressed() {
		if (clickedLatLng != null && !ETUtil.isEmpty(etContent)) {
			Intent intent = new Intent();
			intent.putExtra("lat", clickedLatLng.latitude);
			intent.putExtra("lon", clickedLatLng.longitude);
			intent.putExtra("address", getString(etContent));
			setResult(RESULT_OK, intent);
			finish();
		}
		super.onBackPressed();
	}
}
