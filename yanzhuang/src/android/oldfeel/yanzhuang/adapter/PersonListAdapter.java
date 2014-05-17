package android.oldfeel.yanzhuang.adapter;

import java.util.List;

import android.content.Context;
import android.oldfeel.yanzhuang.R;
import android.oldfeel.yanzhuang.base.BaseBaseAdapter;
import android.oldfeel.yanzhuang.item.InformationListItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 个人服务列表适配器
 * 
 * @author oldfeel
 * 
 */
public class PersonListAdapter extends BaseBaseAdapter<InformationListItem> {

	public PersonListAdapter(Context context) {
		super(context);
	}

	@Override
	public View getView(int position, View view) {
		InformationListItem item = getItem(position);
		view = inflater.inflate(R.layout.personal_list_item, null);
		ImageView ivImage = getImageView(view, R.id.personal_list_item_image);
		TextView tvTitle = getTextView(view, R.id.personal_list_item_title);
		TextView tvDesc = getTextView(view, R.id.personal_list_item_desc);
		TextView tvEvaluation = getTextView(view,
				R.id.personal_list_item_evaluation);
		TextView tvTime = getTextView(view, R.id.personal_list_item_time);
		imageLoader.displayImage(item.getImage(), ivImage, options);
		tvTitle.setText(item.getTitle());
		tvDesc.setText(item.getDescription());
		tvEvaluation.setText(String.valueOf(item.getEvaluation()));
		tvTime.setText(item.getTime());
		return view;
	}

	@Override
	public void addResult(int page, String result) {
		super.addResult(page, result);
		List<InformationListItem> list = new Gson().fromJson(array,
				new TypeToken<List<InformationListItem>>() {
				}.getType());
		addAll(list);
	}

}
