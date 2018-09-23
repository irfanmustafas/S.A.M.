package com.estimote.proximity.estimote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.estimote.proximity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

//
// Running into any issues? Drop us an email to: contact@estimote.com
//

public class ProximityContentAdapter extends BaseAdapter {


    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

    private Context context;

    public ProximityContentAdapter(Context context) {
        this.context = context;
    }

    private List<ProximityContent> nearbyContent = new ArrayList<>();

    public void setNearbyContent(List<ProximityContent> nearbyContent) {
        this.nearbyContent = nearbyContent;
    }

    @Override
    public int getCount() {
        return nearbyContent.size();
    }

    @Override
    public Object getItem(int position) {
        return nearbyContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;

            convertView = inflater.inflate(R.layout.content_view, parent, false);
        }

        ProximityContent content = nearbyContent.get(position);

        TextView subtitle = convertView.findViewById(R.id.subtitle);
        TextView title = convertView.findViewById(R.id.title);

        if (content.getTitle().equals("ice")) {
            title.setText("Mrs.Jones");
            subtitle.setText("E-223");
            myRef.child("Period 1").child("Pranit").setValue("present");
        }

        if (content.getTitle().equals("blueberry")) {
            title.setText("Mr.Drage");
            subtitle.setText("92");
            myRef.child("Period 2").child("Pranit").setValue("present");
        }

        if (content.getTitle().equals("coconut")) {
            title.setText("Mr. Clevenger");
            subtitle.setText("34");
            myRef.child("Period 3").child("Pranit").setValue("present");
        }



        //title.setText(content.getTitle());
        //subtitle.setText(content.getSubtitle());

        convertView.setBackgroundColor(Utils.getEstimoteColor(content.getTitle()));

        return convertView;
    }
}
