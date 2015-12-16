package bps.go.id.mrhandsdroid.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by handi_000 on 6/29/2015.
 */
public class MrhandsListViewSelectedColorAdapter<T> extends BaseAdapter
{

        private Context context;
        private List<T> testList;
        private int selectedIndex;
        private int selectedColor;

        public MrhandsListViewSelectedColorAdapter(Context ctx, List<T> testList,int color)
        {
            selectedColor=color;
            this.context = ctx;
            this.testList = testList;
            selectedIndex = -1;
        }

        public void setSelectedIndex(int ind)
        {
            selectedIndex = ind;
            notifyDataSetChanged();
        }

        @Override
        public int getCount()
        {
            return testList.size();
        }

        @Override
        public Object getItem(int position)
        {
            return testList.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        private class ViewHolder
        {
            TextView tv;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View vi = convertView;
            ViewHolder holder;
            if(convertView == null)
            {
                vi = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, null);
                holder = new ViewHolder();

                holder.tv = (TextView) vi;

                vi.setTag(holder);
            }
            else
            {
                holder = (ViewHolder) vi.getTag();
            }

            if(selectedIndex!= -1 && position == selectedIndex)
            {
                holder.tv.setBackgroundColor(selectedColor);
            }
            else
            {
                holder.tv.setBackgroundColor(Color.TRANSPARENT);
            }
            holder.tv.setText("" + (position + 1) + " " + testList.get(position));

            return vi;
        }


}
