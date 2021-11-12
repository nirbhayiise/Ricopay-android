/*
 * Copyright (c) 2017. Truiton (http://www.truiton.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 * Mohit Gupt (https://github.com/mohitgupt)
 *
 */

package com.np.ricopayapp.adpt;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.np.ricopayapp.R;
import com.np.ricopayapp.responsemodels.cut_regist_list_resp.Datum;
import com.np.ricopayapp.services.Const;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.np.ricopayapp.services.Const.BASE_URL_IMG;

public class CustProfileListAdapter extends RecyclerView
        .Adapter<CustProfileListAdapter
        .DataObjectHolder> {
   Context cntext;

    Typeface sonictonicfonts;

    private static String LOG_TAG = "CategoryRecyclerViewAdapter";
    private List<Datum> mDataset;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
           {
        TextView cname,acc_no,amount,deposit,accty;
        LinearLayout lay1;
        //ImageView proimg;
        CircleImageView proimg;



        public DataObjectHolder(View itemView) {
            super(itemView);
            cname = (TextView) itemView.findViewById(R.id.cname);
            acc_no = (TextView) itemView.findViewById(R.id.acc_no);
            amount = (TextView) itemView.findViewById(R.id.amount);
            deposit = (TextView) itemView.findViewById(R.id.deposit);
            accty = (TextView) itemView.findViewById(R.id.accty);
            proimg = (CircleImageView) itemView.findViewById(R.id.proimg);

            lay1 = (LinearLayout) itemView.findViewById(R.id.lay1);

          //  deletefaultItem = (LinearLayout) itemView.findViewById(R.id.deletefaultItem);


            //Log.i(LOG_TAG, "Adding Listener");

        }


    }


    public CustProfileListAdapter(Context cnt, List<Datum> myDataset) {
        mDataset = myDataset;
        cntext=cnt;


    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cust_profile_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
       // cntext=parent.getContext();
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
//TextView odrId,servicename,tech,Works,pay,payment;
        holder.cname.setText(mDataset.get(position).getNames());
        holder.acc_no.setText(mDataset.get(position).getAccNo());
        holder.amount.setText(mDataset.get(position).getAmount());
        holder.deposit.setText(mDataset.get(position).getDipositAmount());
        holder.accty.setText(mDataset.get(position).getAccType());
        Glide.with(cntext).load(Const.BASE_URL_IMG+mDataset.get(position).getImage()).into(holder.proimg);
       /* holder.copybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager)cntext.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setText(holder.acc_no.getText());
                Toast.makeText(cntext, "Account number  Copied  ", Toast.LENGTH_SHORT).show();
            }
        });
*/


    }

    public void addItem(Datum dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}