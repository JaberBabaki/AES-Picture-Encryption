package android.simplify.contact.customcontrol;

import java.util.ArrayList;
import android.simplify.contact.G;
import android.simplify.contact.R;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;


public class AdapterDetilsTopic extends ArrayAdapter<ContactModel> {

    public AdapterDetilsTopic(ArrayList<ContactModel> array) {
        super(G.context, R.layout.item_faq, array);

    }


    private static class ViewHolder {

        NewControlTextView txtName;
        LinearLayout       layItem;
        CircularImageView  imgC;


        public ViewHolder(View view) {

            txtName = (NewControlTextView) view.findViewById(R.id.txt_title);
            imgC = (CircularImageView) view.findViewById(R.id.img_pro);
            //layItem = (LinearLayout) view.findViewById(R.id.lay_item);

        }


        public void fill(ArrayAdapter<ContactModel> adapter, final ContactModel item, int position) {
            txtName.setText(" " + item.name);
            if (item.photo != null) {
                imgC.setImageBitmap(item.photo);
            } else {
                imgC.setImageDrawable(G.context.getResources().getDrawable(R.drawable.user));
                // int id = G.context.getIdentifier("android.simplify.contact:drawable/ user.png", null, null);
                //  imgC.setImageBitmap(item.photo);

            }

        }
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        ContactModel item = getItem(position);
        if (convertView == null) {
            convertView = G.inflater.inflate(R.layout.item_faq, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.fill(this, item, position);
        Animation animation = AnimationUtils.loadAnimation(G.context,
                android.R.anim.slide_in_left);
        convertView.startAnimation(animation);
        return convertView;
    }
}