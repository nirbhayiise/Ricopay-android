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

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.np.ricopayapp.R;
import com.np.ricopayapp.responsemodels.transaction_list_response.Datum;

import java.util.List;

public class TranscationListAdapter extends RecyclerView
        .Adapter<TranscationListAdapter
        .DataObjectHolder> {
   Context cntext;

    Typeface sonictonicfonts;

    private static String LOG_TAG = "CategoryRecyclerViewAdapter";
    private List<com.np.ricopayapp.responsemodels.transaction_list_response.Datum> mDataset;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
           {
        TextView dt,nm,acc,acctype,deposit;
        LinearLayout lay1;



        public DataObjectHolder(View itemView) {
            super(itemView);
            dt = (TextView) itemView.findViewById(R.id.dt);
            nm = (TextView) itemView.findViewById(R.id.nm);
            acc = (TextView) itemView.findViewById(R.id.acc);
            acctype = (TextView) itemView.findViewById(R.id.acctype);
            deposit = (TextView) itemView.findViewById(R.id.deposit);

            lay1 = (LinearLayout) itemView.findViewById(R.id.lay1);

          //  deletefaultItem = (LinearLayout) itemView.findViewById(R.id.deletefaultItem);


            //Log.i(LOG_TAG, "Adding Listener");

        }


    }


    public TranscationListAdapter(Context cnt, List<com.np.ricopayapp.responsemodels.transaction_list_response.Datum> myDataset) {
        mDataset = myDataset;
        cntext=cnt;


    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trascation_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
       // cntext=parent.getContext();
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
//TextView odrId,servicename,tech,Works,pay,payment;
        holder.dt.setText(mDataset.get(position).getDateUpdated());
        holder.nm.setText(mDataset.get(position).getNames());
        holder.acc.setText(mDataset.get(position).getAccNo());
        holder.acctype.setText(mDataset.get(position).getAccType());
        holder.deposit.setText(mDataset.get(position).getAmount());



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